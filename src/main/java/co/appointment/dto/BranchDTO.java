package co.appointment.dto;

import lombok.Data;

@Data
public class BranchDTO {
    private int id;
    private String name;
    private String streetNo;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String postalCode;
}
