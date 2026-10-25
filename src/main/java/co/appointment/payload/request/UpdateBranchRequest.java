package co.appointment.payload.request;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateBranchRequest extends CreateBranchRequest {

    @NotNull(message = "Branch Id cannot be null")
    private UUID id;
}
