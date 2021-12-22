package com.cleveritgroup.airyfabian.test.beers.dto;

import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import lombok.*;

import java.math.BigDecimal;

/**
 * The type Beer dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    private Integer id;

    private String name;

    private String brewery;

    private String country;

    private BigDecimal price;

    private Currency currency;

    @Override
    public String toString() {
        return "BeerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brewery='" + brewery + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                '}';
    }
}
