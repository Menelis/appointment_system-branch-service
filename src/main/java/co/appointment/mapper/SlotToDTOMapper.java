package co.appointment.mapper;

import co.appointment.dto.SlotDTO;
import co.appointment.entity.Slot;
import co.appointment.payload.request.NewSlotRequest;
import co.appointment.payload.request.UpdateSlotRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SlotToDTOMapper {
    Slot toEntity(NewSlotRequest newSlotRequest);
    Slot toEntity(UpdateSlotRequest updateSlotRequest);
    SlotDTO toDTO(Slot slot);
}
