package com.cleveritgroup.airyfabian.test.beers.mock.object;

import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;

import java.math.BigDecimal;

public class DtoMockObject {

    public static BeerDTO getBeerDTO(){
        BeerDTO beer = new BeerDTO();
        beer.setCurrency( Currency.USD );
        beer.setPrice(new BigDecimal("12.3"));
        beer.setId(1);
        beer.setName("USD");
        return beer;
    }
}
