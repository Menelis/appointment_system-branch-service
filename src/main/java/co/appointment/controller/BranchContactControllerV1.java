package co.appointment.controller;

import co.appointment.dto.BranchContactDTO;
import co.appointment.payload.request.NewBranchContactRequest;
import co.appointment.payload.request.UpdateBranchContactRequest;
import co.appointment.service.BranchContactService;
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
@RequestMapping("/api/v1/branch-contact")
@RequiredArgsConstructor
public class BranchContactControllerV1 {

    private final BranchContactService branchContactService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BranchContactDTO>>> getBranchContacts(@RequestParam(name = SharedConstants.PAGE_NUMBER_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_NUMBER_DEFAULT_VALUE) final int pageNumber,
                                                                                 @RequestParam(name = SharedConstants.PAGE_SIZE_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_SIZE_DEFAULT_VALUE) final int pageSize) {
        Page<BranchContactDTO> pagedBranchContacts = branchContactService.getBranchContacts(pageNumber, pageSize);

        return ResponseEntity.ok(new ApiResponse<>(pagedBranchContacts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BranchContactDTO>> getBranchContact(@PathVariable("id") final Integer id) {
        return branchContactService.getBranchContactById(id);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<ApiResponse<BranchContactDTO>> createBranchContact(@RequestBody @Valid final NewBranchContactRequest  request) {
        return branchContactService.createBranchContact(request);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ApiResponse<BranchContactDTO>> updateBranchContact(@PathVariable("id") final Integer id,
                                                                             @RequestBody @Valid final UpdateBranchContactRequest request) {
        return branchContactService.updateBranchContact(id, request);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBranchContact(@PathVariable("id") final Integer id) {
        return branchContactService.deleteBranchContact(id);
    }
}
