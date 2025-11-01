package co.appointment.repository;

import co.appointment.entity.AppointmentDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentDateRepository extends JpaRepository<AppointmentDate, Long> {
}
