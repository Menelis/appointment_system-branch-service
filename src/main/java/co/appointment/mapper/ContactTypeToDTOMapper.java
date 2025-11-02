package co.appointment.mapper;

import co.appointment.dto.ContactTypeDTO;
import co.appointment.entity.ContactType;
import co.appointment.payload.request.NewContactTypeRequest;
import co.appointment.payload.request.UpdateContactTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactTypeToDTOMapper {
    ContactTypeDTO toDTO(ContactType contactType);
    ContactType toEntity(NewContactTypeRequest request);
    ContactType toEntity(UpdateContactTypeRequest request);
}
