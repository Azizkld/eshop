package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.entities.ServiceName;
import com.billcom.eshop.commons.dtos.ServiceNameDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServiceNameMapper {
    ServiceName toEntity(ServiceNameDto serviceNameDto);

    ServiceNameDto toDto(ServiceName serviceName);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ServiceName partialUpdate(ServiceNameDto serviceNameDto, @MappingTarget ServiceName serviceName);}

