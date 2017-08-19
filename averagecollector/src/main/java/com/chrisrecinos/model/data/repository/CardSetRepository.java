package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.Brand;

import com.chrisrecinos.model.data.entity.CardYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Repository
public interface CardSetRepository extends CrudRepository<CardSet, Long> {
    CardSet findByCardYearAndBrandAndSetName(CardYear year, Brand brand, String setName);
    List<CardSet> findByBrandAndSetNameOrderByCardYearAsc(Brand brand, String setName);
    List<CardSet> findAllByOrderByCardYearAscBrandAscSetNameAsc();
}
