package co.appointment.mapper;

import co.appointment.dto.BranchDTO;
import co.appointment.entity.Branch;
import co.appointment.payload.request.NewBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
     uses = { CityMapper.class, ProvinceMapper.class })
public interface BranchToDTOMapper {
    BranchDTO toDTO(Branch branch);

    @Mapping(target = "province", source = "provinceId")
    @Mapping(target = "city", source = "cityId")
    Branch toEntity(NewBranchRequest newBranchRequest);

    @Mapping(target = "province", source = "provinceId")
    @Mapping(target = "city", source = "cityId")
    Branch toEntity(UpdateBranchRequest updateBranchRequest);
}
