package co.appointment.entity;

import co.appointment.shared.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "branch_daily_slots")
public class BranchDailySlot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    public Branch branch;

    @ManyToOne
    @JoinColumn(name = "appointment_date_id", nullable = false)
    public AppointmentDate appointmentDate;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    public Slot slot;
}
