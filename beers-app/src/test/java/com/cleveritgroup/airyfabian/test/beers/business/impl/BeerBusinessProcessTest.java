package com.cleveritgroup.airyfabian.test.beers.business.impl;

import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.entity.BeerEntity;
import com.cleveritgroup.airyfabian.test.beers.exception.DuplicateIdException;
import com.cleveritgroup.airyfabian.test.beers.exception.InvalidRequestException;
import com.cleveritgroup.airyfabian.test.beers.exception.NotFoundException;
import com.cleveritgroup.airyfabian.test.beers.mock.object.DtoMockObject;
import com.cleveritgroup.airyfabian.test.beers.mock.object.EntityMockObject;
import com.cleveritgroup.airyfabian.test.beers.repository.IBeerRepository;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeerBusinessProcessTest {

    @Autowired
    private BeerBusinessProcess process;

    @MockBean
    private IBeerRepository beerRepository;


    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void getAllBeer() {
        Mockito.when( beerRepository.findAll() )
                .thenReturn(Arrays.asList(
                    EntityMockObject.getBeerEntity(1, "Beer1", new BigDecimal("2")),
                    EntityMockObject.getBeerEntity(2, "Beer3", new BigDecimal("3")),
                    EntityMockObject.getBeerEntity(3, "Beer3", new BigDecimal("2.5"))
                ) );

        List<BeerDTO> beers = process.getAllBeer();
        Assert.assertNotNull(beers);
        Assert.assertTrue( beers.size() > 0 );
    }

    @org.junit.Test
    public void createBeer_success() throws DuplicateIdException, NotFoundException, InvalidRequestException {
        BeerDTO beer = DtoMockObject.getBeerDTO();
        String response = process.createBeer(beer);
        Assert.assertEquals(response, "Cerveza creada");
    }

    @org.junit.Test
    public void createBeer_duplicate()  {
        BeerDTO beer = DtoMockObject.getBeerDTO();
        String response = null;
        Optional<BeerEntity> entityOptional = EntityMockObject.getBeerEntity();
        Mockito.when( beerRepository.findById( beer.getId() ) ).thenReturn( entityOptional );
        try {
            response = process.createBeer(beer);
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateIdException e) {
            Assert.assertEquals(e.getMessage(), "El ID de la cerveza ya existe");
        }

    }

    @org.junit.Test
    public void getBeerById() throws NotFoundException {
        Integer id = 1;
        Optional<BeerEntity> entityOptional = EntityMockObject.getBeerEntity();
        Mockito.when( beerRepository.findById(id) ).thenReturn( entityOptional );
        BeerDTO beer = process.getBeerById( id );
        Assert.assertNotNull(beer);
        Assert.assertEquals(entityOptional.get().getId(), beer.getId());
    }

    @org.junit.Test
    public void getBoxPriceTotal_success() throws NotFoundException {
        Integer id = 1;
        String currencyParam = "EUR";
        Integer quantity=2;
        Optional<BeerEntity> entityOptional = EntityMockObject.getBeerEntity();
        Mockito.when( beerRepository.findById(id) ).thenReturn( entityOptional );
        BigDecimal result = process.getBoxPriceTotal(id, currencyParam, quantity);
        Assert.assertNotNull(result);
    }

    @org.junit.Test
    public void getBoxPriceTotal_dollarToDollar_success() throws NotFoundException {
        Integer id = 1;
        String currencyParam = "USD";
        Integer quantity=2;
        Optional<BeerEntity> entityOptional = EntityMockObject.getBeerEntity();
        Mockito.when( beerRepository.findById(id) ).thenReturn( entityOptional );
        BigDecimal result = process.getBoxPriceTotal(id, currencyParam, quantity);
        Assert.assertNotNull(result);
    }
}