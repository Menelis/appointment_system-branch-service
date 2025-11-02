package co.appointment.controller;

import co.appointment.dto.SlotDTO;
import co.appointment.entity.Slot;
import co.appointment.payload.request.NewSlotRequest;
import co.appointment.payload.request.UpdateSlotRequest;
import co.appointment.service.SlotService;
import co.appointment.shared.constant.SharedConstants;
import co.appointment.shared.payload.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/slot")
@RequiredArgsConstructor
public class SlotControllerV1 {
    private final SlotService slotService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SlotDTO>>> getAllSlots(@RequestParam(name = SharedConstants.PAGE_NUMBER_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_NUMBER_DEFAULT_VALUE) final int pageNumber,
                                                               @RequestParam(name = SharedConstants.PAGE_SIZE_PARAMETER_NAME, defaultValue = SharedConstants.PAGE_SIZE_DEFAULT_VALUE) final int pageSize) {
        Page<SlotDTO> pagedSlots = slotService.getAllSlots(pageNumber, pageSize);

         return ResponseEntity.ok(new ApiResponse<>(pagedSlots));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SlotDTO>> getSlotById(@PathVariable("id") final Integer id) {
        return slotService.getSlotById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<SlotDTO>> createSlot(@RequestBody @Valid final NewSlotRequest newSlotRequest) {
        return slotService.createSlot(newSlotRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SlotDTO>> updateSlot(@PathVariable("id") final Integer id, @RequestBody @Valid final UpdateSlotRequest newSlotRequest) {
        return slotService.updateSlot(id, newSlotRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSlot(@PathVariable("id") final Integer id) {
        return slotService.deleteSlot(id);
    }
}
