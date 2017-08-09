package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.Brand;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Repository
public interface CardSetRepository extends CrudRepository<CardSet, Long> {
    CardSet findByBrandAndSetNameIgnoreCase(Brand brand, String setName);
    List<CardSet> findBySetNameIgnoreCase(String setName);
    List<CardSet> findByBrand(Brand brand);
    List<CardSet> findAll();
}
