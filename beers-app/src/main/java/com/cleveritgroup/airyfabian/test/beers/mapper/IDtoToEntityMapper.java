package com.cleveritgroup.airyfabian.test.beers.mapper;

import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.entity.BeerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IDtoToEntityMapper {

    IDtoToEntityMapper MAPPER =  Mappers.getMapper(IDtoToEntityMapper.class);

    BeerEntity getBeerDtoToEn(BeerDTO dto);
}
