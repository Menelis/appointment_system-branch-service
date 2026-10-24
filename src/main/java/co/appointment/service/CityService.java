package co.appointment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    /*private final CityRepository cityRepository;
    private final CityToDTOMapper cityToDTOMapper;

    public List<CityDTO> getCitiesByProvinceId(final UUID provinceId) {
        return cityRepository.findByProvinceId(provinceId).stream()
                .map(cityToDTOMapper::cityDTO)
                .toList();
    }

     */
}
