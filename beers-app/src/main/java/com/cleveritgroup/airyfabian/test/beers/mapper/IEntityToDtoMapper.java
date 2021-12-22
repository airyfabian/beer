package com.cleveritgroup.airyfabian.test.beers.mapper;

import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.entity.BeerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IEntityToDtoMapper {
    IEntityToDtoMapper MAPPER = Mappers.getMapper(IEntityToDtoMapper.class);
    BeerDTO getBeerEnToDto(BeerEntity entity);
}
