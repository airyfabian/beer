package com.cleveritgroup.airyfabian.test.beers.mapper;

import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.request.BeerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IDtoToRequest {
    IDtoToRequest MAPPER =  Mappers.getMapper(IDtoToRequest.class);
    BeerRequest getDtoToRequest(BeerDTO dto);
}
