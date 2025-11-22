package co.appointment.mapper;

import co.appointment.dto.ProvinceDTO;
import co.appointment.entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProvinceToDTOMapper {

    ProvinceDTO toDTO(Province province);
}
