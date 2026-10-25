package co.appointment.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface BranchRepository
        extends CrudRepository<BranchEntity, UUID>, ListPagingAndSortingRepository<BranchEntity, UUID> {
    List<BranchEntity> findAllByProvinceIdAndCityId(UUID provinceId, UUID cityId);
}
