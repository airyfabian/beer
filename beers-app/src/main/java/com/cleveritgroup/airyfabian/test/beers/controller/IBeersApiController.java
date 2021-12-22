package com.cleveritgroup.airyfabian.test.beers.controller;

import com.cleveritgroup.airyfabian.test.beers.exception.DuplicateIdException;
import com.cleveritgroup.airyfabian.test.beers.exception.InvalidRequestException;
import com.cleveritgroup.airyfabian.test.beers.exception.NotFoundException;
import com.cleveritgroup.airyfabian.test.beers.request.BeerRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Beers api controller.
 */
public interface IBeersApiController {

    /**
     * Gets beer.
     *
     * @param id the id
     * @return the beer
     * @throws NotFoundException the not found exception
     */
    ResponseEntity<?> getBeer(@PathVariable Integer id) throws NotFoundException;

    /**
     * Gets beers.
     *
     * @return the beers
     */
    ResponseEntity<?> getBeers();

    /**
     * Gets beer create.
     *
     * @param beer the beer
     * @return the beer create
     * @throws DuplicateIdException    the duplicate id exception
     * @throws NotFoundException       the not found exception
     * @throws InvalidRequestException the invalid request exception
     */
    ResponseEntity<?> getBeerCreate(@RequestBody BeerRequest beer) throws DuplicateIdException, NotFoundException, InvalidRequestException;

    /**
     * Gets price box.
     *
     * @param id       the id
     * @param currency the currency
     * @param quantity the quantity
     * @return the price box
     */
    ResponseEntity<?> getPriceBox(@PathVariable @NonNull Integer id,
                                  @RequestParam(value = "currency", required = true) String currency,
                                  @RequestParam(value = "quantity", required = false) Integer quantity) throws NotFoundException;
}
