package com.cleveritgroup.airyfabian.test.beers.request;

import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BeerRequest {

    @JsonProperty("Id")
    private Integer id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Brewery")
    private String brewery;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Price")
    private BigDecimal price;

    @JsonProperty("Currency")
    private Currency currency;

    @PostConstruct
    private void ini(){
        if(null == currency){
            currency = Currency.USD;
        }
    }

    @Override
    public String toString() {
        return "BeerRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brewery='" + brewery + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
