package co.appointment.mapper;

import co.appointment.dto.BranchDto;
import co.appointment.payload.request.CreateBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import co.appointment.persistence.BranchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {MapperHelper.class})
public interface BranchMapper {

    @Mapping(target = "city", source = "cityId")
    @Mapping(target = "province", source = "provinceId")
    BranchDto mapToDto(BranchEntity entity);

    @Mapping(target = "addressLine1", source = "physicalAddress.addressLine1")
    @Mapping(target = "addressLine2", source = "physicalAddress.addressLine2")
    @Mapping(target = "suburb", source = "physicalAddress.suburb")
    @Mapping(target = "cityId", source = "physicalAddress.cityId")
    @Mapping(target = "provinceId", source = "physicalAddress.provinceId")
    @Mapping(target = "postalCode", source = "physicalAddress.postalCode")
    BranchEntity mapToEntity(CreateBranchRequest request);

    @Mapping(target = "addressLine1", source = "physicalAddress.addressLine1")
    @Mapping(target = "addressLine2", source = "physicalAddress.addressLine2")
    @Mapping(target = "suburb", source = "physicalAddress.suburb")
    @Mapping(target = "cityId", source = "physicalAddress.cityId")
    @Mapping(target = "provinceId", source = "physicalAddress.provinceId")
    @Mapping(target = "postalCode", source = "physicalAddress.postalCode")
    BranchEntity mapToEntity(UpdateBranchRequest request);
}
