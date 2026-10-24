package co.appointment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchControllerV1 {

    /*private final BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches(
            @RequestParam(name = X_CORRELATION_ID) final UUID xCorrelationId) {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/getBranchesByProvinceIdAndCityId/{provinceId}/{cityId}")
    public ResponseEntity<List<BranchDTO>> getAllBranchesByProvinceId(
            @PathVariable(name = "provinceId") final int provinceId, @PathVariable(name = "cityId") final int cityId) {
        return ResponseEntity.ok(branchService.getBranchesByProvinceId(provinceId, cityId));
    }

    @GetMapping("/get-paginated-branches")
    public ResponseEntity<Page<BranchDTO>> getAllBranches(
            @RequestParam(
                            name = SharedConstants.PAGE_NUMBER_PARAMETER_NAME,
                            defaultValue = SharedConstants.PAGE_NUMBER_DEFAULT_VALUE)
                    final int pageNumber,
            @RequestParam(
                            name = SharedConstants.PAGE_SIZE_PARAMETER_NAME,
                            defaultValue = SharedConstants.PAGE_SIZE_DEFAULT_VALUE)
                    final int pageSize,
            @RequestParam(name = SharedConstants.SEARCH_TERM_PARAMETER_NAME, required = false)
                    final String searchTerm) {
        Page<BranchDTO> pagedBranches = branchService.getPagedBranches(pageNumber, pageSize, searchTerm);

        return ResponseEntity.ok(pagedBranches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(
            @PathVariable final Integer id, @RequestHeader(name = X_CORRELATION_ID) final UUID xCorrelationId) {

        Optional<BranchDTO> branchDTO = branchService.getBranchById(id);

        return ResponseEntity.ok(branchDTO.get());
    }

    @PostMapping("/admin/create")
    public ResponseEntity<ServiceResponseDto> createBranch(
            @RequestHeader(name = X_CORRELATION_ID) final UUID xCorrelationId,
            @RequestBody @Valid final NewBranchRequest branchRequest) {
        branchService.createBranch(branchRequest, xCorrelationId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ApiResponse<BranchDTO>> updateBranch(
            @PathVariable("id") final Integer id,
            @RequestHeader(name = X_CORRELATION_ID) final UUID xCorrelationId,
            @RequestBody final UpdateBranchRequest branchRequest) {
        branchService.updateBranch(id, branchRequest, xCorrelationId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBranch(
            @PathVariable final Integer id, @RequestHeader(name = X_CORRELATION_ID) final UUID xCorrelationId) {

        branchService.deleteBranch(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }*/
}
