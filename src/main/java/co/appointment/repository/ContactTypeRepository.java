package co.appointment.repository;

import co.appointment.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactTypeRepository extends JpaRepository<ContactType, Integer> {
    Optional<ContactType> findByName(String name);
}
