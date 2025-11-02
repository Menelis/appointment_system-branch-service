package co.appointment.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateBranchContactRequest extends NewBranchContactRequest {

    @NotEmpty(message = "Contact Type Id cannot not be empty")
    @NotBlank(message = "Contact Type Id cannot be blank")
    @NotNull(message = "Contact Type Id cannot be null")
    private Integer id;
}
