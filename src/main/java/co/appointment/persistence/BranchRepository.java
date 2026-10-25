package co.appointment.persistence;

import co.appointment.shared.record.BranchSummary;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface BranchRepository
        extends CrudRepository<BranchEntity, UUID>, ListPagingAndSortingRepository<BranchEntity, UUID> {

    @Query("""
            SELECT id,
                   version,
                   created_at,
                   updated_at,
                   name,
                   physical_address_line1,
                   physical_address_line2,
                   physical_suburb,
                   physical_province_id,
                   physical_city_id,
                   physical_postal_code,
                   email,
                   fax_no,
                   land_line
            FROM branches
            WHERE physical_province_id = :provinceId AND physical_city_id = :cityId
""")
    List<BranchEntity> findAllByProvinceIdAndCityId(UUID provinceId, UUID cityId);

    @Query("""
         SELECT name,
                physical_address_line1 AS address_Line1,
                physical_address_line2 AS address_Line1,
                physical_suburb,
                p.name as province,
                c.name as city,
                physical_postal_code as postal_code,
                email,
                fax_no,
                land_line
        FROM branches b INNER JOIN province p ON B.physical_province_id = p.id
                        INNER JOIN city c ON b.physical_city_id = c.id
         WHERE b.id = :id
""")
    Optional<BranchSummary> getBranchSummary(UUID id);
}
