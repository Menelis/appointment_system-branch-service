package co.appointment.controller;

import co.appointment.dto.ContactTypeDTO;
import co.appointment.payload.request.NewContactTypeRequest;
import co.appointment.payload.request.UpdateContactTypeRequest;
import co.appointment.service.ContactTypeService;
import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.payload.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contact-type")
public class ContactTypeControllerV1 {

    private final ContactTypeService contactTypeService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ContactTypeDTO>>> getAllContactTypes(
            @RequestParam(name = SharedConstants.PAGE_NUMBER_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_NUMBER_DEFAULT_VALUE) final Integer pageNumber,
            @RequestParam(name = SharedConstants.PAGE_SIZE_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_SIZE_DEFAULT_VALUE) final Integer pageSize) {
        Page<ContactTypeDTO> pagedContactTypes = contactTypeService.getAllContactTypes(pageNumber, pageSize);

        return ResponseEntity.ok(new ApiResponse<>(pagedContactTypes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactTypeDTO>> getContactTypeById(@PathVariable final Integer id) {
        return contactTypeService.getContactTypeById(id);
    }
    @PostMapping("/admin/create")
    public ResponseEntity<ApiResponse<ContactTypeDTO>> createContactType(@RequestBody @Valid final NewContactTypeRequest contactTypeRequest) {
        return contactTypeService.createContactType(contactTypeRequest);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ApiResponse<ContactTypeDTO>> updateContactType(@PathVariable("id") final Integer id, @RequestBody @Valid final UpdateContactTypeRequest updateContactTypeRequest) {
        return contactTypeService.updateContactType(id, updateContactTypeRequest);
    }
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContactType(@PathVariable("id") final Integer id) {
        return contactTypeService.deleteContactType(id);
    }
}
