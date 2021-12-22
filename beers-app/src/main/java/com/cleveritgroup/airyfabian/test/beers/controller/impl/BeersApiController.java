package com.cleveritgroup.airyfabian.test.beers.controller.impl;

import com.cleveritgroup.airyfabian.test.beers.business.IBeerBusinessProcess;
import com.cleveritgroup.airyfabian.test.beers.controller.IBeersApiController;
import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.exception.DuplicateIdException;
import com.cleveritgroup.airyfabian.test.beers.exception.InvalidRequestException;
import com.cleveritgroup.airyfabian.test.beers.exception.NotFoundException;
import com.cleveritgroup.airyfabian.test.beers.mapper.IDtoToRequest;
import com.cleveritgroup.airyfabian.test.beers.mapper.IRequestToDto;
import com.cleveritgroup.airyfabian.test.beers.request.BeerRequest;
import com.cleveritgroup.airyfabian.test.beers.validation.Validation;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The type Beers api controller.
 */
@RestController
@RequestMapping("/api")
public class BeersApiController implements IBeersApiController {

    private final static Logger log = Logger.getLogger(BeersApiController.class.getName());

    @Autowired
    private IBeerBusinessProcess businessProcess;

    @Autowired
    private IDtoToRequest mapperDtoToRequest;

    @Autowired
    private IRequestToDto mapperRequestToDto;

    @Autowired
    private Validation validation;

    @Override
    @GetMapping("/beers/{beerID}")
    public ResponseEntity<?> getBeer(@PathVariable(value = "beerID") Integer id) throws NotFoundException {
        log.info("Start process to get one beer");
        BeerDTO dto = businessProcess.getBeerById(id);
        BeerRequest beerRequest = mapperDtoToRequest.getDtoToRequest(dto);
        log.info("End process to get one beer");
        return new ResponseEntity<>( beerRequest, HttpStatus.OK);
    }

    @Override
    @GetMapping("/beers")
    public ResponseEntity<?> getBeers() {
        log.info("Start process to get all the beers");
        List<BeerRequest> dtos = businessProcess.getAllBeer()
                .stream()
                .map(dto -> mapperDtoToRequest.getDtoToRequest(dto) )
                .collect(Collectors.toList());
        log.info("End process to get all the beers");
        return new ResponseEntity<>( dtos, HttpStatus.OK);
    }

    @Override
    @PostMapping("/beers")
    public ResponseEntity<?> getBeerCreate(@RequestBody @NonNull BeerRequest beer) throws DuplicateIdException, NotFoundException, InvalidRequestException {
        log.info("Start process to create a beer");
        validation.isValidRequestNewBeer(beer);
        BeerDTO dto = mapperRequestToDto.getBeerRequestToDto(beer);
        String response = businessProcess.createBeer(dto);
        log.info("End process to create a beer");
        return new ResponseEntity<>( response, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/beers/{beerID}/boxprice")
    public ResponseEntity<?> getPriceBox(@PathVariable(value = "beerID") Integer id,
                                         @RequestParam(value = "currency", required = true) String currency,
                                         @RequestParam(value = "quantity", required = false) Integer quantity) throws NotFoundException {
        log.info("Start process to get Price Box");
        quantity = validation.checkQuantityValueOut(quantity);
        BigDecimal pay = businessProcess.getBoxPriceTotal(id, currency, quantity);
        HashMap<String, String> map = new HashMap<>();
        map.put("Price Total", pay.toString());
        log.info("Price Total "+pay.toString()+"End process to get Price Box.");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
