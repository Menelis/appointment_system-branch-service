package co.appointment.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<BranchEntity, UUID> {}
