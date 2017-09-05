package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Brand;
import com.chrisrecinos.model.data.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author - Christopher Recinos
 *
 * This class handles all of the communication with the front end
 * for the "/brands" endpoint.
 */

@Controller
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(method = RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) String brandName,
                               Model model) {
        List<Brand> brands = this.brandService.getBrands(brandName);

        model.addAttribute("brands", brands);

        return "brands";
    }

}
