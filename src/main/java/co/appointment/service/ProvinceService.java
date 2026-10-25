package co.appointment.service;

import co.appointment.dto.ProvinceDTO;
import co.appointment.mapper.ProvinceMapper;
import co.appointment.persistence.ProvinceEntity;
import co.appointment.persistence.ProvinceRepository;
import co.appointment.shared.exception.ServiceException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    public List<ProvinceDTO> getAllProvinces(final UUID xCorrelationId) {

        List<ProvinceEntity> provinces = provinceRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
        if (provinces.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        return provinces.stream().map(provinceMapper::mapToDto).toList();
    }
}
