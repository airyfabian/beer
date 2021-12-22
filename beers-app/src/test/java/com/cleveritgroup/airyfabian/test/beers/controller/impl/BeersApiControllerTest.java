package com.cleveritgroup.airyfabian.test.beers.controller.impl;

import com.cleveritgroup.airyfabian.test.beers.business.IBeerBusinessProcess;
import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.exception.DuplicateIdException;
import com.cleveritgroup.airyfabian.test.beers.exception.InvalidRequestException;
import com.cleveritgroup.airyfabian.test.beers.exception.NotFoundException;
import com.cleveritgroup.airyfabian.test.beers.mock.object.DtoMockObject;
import com.cleveritgroup.airyfabian.test.beers.request.BeerRequest;
import org.h2.command.dml.MergeUsing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeersApiControllerTest {

    @Autowired
    private BeersApiController controller;

    @MockBean
    private IBeerBusinessProcess businessProcess;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getBeer_success() throws NotFoundException {
        Integer id = 1;
        Mockito.when(businessProcess.getBeerById(id)).thenReturn( DtoMockObject.getBeerDTO() );
        ResponseEntity response = controller.getBeer(id);
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(), HttpStatus.OK );
    }

    @Test
    public void getBeer_notFoundException() {
        Integer id = 1;
        String message = "El Id de la cerveza no existe";
        try {
            Mockito.when(businessProcess.getBeerById(id)).thenThrow( new NotFoundException(message) );
            ResponseEntity response = controller.getBeer(id);
        } catch (NotFoundException e) {
            Assert.assertEquals( e.getMessage(), message );
        }
    }

    @Test
    public void getBeers_success() {

        List<BeerDTO> beers = new ArrayList<BeerDTO>();
        beers.add( DtoMockObject.getBeerDTO() );
        Mockito.when(businessProcess.getAllBeer()).thenReturn(beers);
        ResponseEntity response  = controller.getBeers();
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(), HttpStatus.OK );
        List<BeerRequest> request = (List<BeerRequest>) response.getBody();
        Assert.assertEquals( request.get(0).getName(), beers.get(0).getName() );
    }

    @Test
    public void getBeerCreate_success() throws DuplicateIdException, NotFoundException, InvalidRequestException {
        String message = "Cerveza creada";
        Mockito.when(businessProcess.createBeer(Mockito.any()))
                .thenReturn(message);

        BeerRequest beer = new BeerRequest();
        beer.setId(1);
        beer.setName("NAME");
        beer.setBrewery("BREWEY");
        beer.setCountry("COUNTRY");
        beer.setPrice(new BigDecimal("2"));
        beer.setCurrency( Currency.USD );

        ResponseEntity response = controller.getBeerCreate(beer);
        Assert.assertNotNull(response);
        Assert.assertEquals( response.getStatusCode(), HttpStatus.CREATED );
        Assert.assertEquals( response.getBody(), message );
    }

    @Test
    public void getBeerCreate_duplicateIdException(){
        String message = "Duplicate";
        try {
            Mockito.when(businessProcess.createBeer(Mockito.any()))
                    .thenThrow(new DuplicateIdException(message));
            BeerRequest beer = new BeerRequest();
            beer.setId(1);
            beer.setName("NAME");
            beer.setBrewery("BREWEY");
            beer.setCountry("COUNTRY");
            beer.setPrice(new BigDecimal("2"));
            beer.setCurrency( Currency.USD );

            ResponseEntity response = controller.getBeerCreate(beer);

        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateIdException e) {
            Assert.assertEquals(e.getMessage(), message);
        }
    }

    @Test
    public void getBeerCreate_invalidRequestException(){
        String message = "Sent Invalid Request";
        try {
            Mockito.when(businessProcess.createBeer(Mockito.any()))
                    .thenThrow(new InvalidRequestException(message));
            BeerRequest beer = new BeerRequest();
            beer.setId(1);
            beer.setName("NAME");
            beer.setBrewery("BREWEY");
            beer.setCountry("COUNTRY");
            beer.setPrice(new BigDecimal("2"));
            beer.setCurrency( Currency.USD );

            ResponseEntity response = controller.getBeerCreate(beer);

        } catch (InvalidRequestException e) {
            Assert.assertEquals(e.getMessage(), message);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateIdException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPriceBox_success() throws NotFoundException {
        Integer id=1;
        String currency = "USD";
        Integer quantity = 1;

        BigDecimal amountRes = new BigDecimal("20.36");

        Mockito.when( businessProcess.getBoxPriceTotal(id, currency, quantity) )
                .thenReturn( amountRes );

        ResponseEntity entity = controller.getPriceBox(id, currency, quantity);
        String tmpAmount = entity.getBody().toString().split("=")[1];
        tmpAmount = tmpAmount.substring(0, tmpAmount.length()-1);
        Assert.assertNotNull(entity);
        Assert.assertEquals( amountRes, new BigDecimal(tmpAmount) );
    }

    @Test
    public void getPriceBox_notFoundException()  {
        Integer id=1;
        String currency = "USD";
        Integer quantity = 1;
        String message = "Id does not found it";
        try {
            Mockito.when( businessProcess.getBoxPriceTotal(id, currency, quantity) )
                    .thenThrow( new NotFoundException(message));
            ResponseEntity entity = controller.getPriceBox(id, currency, quantity);
        } catch (NotFoundException e) {
            Assert.assertEquals(e.getMessage(), message);
        }


    }
}