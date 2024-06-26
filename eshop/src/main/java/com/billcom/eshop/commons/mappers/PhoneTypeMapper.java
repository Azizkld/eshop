package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.entities.PhoneType;
import com.billcom.eshop.commons.dtos.PhoneTypeDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhoneTypeMapper {
    PhoneType toEntity(PhoneTypeDto phoneTypeDto);

    PhoneTypeDto toDto(PhoneType phoneType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PhoneType partialUpdate(PhoneTypeDto phoneTypeDto, @MappingTarget PhoneType phoneType);
}