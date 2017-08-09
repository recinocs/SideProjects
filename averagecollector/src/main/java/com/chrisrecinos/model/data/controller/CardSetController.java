package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.repository.BrandRepository;
import com.chrisrecinos.model.data.repository.CardSetRepository;
import com.chrisrecinos.model.data.entity.Brand;

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
public class CardSetController {

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private BrandRepository brandRepository;

    @RequestMapping(value = "/sets", method = RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) String brandName,
                               @RequestParam(required = false) String setName) {
        String results = "";
        Brand brand = null;
        List<CardSet> cardSets = new ArrayList<>();

        if(brandName != null)
            brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);

        if(setName != null && brand != null) {
            CardSet cardSet = this.cardSetRepository.findByBrandAndSetNameIgnoreCase(brand, setName);
            if(cardSet != null)
                cardSets.add(cardSet);
        } else if(setName != null) {
            cardSets = this.cardSetRepository.findBySetNameIgnoreCase(setName);
        } else if(brand != null) {
            cardSets = this.cardSetRepository.findByBrand(brand);
        }

        if(cardSets.size() == 0)
            cardSets = this.cardSetRepository.findAll();

        for(CardSet set : cardSets) {
            results += set + "<br/>";
        }

        return results;
    }
}
