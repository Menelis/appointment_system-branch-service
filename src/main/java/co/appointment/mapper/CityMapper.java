package co.appointment.mapper;

import co.appointment.dto.CityDTO;
import co.appointment.persistence.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityMapper {

    CityDTO mapToDto(CityEntity city);
}
