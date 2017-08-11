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

    private List<Brand> brands = new ArrayList<>();

    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> getBrandsWithBrandName(String brandName) {
        if(brandName != null) {
            Brand brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);
            if(brand != null)
                this.brands.add(brand);
        }

        if(this.brands.isEmpty())
            this.brands = this.brandRepository.findAll();

        return brands;
    }


}
