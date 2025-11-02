package co.appointment;

import co.appointment.entity.ContactType;
import co.appointment.entity.Slot;
import co.appointment.repository.ContactTypeRepository;
import co.appointment.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final ContactTypeRepository contactTypeRepository;
    private final SlotRepository slotRepository;
    @Override
    public void run(String... args) {
        seedContactTypes();
        seedSlots();
    }
    void seedSlots() {
        List<Slot> slots = List.of(
                new Slot("08:00", "09:00"),
                new Slot("09:00", "10:00"),
                new Slot("10:00", "11:00"),
                new Slot("12:00", "13:00"),
                new Slot("13:00", "14:00"),
                new Slot("14:00", "15:00"),
                new Slot("15:00", "16:00"),
                new Slot("16:00", "17:00")
        );
        slots.forEach(slot -> {
            Optional<Slot> optionalSlot = slotRepository.findBySlotStartAndSlotEnd(slot.getSlotStart(), slot.getSlotEnd());
            if (optionalSlot.isEmpty()) {
                slotRepository.save(slot);
            }
        });
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
