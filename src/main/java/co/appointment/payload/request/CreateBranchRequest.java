package co.appointment.payload.request;

import co.appointment.dto.AddressDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBranchRequest {

    @NotEmpty(message = "Branch Name cannot be empty")
    @NotNull(message = "Branch Name cannot be null")
    @NotBlank(message = "Branch Name cannot be blank")
    @Size(max = 100, message = "Only 100 characters allowed for branch name")
    private String name;

    @NotNull
    private AddressDto physicalAddress;

    @Email(message = "Invalid Email")
    @Size(max = 50, message = "Only 100 characters allowed for Email.")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Size(max = 50, message = "Only 50 characters allowed for Fax.")
    private String faxNo;

    @Size(max = 50, message = "Only 50 characters allowed for Land Line.")
    @NotEmpty(message = "Land Line cannot be empty")
    @NotNull(message = "Land Line cannot be null")
    @NotBlank(message = "Land Line cannot be blank")
    private String landLine;
}
