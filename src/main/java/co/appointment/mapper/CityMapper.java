package co.appointment.mapper;

import co.appointment.entity.City;
import co.appointment.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityMapper {
    private final CityRepository cityRepository;

    public City fromId(final Integer id) {
        if(id == null) {
            return null;
        }
        return cityRepository.findById(id).orElse(null);
    }
}
