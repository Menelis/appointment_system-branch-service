package co.appointment.service;

import co.appointment.dto.ProvinceDTO;
import co.appointment.mapper.ProvinceToDTOMapper;
import co.appointment.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService {

    private final ProvinceRepository provinceRepository;
    private final ProvinceToDTOMapper provinceToDTOMapper;

    public List<ProvinceDTO> getAllProvinces() {
        return provinceRepository.findAll()
                .stream()
                .map(provinceToDTOMapper::toDTO)
                .toList();
    }
}
