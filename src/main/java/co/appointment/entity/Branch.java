package co.appointment.entity;

import co.appointment.shared.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.EqualsAndHashCode;
import lombok.Data;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "branch")
@Data
public class Branch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100, name = "street_no")
    private String streetNo;

    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;

    @Column(nullable = false, length = 10, name = "postal_code")
    private String postalCode;

    @Column(nullable = false, length = 100, name = "email_address")
    private String email;

    @Column(length = 50, name = "fax_no")
    private String faxNo;

    @Column(nullable = false, length = 50, name = "land_line")
    private String landLine;

    public Branch() {}
    public Branch(
            final String name,
            final String streetNo,
            final String addressLine1,
            final String landLine,
            final String email,
            final Province province,
            final City city,
            final String postalCode) {
        this.name = name;
        this.streetNo = streetNo;
        this.addressLine1 = addressLine1;
        this.landLine = landLine;
        this.email = email;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
    }
}
