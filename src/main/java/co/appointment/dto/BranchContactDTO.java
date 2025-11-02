package co.appointment.dto;

import co.appointment.entity.ContactType;
import lombok.Data;

@Data
public class BranchContactDTO {
    private Long id;
    private String contact;
    private BranchDTO branch;
    private ContactType contactType;
}
