package co.appointment.entity;


import co.appointment.shared.entity.base.BaseEntity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "appointment_dates")
@Getter
@Setter
public class AppointmentDate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "appointment_date")
    private Date appointmentDate;
}
