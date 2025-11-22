package co.appointment.dto;

import lombok.Data;

@Data
public class CityDTO {
    private int id;
    private String name;
    private ProvinceDTO province;
}
