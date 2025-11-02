package co.appointment.service;

import co.appointment.dto.SlotDTO;
import co.appointment.entity.Slot;
import co.appointment.mapper.SlotToDTOMapper;
import co.appointment.payload.request.NewSlotRequest;
import co.appointment.payload.request.UpdateSlotRequest;
import co.appointment.repository.SlotRepository;
import co.appointment.shared.payload.response.ApiResponse;
import co.appointment.shared.util.SharedObjectUtils;
import co.appointment.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlotService {
    private final SlotRepository slotRepository;
    private final SlotToDTOMapper slotToDTOMapper;

    public Page<SlotDTO> getAllSlots(final Integer pageNo, final Integer pageSize) {
        Page<Slot> slots = slotRepository.findAll(ObjectUtils.getPageable(pageNo, pageSize));
        List<SlotDTO> content = slots
                .stream()
                .map(slotToDTOMapper::toDTO)
                .toList();
        return new PageImpl<>(content, slots.getPageable(), slots.getTotalElements());
    }
    public ResponseEntity<ApiResponse<SlotDTO>> getSlotById(final Integer id) {
        Optional<Slot> optionalSlot = slotRepository.findById(id);

        return optionalSlot
                .map(slot ->  ResponseEntity.ok(new ApiResponse<>(slotToDTOMapper.toDTO(slot))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    public ResponseEntity<ApiResponse<SlotDTO>> createSlot(final NewSlotRequest newSlotRequest) {
        Optional<Slot> optionalSlot = slotRepository.findBySlotStartAndSlotEnd(newSlotRequest.getSlotStart(), newSlotRequest.getSlotEnd());
        if (optionalSlot.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Slot already exists"));
        }
        slotRepository.save(slotToDTOMapper.toEntity(newSlotRequest));
        return ResponseEntity.ok(new ApiResponse<>("Slot has been created successfully"));
    }

    public ResponseEntity<ApiResponse<SlotDTO>> updateSlot(final int id, final UpdateSlotRequest updateSlotRequest) {
        if(id != updateSlotRequest.getId()) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Slot id does not match"));
        }
        Optional<Slot> optionalSlot = slotRepository.findById(id);
        if (optionalSlot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Slot slot = slotToDTOMapper.toEntity(updateSlotRequest);
        SharedObjectUtils.mapAuditFields(slot, optionalSlot.get());
        slotRepository.save(slot);

        return ResponseEntity.ok(new ApiResponse<>("Slot has been updated successfully"));
    }
    public ResponseEntity<ApiResponse<Void>> deleteSlot(final int id) {
        Optional<Slot> optionalSlot = slotRepository.findById(id);
        if (optionalSlot.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        slotRepository.delete(optionalSlot.get());
        return ResponseEntity.noContent().build();
    }
}
