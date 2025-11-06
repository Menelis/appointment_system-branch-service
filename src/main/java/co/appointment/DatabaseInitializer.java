package co.appointment;

import co.appointment.entity.ContactType;
import co.appointment.repository.ContactTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public void run(String... args) {
        seedContactTypes();
    }
    void seedContactTypes() {
        List<ContactType> contactTypes = List.of(
                new ContactType("Email"),
                new ContactType("Fax"),
                new ContactType("LandLine"));
        contactTypes.forEach(contactType -> {
            Optional<ContactType> optionalContactType = contactTypeRepository.findByName(contactType.getName());
            if (optionalContactType.isPresent()) {
                contactTypeRepository.save(contactType);
            }
        });
    }
}
