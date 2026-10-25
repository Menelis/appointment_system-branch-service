package co.appointment.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface ProvinceRepository
        extends CrudRepository<ProvinceEntity, UUID>, ListPagingAndSortingRepository<ProvinceEntity, UUID> {}
