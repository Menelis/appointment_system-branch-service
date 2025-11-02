package co.appointment.entity;

import co.appointment.shared.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
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

    @Column(name = "is_enabled", nullable = false)
    public Boolean isEnabled = true;
}
