package co.appointment.repository;

import co.appointment.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    List<Branch> findAllByProvinceIdAndCityId(Integer provinceId, Integer cityId);
}
