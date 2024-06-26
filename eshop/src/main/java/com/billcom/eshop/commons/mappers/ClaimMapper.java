package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.dtos.ClaimDto;
import com.billcom.eshop.commons.entities.Claim;
import com.billcom.eshop.commons.entities.ContractAll;
import com.billcom.eshop.commons.dtos.ContractAllDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClaimMapper {
    Claim toEntity(ClaimDto claimDto);

    ClaimDto toDto(Claim claim);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Claim partialUpdate(ClaimDto claimDto, @MappingTarget Claim claim);
}