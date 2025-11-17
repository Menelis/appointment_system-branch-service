package co.appointment.payload.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class NewBranchRequest {

    @NotEmpty(message = "Branch Name cannot be empty")
    @NotNull(message = "Branch Name cannot be null")
    @NotBlank(message = "Branch Name cannot be blank")
    @Size(max = 100, message = "Only 100 characters allowed for branch name")
    private String name;


    @NotEmpty(message = "Street Number cannot be empty")
    @NotNull(message = "Street Number cannot be null")
    @NotBlank(message = "Street Number cannot be blank")
    @Size(max = 100, message = "Only 100 characters allowed for Street Number")
    private String streetNo;

    @NotEmpty(message = "Address Line 1 cannot be empty")
    @NotNull(message = "Address Line 1 cannot be null")
    @NotBlank(message = "Address Line 1 cannot be blank")
    @Size(max = 255, message = "Only 255 characters allowed for Address Line 1")
    private String addressLine1;


    @NotEmpty(message = "Address Line 2 cannot be empty")
    @NotNull(message = "Address Line 2 cannot be null")
    @NotBlank(message = "Address Line 1 cannot be blank")
    @Size(max = 255, message = "Only 255 characters allowed for Address Line 2")
    private String addressLine2;

    @NotEmpty(message = "City cannot be empty")
    @NotNull(message = "City cannot be null")
    @Size(max = 100, message = "Only 100 characters allowed for City")
    private String city;


    @NotEmpty(message = "Province cannot be empty")
    @NotNull(message = "Province cannot be null")
    @NotBlank(message = "Province cannot be blank")
    @Size(max = 100, message = "Only 100 characters allowed for Province")
    private String province;

    @NotEmpty(message = "Postal Code cannot be empty")
    @NotNull(message = "Postal Code cannot be null")
    @NotBlank(message = "Postal Code cannot be blank")
    @Size(max = 10, message = "Only 10 characters allowed for Postal Code")
    private String postalCode;

    @Email(message = "Invalid Email")
    @Size(max = 100, message = "Only 100 characters allowed for Email.")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Size(max = 50, message = "Only 50 characters allowed for Fax.")
    @NotEmpty(message = "Fax cannot be empty")
    @NotNull(message = "Fax cannot be null")
    @NotBlank(message = "Fax cannot be blank")
    private String faxNo;

    @Size(max = 50, message = "Only 50 characters allowed for Land Line.")
    @NotEmpty(message = "Land Line cannot be empty")
    @NotNull(message = "Land Line cannot be null")
    @NotBlank(message = "Land Line cannot be blank")
    private String landLine;
}
