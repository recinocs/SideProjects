package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.Brand;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Brand findByBrandNameIgnoreCase(String brandName);
    List<Brand> findAll();
}
