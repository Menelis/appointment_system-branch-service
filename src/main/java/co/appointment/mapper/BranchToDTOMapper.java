package co.appointment.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CityMapper.class, ProvinceMapper.class})
public interface BranchToDTOMapper {
    /*BranchDTO toDTO(Branch branch);

    @Mapping(target = "province", source = "provinceId")
    @Mapping(target = "city", source = "cityId")
    Branch toEntity(NewBranchRequest newBranchRequest);

    @Mapping(target = "province", source = "provinceId")
    @Mapping(target = "city", source = "cityId")
    Branch toEntity(UpdateBranchRequest updateBranchRequest);*/
}
