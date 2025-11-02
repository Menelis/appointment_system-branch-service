package co.appointment.repository;

import co.appointment.entity.Branch;
import co.appointment.entity.BranchContact;
import co.appointment.entity.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchContactRepository extends JpaRepository<BranchContact, Integer> {

    Optional<BranchContact> findByBranchAndContactTypeAndContact(Branch branch,
                                                                 ContactType contactType,
                                                                 String contact);
}
