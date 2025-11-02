package co.appointment.dto;

import lombok.Data;

@Data
public class BranchContactDTO {
    private Long id;
    private String contact;
    private BranchDTO branch;
    private ContactTypeDTO contactType;
}
