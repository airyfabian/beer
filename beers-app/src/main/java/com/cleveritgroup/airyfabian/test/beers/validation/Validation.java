package com.cleveritgroup.airyfabian.test.beers.validation;

import com.cleveritgroup.airyfabian.test.beers.exception.InvalidRequestException;
import com.cleveritgroup.airyfabian.test.beers.request.BeerRequest;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * The type Validation.
 */
@Component
public class Validation {

    private final static Logger log = Logger.getLogger(Validation.class.getName());

    /**
     * Is valid request new beer.
     *
     * @param request the request
     * @throws InvalidRequestException the invalid request exception
     */
    public void isValidRequestNewBeer(BeerRequest request) throws InvalidRequestException {
        log.info("Reading the request object and its attributes");
        if(null == request){
            throw new InvalidRequestException("Request invalida");
        }
        if(null == request.getName() ||
            null == request.getId() ||
            null == request.getBrewery() ||
            null == request.getCountry() ||
            null == request.getCurrency() ||
            null == request.getPrice() ){
            throw new InvalidRequestException("Request invalida");
        }
    }

    /**
     * Check quantity value out integer.
     *
     * @param quantity the quantity
     * @return the integer
     */
    public Integer checkQuantityValueOut(Integer quantity) {
        return null == quantity ? 6 : quantity;
    }
}
