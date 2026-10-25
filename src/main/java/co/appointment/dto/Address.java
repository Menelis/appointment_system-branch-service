package co.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String suburb;
    private CityDTO city;
    private ProvinceDTO province;
    private String postalCode;
}
