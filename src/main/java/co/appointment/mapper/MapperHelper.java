package co.appointment.mapper;

import co.appointment.dto.CityDTO;
import co.appointment.dto.ProvinceDTO;
import co.appointment.persistence.CityEntity;
import co.appointment.persistence.CityRepository;
import co.appointment.persistence.ProvinceEntity;
import co.appointment.persistence.ProvinceRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperHelper {

    private final CityRepository cityRepository;
    private final ProvinceRepository provinceRepository;

    public CityDTO mapCityById(final UUID id) {
        if (id == null) {
            return null;
        }
        Optional<CityEntity> city = cityRepository.findById(id);
        return city.map(cityEntity -> CityDTO.builder()
                        .id(cityEntity.getId())
                        .name(cityEntity.getName())
                        .build())
                .orElse(null);
    }

    public ProvinceDTO mapProvinceById(final UUID id) {
        if (id == null) {
            return null;
        }
        Optional<ProvinceEntity> province = provinceRepository.findById(id);
        return province.map(provinceEntity -> ProvinceDTO.builder()
                        .id(provinceEntity.getId())
                        .name(provinceEntity.getName())
                        .build())
                .orElse(null);
    }
}
