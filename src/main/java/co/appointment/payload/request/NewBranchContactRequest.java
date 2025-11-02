package co.appointment.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewBranchContactRequest {

    @NotEmpty(message = "Contact cannot be empty")
    @NotNull(message = "Contact cannot be null")
    @NotBlank(message = "Contact cannot be blank")
    @Size(max = 255, message = "Only 255 characters allowed for Contact")
    private String contact;

    @NotEmpty(message = "Branch Id cannot be empty")
    @NotNull(message = "Branch Id cannot be null")
    @NotBlank(message = "Branch Id cannot be blank")
    private Integer branchId;

    @NotEmpty(message = "Contact Type Id cannot be empty")
    @NotNull(message = "Contact Type Id cannot be null")
    @NotBlank(message = "Contact Type Id cannot be blank")
    private Integer contactTypeId;
}
