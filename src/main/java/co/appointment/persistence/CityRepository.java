package co.appointment.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<CityEntity, UUID> {}
