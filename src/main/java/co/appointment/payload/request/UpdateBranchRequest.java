package co.appointment.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateBranchRequest extends NewBranchRequest {

    @NotBlank(message = "Branch Id cannot be blank")
    @NotEmpty(message = "Branch Id cannot be empty")
    private Integer id;
}
