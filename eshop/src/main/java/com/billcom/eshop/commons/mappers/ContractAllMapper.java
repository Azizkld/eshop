package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.dtos.ContractAllDto;
import com.billcom.eshop.commons.entities.ContractAll;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContractAllMapper {
    ContractAll toEntity(ContractAllDto contractAllDto);

    ContractAllDto toDto(ContractAll contractAll);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ContractAll partialUpdate(ContractAllDto contractAllDto, @MappingTarget ContractAll contractAll);
}