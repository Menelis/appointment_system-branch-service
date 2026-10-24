package co.appointment.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "branch")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @CreatedDate
    private OffsetDateTime createdAt;

    @LastModifiedDate
    private OffsetDateTime updatedAt;

    @Version
    private Long version;

    private String name;
    private String addressLine1;
    private String addressLine2;
    private UUID cityId;
    private UUID provinceId;
    private String postalCode;
    private String email;
    private String faxNo;
    private String landLine;
}
