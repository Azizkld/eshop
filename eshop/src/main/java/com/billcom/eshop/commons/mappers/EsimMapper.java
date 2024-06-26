package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.dtos.EsimDto;
import com.billcom.eshop.commons.entities.Esim;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EsimMapper {
    Esim toEntity(EsimDto esimDto);

    EsimDto toDto(Esim esim);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Esim partialUpdate(EsimDto esimDto, @MappingTarget Esim esim);
}