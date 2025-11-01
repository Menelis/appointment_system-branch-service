package co.appointment.entity;

import co.appointment.shared.entity.base.BaseEntity;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "branches")
@Getter
@Setter
public class Branch extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100, name = "street_no")
    private String streetNo;

    @Column(nullable = false, name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String province;

    @Column(nullable = false, length = 10, name = "postal_code")
    private String postalCode;

    @OneToMany(
            mappedBy = "branch",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BranchContact> branchContacts = new HashSet<>();

    public void addBranchContact(final ContactType contactType,
                                 final String contact) {
        this.branchContacts.add(new BranchContact(this, contactType, contact, new BranchContactKey(this.id, contactType.getId())));
    }
    public void removeBranchContact(final ContactType contactType) {
        this.branchContacts.removeIf(branchContact -> branchContact.getContactType().equals(contactType) && branchContact.getBranch().equals(this));
    }
}
