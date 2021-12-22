package com.cleveritgroup.airyfabian.test.beers.mock.object;

import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import com.cleveritgroup.airyfabian.test.beers.entity.BeerEntity;

import java.math.BigDecimal;
import java.util.Optional;

public class EntityMockObject {
    public static BeerEntity getBeerEntity(Integer id, String name, BigDecimal price){
        BeerEntity entity = new BeerEntity();
        entity.setId(id);
        entity.setPrice(price);
        entity.setName(name);
        return entity;
    }
    public static Optional<BeerEntity> getBeerEntity(){
        BeerEntity entity = new BeerEntity();
        entity.setId(1);
        entity.setName("BEER");
        entity.setBrewery("B B");
        entity.setCountry("USA");
        entity.setPrice(new BigDecimal("1.0"));
        entity.setCurrency( Currency.USD );
        return Optional.of(entity);
    }

}
