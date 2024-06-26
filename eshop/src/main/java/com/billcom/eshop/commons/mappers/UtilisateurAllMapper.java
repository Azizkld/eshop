package com.billcom.eshop.commons.mappers;

import com.billcom.eshop.commons.entities.UtilisateurAll;
import com.billcom.eshop.commons.dtos.UtilisateurAllDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UtilisateurAllMapper {
    UtilisateurAll toEntity(UtilisateurAllDto utilisateurAllDto);

    UtilisateurAllDto toDto(UtilisateurAll utilisateurAll);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UtilisateurAll partialUpdate(UtilisateurAllDto utilisateurAllDto, @MappingTarget UtilisateurAll utilisateurAll);
}