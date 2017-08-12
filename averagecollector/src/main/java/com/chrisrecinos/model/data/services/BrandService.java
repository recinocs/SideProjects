package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.Brand;
import com.chrisrecinos.model.data.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 *
 * This class handles all of the business logic for
 * the Brand Controller
 */

@Service
public class BrandService {

    private BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }


    /**
     * This method handles all of the business logic behind finding as accurate
     * search results as possible.
     *
     * If a parameter is entered, i.e. brandName isn't null, the method tries to grab
     * the associated brand with the brandName. If no such brand exists, the method
     * then tries to grab all brands that starting with the same letter as the entered
     * brand name. If there are still no results, the method returns all brands.
     *
     * @param brandName - the brandName being searched for
     * @return - A List of Brands based on what brandName was submitted
     */
    public List<Brand> getBrands(String brandName) {
        List<Brand> brands = new ArrayList<>();

        if(brandName != null) {
            Brand brand = getBrandWithBrandName(brandName);
            if(brand != null)
                brands.add(brand);
            else {
                brands = getBrandsStartingWith(brandName);
            }
        }

        if(brands.isEmpty())
            brands = this.brandRepository.findAllByOrderByBrandNameAsc();

        return brands;
    }

    private Brand getBrandWithBrandName(String brandName) {
        return this.brandRepository.findByBrandNameIgnoreCase(brandName);
    }

    private List<Brand> getBrandsStartingWith(String brandName) {
        return this.brandRepository.findByBrandNameIgnoreCaseStartingWithOrderByBrandNameAsc(brandName.charAt(0));
    }


}
