package com.cleveritgroup.airyfabian.test.beers.entity;

import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * The type Beer entity.
 */
@Entity
@Getter
@Setter
@Table(name = "beers")
public class BeerEntity {

    @Id
    private Integer id;

    private String name;

    private String brewery;

    private String country;

    private BigDecimal price;

    private Currency currency;

    @Override
    public String toString() {
        return "BeerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brewery='" + brewery + '\'' +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
