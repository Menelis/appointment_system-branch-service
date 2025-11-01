package co.appointment.entity;

import co.appointment.shared.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "branch_contacts")
@Setter
@Getter
public class BranchContact extends BaseEntity {

    @EmbeddedId
    private BranchContactKey id;

    @ManyToOne
    @MapsId("branchId")
    private Branch branch;

    @ManyToOne
    @MapsId("contactTypeId")
    private ContactType contactType;

    @Column(nullable = false)
    private String contact;

    public BranchContact() {}

    public BranchContact(final Branch branch, final ContactType contactType, final String contact, final BranchContactKey id) {
        this.branch = branch;
        this.contactType = contactType;
        this.contact = contact;
        this.id = id;
    }
}
