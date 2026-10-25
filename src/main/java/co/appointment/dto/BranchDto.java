package co.appointment.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private UUID id;
    private String name;
    private Address physicalAddress;
    private String email;
    private String faxNo;
    private String landLine;
}
