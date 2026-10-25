package co.appointment.mapper;

import co.appointment.dto.ProvinceDTO;
import co.appointment.persistence.ProvinceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProvinceMapper {

    ProvinceDTO mapToDto(ProvinceEntity province);
}
