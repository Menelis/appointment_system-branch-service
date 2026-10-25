package co.appointment.service;

import co.appointment.dto.CityDTO;
import co.appointment.mapper.CityMapper;
import co.appointment.persistence.CityEntity;
import co.appointment.persistence.CityRepository;
import co.appointment.shared.exception.ServiceException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<CityDTO> getCities(final UUID provinceId, final UUID xCorrelationId) {
        if (provinceId == null) {
            return getAllCities(xCorrelationId);
        }
        return getCitiesByProvince(provinceId, xCorrelationId);
    }

    private List<CityDTO> getCitiesByProvince(final UUID provinceId, final UUID xCorrelationId) {
        List<CityEntity> cities = cityRepository.findByProvinceId(provinceId);
        if (cities.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        return cities.stream().map(cityMapper::mapToDto).toList();
    }

    private List<CityDTO> getAllCities(final UUID xCorrelationId) {

        List<CityEntity> cities = cityRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        if (cities.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        return cities.stream().map(cityMapper::mapToDto).toList();
    }
}
