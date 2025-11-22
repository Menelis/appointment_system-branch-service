package co.appointment.mapper;

import co.appointment.dto.CityDTO;
import co.appointment.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CityToDTOMapper {
    CityDTO cityDTO(City city);
}
