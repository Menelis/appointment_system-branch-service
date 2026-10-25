package co.appointment.mapper;

import co.appointment.dto.BranchDto;
import co.appointment.payload.request.CreateBranchRequest;
import co.appointment.payload.request.UpdateBranchRequest;
import co.appointment.persistence.BranchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {MapperHelper.class})
public interface BranchMapper {

    @Mapping(target = "physicalAddress.addressLine1", source = "physicalAddressLine1")
    @Mapping(target = "physicalAddress.addressLine2", source = "physicalAddressLine2")
    @Mapping(target = "physicalAddress.suburb", source = "physicalSuburb")
    @Mapping(target = "physicalAddress.postalCode", source = "physicalPostalCode")
    @Mapping(target = "physicalAddress.city", source = "physicalCityId")
    @Mapping(target = "physicalAddress.province", source = "physicalProvinceId")
    BranchDto mapToDto(BranchEntity entity);

    @Mapping(target = "physicalAddressLine1", source = "physicalAddress.addressLine1")
    @Mapping(target = "physicalAddressLine2", source = "physicalAddress.addressLine2")
    @Mapping(target = "physicalSuburb", source = "physicalAddress.suburb")
    @Mapping(target = "physicalCityId", source = "physicalAddress.cityId")
    @Mapping(target = "physicalProvinceId", source = "physicalAddress.provinceId")
    @Mapping(target = "physicalPostalCode", source = "physicalAddress.postalCode")
    BranchEntity mapToEntity(CreateBranchRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "physicalAddressLine1", source = "physicalAddress.addressLine1")
    @Mapping(target = "physicalAddressLine2", source = "physicalAddress.addressLine2")
    @Mapping(target = "physicalSuburb", source = "physicalAddress.suburb")
    @Mapping(target = "physicalCityId", source = "physicalAddress.cityId")
    @Mapping(target = "physicalProvinceId", source = "physicalAddress.provinceId")
    @Mapping(target = "physicalPostalCode", source = "physicalAddress.postalCode")
    void updateEntityFromRequest(UpdateBranchRequest request, @MappingTarget BranchEntity entity);
}
