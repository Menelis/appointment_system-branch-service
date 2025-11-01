package co.appointment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
public class BranchContactKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "contact_type_id")
    private Integer contactTypeId;

    public BranchContactKey() {}

    public BranchContactKey(final Integer branchId, final Integer contactTypeId) {
        this.branchId = branchId;
        this.contactTypeId = contactTypeId;
    }
}
