package co.appointment.mapper;

import co.appointment.entity.ContactType;
import co.appointment.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContactTypeMapper {

    private final ContactTypeRepository contactTypeRepository;

    public ContactType fromId(final Integer id) {
        if(id == null) {
            return null;
        }
        return contactTypeRepository.findById(id).orElse(null);
    }
}
