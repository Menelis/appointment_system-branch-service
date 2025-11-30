package co.appointment.service;

import co.appointment.dto.BranchDTO;
import co.appointment.entity.Branch;
import co.appointment.mapper.BranchToDTOMapper;
import co.appointment.payload.request.NewBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import co.appointment.repository.BranchRepository;
import co.appointment.repository.specification.BranchSpecifications;
import co.appointment.shared.payload.response.ApiResponse;
import co.appointment.shared.util.SharedObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final BranchToDTOMapper branchToDTOMapper;

    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(branchToDTOMapper::toDTO)
                .toList();
    }
    public List<BranchDTO> getBranchesByProvinceId(final int provinceId,
                                                   final int cityId) {
        return this.branchRepository.findAllByProvinceIdAndCityId(provinceId, cityId)
                .stream()
                .map(branchToDTOMapper::toDTO)
                .toList();
    }
    private Page<Branch> getBranches(final int page, final int pageSize,
                                     final String searchTerm) {
        Pageable pageable = SharedObjectUtils.getPageable(page, pageSize);
        if(!StringUtils.hasText(searchTerm)) {
            return branchRepository.findAll(pageable);
        }
        Specification<Branch> branchSpecification = BranchSpecifications.nameContains(searchTerm)
                .or(BranchSpecifications.streetNoContains(searchTerm));
        return branchRepository.findAll(branchSpecification, pageable);
    }

    public Page<BranchDTO> getPagedBranches(final int page, final int pageSize,
                                            final String searchTerm) {
        Page<Branch> branches = getBranches(page, pageSize, searchTerm);

        List<BranchDTO> content = branches.stream()
                .map(branchToDTOMapper::toDTO)
                .toList();

        return new PageImpl<>(content, branches.getPageable(), branches.getTotalElements());
    }
    public ResponseEntity<ApiResponse<BranchDTO>> getBranchById(final int id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        return optionalBranch
                .map(branch -> ResponseEntity.ok(new ApiResponse<>(branchToDTOMapper.toDTO(branch))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    public BranchDTO createBranch(final NewBranchRequest branchRequest) {
        Branch newBranch = branchToDTOMapper.toEntity(branchRequest);
        return branchToDTOMapper.toDTO(branchRepository.save(newBranch));
    }
    public ResponseEntity<ApiResponse<BranchDTO>> updateBranch(final int id, final UpdateBranchRequest branchRequest) {
       if(id != branchRequest.getId()) {
           return ResponseEntity.badRequest().body(new ApiResponse<>("id mismatch"));
       }
       Optional<Branch> optionalBranch = branchRepository.findById(id);
       if(optionalBranch.isEmpty()) {
           return ResponseEntity.notFound().build();
       }
       Branch branch = branchToDTOMapper.toEntity(branchRequest);
        SharedObjectUtils.mapAuditFields(branch, optionalBranch.get());
       branchRepository.save(branch);
       return ResponseEntity.ok().body(new ApiResponse<>(true, "Branch updated successfully"));
    }
    public ResponseEntity<ApiResponse<Void>> deleteBranch(final int id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if(optionalBranch.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        branchRepository.delete(optionalBranch.get());
        return ResponseEntity.noContent().build();
    }
}
