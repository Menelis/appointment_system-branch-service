package co.appointment.mapper;

import co.appointment.dto.BranchDTO;
import co.appointment.entity.Branch;
import co.appointment.payload.request.NewBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BranchToDTOMapper {
    BranchDTO toDTO(Branch branch);
    Branch toEntity(NewBranchRequest newBranchRequest);
    Branch toEntity(UpdateBranchRequest updateBranchRequest);
}
