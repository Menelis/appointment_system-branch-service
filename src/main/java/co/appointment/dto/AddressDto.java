package co.appointment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Data;

@Data
public class AddressDto {

    @Size(max = 255, message = "Only 255 characters allowed for Address Line 1")
    private String addressLine1;

    @Size(max = 255, message = "Only 255 characters allowed for Address Line 2")
    private String addressLine2;

    @Size(max = 50, message = "Only 50 characters allowed for Suburb")
    private String suburb;

    @NotNull(message = "CityId cannot be null")
    private UUID cityId;

    @NotNull(message = "ProvinceId cannot be null")
    private UUID provinceId;

    @NotEmpty(message = "Postal Code cannot be empty")
    @NotNull(message = "Postal Code cannot be null")
    @NotBlank(message = "Postal Code cannot be blank")
    @Size(max = 10, message = "Only 10 characters allowed for Postal Code")
    private String postalCode;
}
