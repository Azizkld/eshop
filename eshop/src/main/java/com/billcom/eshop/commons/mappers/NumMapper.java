package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.entities.Num;
import com.billcom.eshop.commons.dtos.NumDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NumMapper {
    Num toEntity(NumDto numDto);

    NumDto toDto(Num num);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Num partialUpdate(NumDto numDto, @MappingTarget Num num);
}