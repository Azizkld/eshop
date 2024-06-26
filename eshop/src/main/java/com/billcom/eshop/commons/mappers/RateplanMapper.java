package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.Request.RateplanRequest;
import com.billcom.eshop.commons.entities.Rateplan;
import com.billcom.eshop.commons.dtos.RateplanDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RateplanMapper {
    Rateplan toEntity(RateplanRequest rateplanDto);

    RateplanDto toDto(Rateplan rateplan);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rateplan partialUpdate(RateplanDto rateplanDto, @MappingTarget Rateplan rateplan);
}