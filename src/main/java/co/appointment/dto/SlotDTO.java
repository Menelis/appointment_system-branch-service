package co.appointment.dto;

import lombok.Data;

@Data
public class SlotDTO {
    private Integer id;
    private String slotStart;
    private String slotEnd;
}
