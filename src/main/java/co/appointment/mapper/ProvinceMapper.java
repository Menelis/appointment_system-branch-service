package co.appointment.mapper;

import co.appointment.entity.Province;
import co.appointment.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProvinceMapper {

    private final ProvinceRepository provinceRepository;

    public Province fromId(Integer id) {
        if(id == null) {
            return null;
        }
        return provinceRepository.findById(id).orElse(null);
    }
}
