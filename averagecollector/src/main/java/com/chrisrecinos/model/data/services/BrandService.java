package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.Brand;
import com.chrisrecinos.model.data.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Service
public class BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getBrandsWithBrandName(String brandName) {
        List<Brand> brands = new ArrayList<>();

        if(brandName != null) {
            Brand brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);
            if(brand != null)
                brands.add(brand);
        }

        if(brands.isEmpty())
            brands = this.brandRepository.findAll();

        return brands;
    }


}
