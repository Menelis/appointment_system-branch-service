package co.appointment.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class BranchDto {
    private UUID id;
    private String name;
    private String streetNo;
    private String addressLine1;
    private String addressLine2;
    private CityDTO city;
    private ProvinceDTO province;
    private String postalCode;
    private String email;
    private String faxNo;
    private String landLine;
}
