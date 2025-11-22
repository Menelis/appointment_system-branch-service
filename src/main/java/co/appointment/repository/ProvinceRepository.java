package co.appointment.repository;

import co.appointment.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    Optional<Province> findByName(String name);
}
