package co.appointment.mapper;

import co.appointment.dto.BranchContactDTO;
import co.appointment.entity.BranchContact;
import co.appointment.payload.request.NewBranchContactRequest;
import co.appointment.payload.request.UpdateBranchContactRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ContactTypeMapper.class, BranchMapper.class })
public interface BranchContactToDTOMapper {

    @Mapping(target = "branch", source = "branchId")
    @Mapping(target = "contactType", source = "contactTypeId")
    BranchContact toEntity(NewBranchContactRequest request);

    @Mapping(target = "branch", source = "branchId")
    @Mapping(target = "contactType", source = "contactTypeId")
    BranchContact toEntity(UpdateBranchContactRequest request);

    BranchContactDTO toDTO(BranchContact branchContact);
}
