package co.appointment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BranchService {

    /*
    private final BranchRepository branchRepository;
    private final BranchToDTOMapper branchToDTOMapper;

    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAll().stream().map(branchToDTOMapper::toDTO).toList();
    }

    public List<BranchDTO> getBranchesByProvinceId(final UUID provinceId, final UUID cityId) {
        return this.branchRepository.findAllByProvinceIdAndCityId(provinceId, cityId).stream()
                .map(branchToDTOMapper::toDTO)
                .toList();
    }

    private Page<Branch> getBranches(final int page, final int pageSize, final String searchTerm) {
        Pageable pageable = SharedObjectUtils.getPageable(page, pageSize);
        if (!StringUtils.hasText(searchTerm)) {
            return branchRepository.findAll(pageable);
        }
        Specification<Branch> branchSpecification =
                BranchSpecifications.nameContains(searchTerm).or(BranchSpecifications.streetNoContains(searchTerm));
        return branchRepository.findAll(branchSpecification, pageable);
    }

    public Page<BranchDTO> getPagedBranches(final int page, final int pageSize, final String searchTerm) {
        Page<Branch> branches = getBranches(page, pageSize, searchTerm);

        List<BranchDTO> content =
                branches.stream().map(branchToDTOMapper::toDTO).toList();

        return new PageImpl<>(content, branches.getPageable(), branches.getTotalElements());
    }

    public Optional<BranchDTO> getBranchById(final int id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        return Optional.of(branchToDTOMapper.toDTO(optionalBranch.get()));
    }

    public void createBranch(final NewBranchRequest branchRequest, final UUID correlationId) {
        branchRepository.save(branchToDTOMapper.toEntity(branchRequest));
    }

    public void updateBranch(final int id, final UpdateBranchRequest branchRequest, final UUID correlationId) {

        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        Branch branch = branchToDTOMapper.toEntity(branchRequest);
        branchRepository.save(branch);
    }

    public void deleteBranch(final int id) {
        Optional<Branch> optionalBranch = branchRepository.findById(id);
        if (optionalBranch.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        branchRepository.delete(optionalBranch.get());
    }

     */
}
