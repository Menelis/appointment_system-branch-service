package co.appointment.service;

import co.appointment.dto.BranchDto;
import co.appointment.mapper.BranchMapper;
import co.appointment.payload.request.CreateBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import co.appointment.persistence.BranchEntity;
import co.appointment.persistence.BranchRepository;
import co.appointment.shared.dto.ServiceResponseDto;
import co.appointment.shared.exception.ServiceException;
import co.appointment.shared.util.SharedObjectUtils;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchMapper branchMapper;

    public List<BranchDto> getAllBranches(final UUID xCorrelationId) {

        List<BranchEntity> branches = branchRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));

        if (branches.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        return branches.stream().map(branchMapper::mapToDto).toList();
    }

    public List<BranchDto> getBranches(final UUID provinceId, final UUID cityId, final UUID xCorrelationId) {

        List<BranchEntity> branches = branchRepository.findAllByProvinceIdAndCityId(provinceId, cityId);

        if (branches.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }

        return branches.stream().map(branchMapper::mapToDto).toList();
    }

    public Page<BranchDto> getPagedBranches(
            final int page, final int pageSize, final String searchTerm, final UUID xCorrelationId) {

        Page<BranchEntity> branches = getBranches(page, pageSize, searchTerm);

        if (branches.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }

        List<BranchDto> content = branches.stream().map(branchMapper::mapToDto).toList();

        return new PageImpl<>(content, branches.getPageable(), branches.getTotalElements());
    }

    private Page<BranchEntity> getBranches(final int page, final int pageSize, final String searchTerm) {
        Pageable pageable = SharedObjectUtils.getPageable(page, pageSize);
        if (!StringUtils.hasText(searchTerm)) {
            return branchRepository.findAll(pageable);
        }
        return branchRepository.findAll(pageable);
    }

    public Optional<BranchDto> getBranchById(final UUID id, final UUID xCorrelationId) {
        Optional<BranchEntity> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        return Optional.of(branchMapper.mapToDto(optionalBranch.get()));
    }

    public ServiceResponseDto createBranch(final CreateBranchRequest branchRequest, final UUID xCorrelationId) {
        branchRepository.save(branchMapper.mapToEntity(branchRequest));

        return ServiceResponseDto.builder()
                .success(true)
                .correlationId(xCorrelationId)
                .build();
    }

    public ServiceResponseDto updateBranch(
            final UUID id, final UpdateBranchRequest branchRequest, final UUID xCorrelationId) {

        Optional<BranchEntity> branch = branchRepository.findById(id);
        if (branch.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        branchRepository.save(branchMapper.mapToEntity(branchRequest));

        return ServiceResponseDto.builder()
                .success(true)
                .correlationId(xCorrelationId)
                .build();
    }

    public ServiceResponseDto deleteBranch(final UUID id, final UUID xCorrelationId) {
        Optional<BranchEntity> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        branchRepository.delete(optionalBranch.get());
        return ServiceResponseDto.builder()
                .success(true)
                .correlationId(xCorrelationId)
                .build();
    }
}
