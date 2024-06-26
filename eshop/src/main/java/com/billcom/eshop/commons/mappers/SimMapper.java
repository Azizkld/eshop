package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.dtos.SimDto;
import com.billcom.eshop.commons.entities.Sim;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SimMapper {
    Sim toEntity(SimDto simDto);

    SimDto toDto(Sim sim);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sim partialUpdate(SimDto simDto, @MappingTarget Sim sim);
}