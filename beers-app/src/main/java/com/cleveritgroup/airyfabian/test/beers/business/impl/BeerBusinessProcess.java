package com.cleveritgroup.airyfabian.test.beers.business.impl;

import com.cleveritgroup.airyfabian.test.beers.business.IBeerBusinessProcess;
import com.cleveritgroup.airyfabian.test.beers.currency.Currency;
import com.cleveritgroup.airyfabian.test.beers.dto.BeerDTO;
import com.cleveritgroup.airyfabian.test.beers.entity.BeerEntity;
import com.cleveritgroup.airyfabian.test.beers.exception.DuplicateIdException;
import com.cleveritgroup.airyfabian.test.beers.exception.InvalidRequestException;
import com.cleveritgroup.airyfabian.test.beers.exception.NotFoundException;
import com.cleveritgroup.airyfabian.test.beers.mapper.IDtoToEntityMapper;
import com.cleveritgroup.airyfabian.test.beers.mapper.IEntityToDtoMapper;
import com.cleveritgroup.airyfabian.test.beers.repository.IBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BeerBusinessProcess implements IBeerBusinessProcess {

    private final static Logger log = Logger.getLogger(BeerBusinessProcess.class.getName());

    @Autowired
    private IBeerRepository beerRepository;

    @Autowired
    private IEntityToDtoMapper entityToDtoMapper;

    @Autowired
    private IDtoToEntityMapper dtoToEntityMapper;

    /**
     * Return all the beer inside of database
     *
     * @return the all beer
     */
    @Override
    public List<BeerDTO> getAllBeer() {
        List<BeerDTO> beers = new ArrayList<BeerDTO>();
        beerRepository.findAll()
                .forEach( entity -> beers.add( entityToDtoMapper.getBeerEnToDto( entity ) ) );
        log.info("the list has "+ beers.size()+ " elements.");
        return beers;
    }

    /**
     * Create beer string.
     *
     * @param dto the dto
     * @return the string
     * @throws InvalidRequestException the invalid request exception
     * @throws NotFoundException       the not found exception
     * @throws DuplicateIdException    the duplicate id exception
     */
    @Override
    public String createBeer(BeerDTO dto) throws InvalidRequestException, NotFoundException, DuplicateIdException {
        avoidDuplicateId(dto);
        BeerEntity entity = dtoToEntityMapper.getBeerDtoToEn(dto);
        beerRepository.save(entity);
        return "Cerveza creada";
    }

    private void avoidDuplicateId(BeerDTO dto) throws DuplicateIdException{
        if(beerRepository.findById(dto.getId()).isPresent()){
            log.info("method duplicate throw an exception");
            throw new DuplicateIdException("El ID de la cerveza ya existe");
        }
    }

    private BeerDTO searchById(Integer id) throws NotFoundException {
        return beerRepository.findById(id)
                .map(entity1 -> {
                    log.info("method search by id has a result");
                    return entityToDtoMapper.getBeerEnToDto(entity1);
                })
                .orElseThrow( () -> new NotFoundException("El Id de la cerveza no existe") );
    }


    /**
     * Gets beer by id.
     *
     * @param id the id
     * @return the beer by id
     * @throws NotFoundException the not found exception
     */
    @Override
    public BeerDTO getBeerById(Integer id) throws NotFoundException {
        return searchById( id );
    }

    /**
     * Gets box price total.
     *
     * @param id       the id
     * @param currencyParam the currency
     * @param quantity the quantity
     * @return the box price total
     * @throws NotFoundException the not found exception
     */
    @Override
    public BigDecimal getBoxPriceTotal(Integer id, String currencyParam, Integer quantity) throws NotFoundException {
        BeerDTO beerDTO = searchById(id);
        Currency currency = Currency.valueOf(currencyParam);
        BigDecimal localCoin = beerDTO.getPrice().multiply( new BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP);
        log.info("buy: "+quantity+", price: "+beerDTO.getPrice()+", total: "+localCoin);
        if(currency.compareTo( beerDTO.getCurrency() ) == 0){
            log.info("is local price.");
            return localCoin;
        }
        if (currency.compareTo( Currency.USD )==0){
            return getAmountDollar(localCoin, beerDTO.getCurrency().getAmount());
        }
        return getAmountDollar(localCoin, beerDTO.getCurrency().getAmount() ).multiply( currency.getAmount() ).setScale( 2, RoundingMode.HALF_UP);
    }

    private BigDecimal getAmountDollar(BigDecimal amount, BigDecimal change){
        log.info("convert: "+amount+", dollar: "+change);
        return amount.divide( change , 2, RoundingMode.HALF_UP );
    }
}
