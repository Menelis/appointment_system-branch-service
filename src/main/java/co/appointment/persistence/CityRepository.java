package co.appointment.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CityRepository
        extends CrudRepository<CityEntity, UUID>, ListPagingAndSortingRepository<CityEntity, UUID> {
    List<CityEntity> findByProvinceId(UUID provinceId);
}
