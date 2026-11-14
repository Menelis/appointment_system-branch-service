package co.appointment.service;

import co.appointment.dto.ProvinceDTO;
import co.appointment.mapper.ProvinceMapper;
import co.appointment.persistence.ProvinceEntity;
import co.appointment.persistence.ProvinceRepository;
import co.appointment.shared.exception.ServiceException;
import co.appointment.shared.util.SharedObjectUtils;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceMapper provinceMapper;

    public List<ProvinceDTO> getProvinces() {
        return provinceRepository.findAll(Sort.by(Sort.Direction.ASC, "name")).stream()
                .map(provinceMapper::mapToDto)
                .toList();
    }

    public Page<ProvinceDTO> getProvinces(
            final int pageNo, final int pageSize, final String searchTerm, final UUID xCorrelationId) {
        Pageable pageable = SharedObjectUtils.getPageable(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "name"));
        Page<ProvinceEntity> paginatedProvinces;
        if (StringUtils.hasText(searchTerm)) {
            paginatedProvinces = provinceRepository.findByNameContainingIgnoreCase(searchTerm, pageable);
        } else {
            paginatedProvinces = provinceRepository.findAll(pageable);
        }
        List<ProvinceDTO> provinces = paginatedProvinces.getContent().stream()
                .map(provinceMapper::mapToDto)
                .toList();
        if (provinces.isEmpty()) {
            throw new ServiceException(HttpStatus.NO_CONTENT);
        }
        return new PageImpl<>(provinces, pageable, paginatedProvinces.getTotalElements());
    }
}
