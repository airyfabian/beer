package com.cleveritgroup.airyfabian.test.beers.repository;

import com.cleveritgroup.airyfabian.test.beers.entity.BeerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBeerRepository extends CrudRepository<BeerEntity, Integer> {
}
