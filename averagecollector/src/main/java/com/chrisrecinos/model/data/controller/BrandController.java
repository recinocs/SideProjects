package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Brand;
import com.chrisrecinos.model.data.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 */

@RestController
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;

    @RequestMapping(value = "/brands", method = RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) String brandName) {
        String results = "";
        List<Brand> brandList = new ArrayList<>();

        if(brandName == null) {
            brandList = this.brandRepository.findAll();
        } else {
            Brand brand = this.brandRepository.findByBrandName(brandName);
            if(brand != null)
                brandList.add(brand);
        }

        for(Brand b : brandList) {
            results = results + b + "<br>";
        }
        return results;
    }
}
