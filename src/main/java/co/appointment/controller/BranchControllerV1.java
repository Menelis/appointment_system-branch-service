package co.appointment.controller;

import co.appointment.dto.BranchDTO;
import co.appointment.payload.request.NewBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import co.appointment.service.BranchService;
import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.payload.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchControllerV1 {

    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BranchDTO>>> getAllBranches(
                                                                 @RequestParam(name = SharedConstants.PAGE_NUMBER_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_NUMBER_DEFAULT_VALUE) final int pageNumber,
                                                                 @RequestParam(name = SharedConstants.PAGE_SIZE_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_SIZE_DEFAULT_VALUE) final int pageSize) {
        Page<BranchDTO> pagedBranches = branchService.getAllBranches(pageNumber, pageSize);

        return ResponseEntity.ok(new ApiResponse<>(pagedBranches));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchDTO>> getBranchById(@PathVariable final Integer id) {
        return branchService.getBranchById(id);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<BranchDTO>> createBranch(@RequestBody @Valid final NewBranchRequest branchRequest) {
        BranchDTO branchDTO = branchService.createBranch(branchRequest);
        if(branchDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new ApiResponse<>("Branch has been created successfully."));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<BranchDTO>> updateBranch(@PathVariable("id") final Integer id, @RequestBody final UpdateBranchRequest branchRequest) {
        return branchService.updateBranch(id, branchRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBranch(@PathVariable final Integer id) {
        return branchService.deleteBranch(id);
    }
}
