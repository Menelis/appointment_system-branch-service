package co.appointment.repository;

import co.appointment.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer>{
    Optional<City> findByName(String name);
    List<City> findByProvinceId(Integer provinceId);
}
