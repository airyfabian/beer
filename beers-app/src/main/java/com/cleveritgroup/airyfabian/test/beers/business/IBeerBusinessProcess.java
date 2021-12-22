package com.cleveritgroup.airyfabian.test.beers.business;

import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.exception.DuplicateIdException;
import com.cleveritgroup.airyfabian.test.beers.exception.InvalidRequestException;
import com.cleveritgroup.airyfabian.test.beers.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.List;

/**
 * The interface Beer business process.
 */
public interface IBeerBusinessProcess {

    /**
     * Return all the beer inside of database
     *
     * @return the all beer
     */
    List<BeerDTO> getAllBeer();


    /**
     * Create beer string.
     *
     * @param dto the dto
     * @return the string
     * @throws InvalidRequestException the invalid request exception
     * @throws NotFoundException       the not found exception
     * @throws DuplicateIdException    the duplicate id exception
     */
    String createBeer(BeerDTO dto) throws InvalidRequestException, NotFoundException, DuplicateIdException;

    /**
     * Gets beer by id.
     *
     * @param id the id
     * @return the beer by id
     * @throws NotFoundException the not found exception
     */
    BeerDTO getBeerById(Integer id) throws NotFoundException;

    /**
     * Gets box price total.
     *
     * @param id       the id
     * @param currencyParam the currency
     * @param quantity the quantity
     * @return the box price total
     * @throws NotFoundException the not found exception
     */
    BigDecimal getBoxPriceTotal(Integer id, String currencyParam, Integer quantity) throws NotFoundException;
}
