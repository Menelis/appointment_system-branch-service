package co.appointment.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateSlotRequest extends NewSlotRequest {
    @NotBlank(message = "Slot Id cannot be blank")
    @NotEmpty(message = "Slot Id cannot be empty")
    @NotNull(message = "Slot Id cannot be null")
    private Integer id;
}
