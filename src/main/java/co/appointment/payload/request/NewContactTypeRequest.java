package co.appointment.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewContactTypeRequest {

    @NotEmpty(message = "Contact Type Name cannot not be empty")
    @NotBlank(message = "Contact Type Name cannot be blank")
    @NotNull(message = "Contact Type Name cannot be null")
    @Size(message = "Only 50 characters allowed for Contact Type Name", max = 50)
    private String name;
}
