package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.Brand;

import com.chrisrecinos.model.data.entity.CardYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author - Christopher Recinos
 *
 */

//TODO - Add searching for first character in addition to LIKE

@Repository
public interface CardSetRepository extends CrudRepository<CardSet, Long> {
    CardSet findByCardYearAndBrandAndSetNameIgnoreCase(CardYear year, Brand brand, String setName);
    List<CardSet> findByCardYearAndBrandAndSetNameIgnoreCaseStartingWithOrderBySetNameAsc(CardYear year, Brand brand, String setName);
    List<CardSet> findByBrandAndSetNameIgnoreCaseOrderByCardYearAsc(Brand brand, String setName);
    List<CardSet> findByBrandAndSetNameIgnoreCaseStartingWithOrderByCardYearAscSetNameAsc(Brand brand, String setName);
    List<CardSet> findByCardYearAndBrandOrderBySetNameAsc(CardYear year, Brand brand);
    List<CardSet> findByCardYearAndSetNameIgnoreCaseOrderByBrand(CardYear year, String setName);
    List<CardSet> findByCardYearAndSetNameIgnoreCaseStartingWithOrderByBrandAscSetNameAsc(CardYear year, String setName);
    List<CardSet> findByBrandOrderByCardYearAscSetNameAsc(Brand brand);
    List<CardSet> findBySetNameIgnoreCaseOrderByCardYearAscBrandAsc(String setName);
    List<CardSet> findBySetNameIgnoreCaseStartingWithOrderByCardYearAscBrandAscSetNameAsc(String setName);
    List<CardSet> findByCardYearOrderByBrandAscSetNameAsc(CardYear year);
    List<CardSet> findAllByOrderByCardYearAscBrandAscSetNameAsc();
}
