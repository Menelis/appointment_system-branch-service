package co.appointment.service;

import co.appointment.dto.CityDTO;
import co.appointment.mapper.CityToDTOMapper;
import co.appointment.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityToDTOMapper cityToDTOMapper;

    public List<CityDTO> getCitiesByProvinceId(final int provinceId) {
        return cityRepository.findByProvinceId(provinceId)
                .stream()
                .map(cityToDTOMapper::cityDTO)
                .toList();
    }
}
