package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.CardYear;
import com.chrisrecinos.model.data.entity.Brand;
import com.chrisrecinos.model.data.repository.BrandRepository;
import com.chrisrecinos.model.data.repository.CardSetRepository;
import com.chrisrecinos.model.data.repository.CardYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Service
public class CardSetService {

    private CardSetRepository cardSetRepository;
    private CardYearRepository cardYearRepository;
    private BrandRepository brandRepository;

    @Autowired
    public CardSetService(CardSetRepository cardSetRepository, CardYearRepository cardYearRepository,
                          BrandRepository brandRepository) {
        this.cardSetRepository = cardSetRepository;
        this.cardYearRepository = cardYearRepository;
        this.brandRepository = brandRepository;
    }

    public List<CardSet> getSets(Integer year, String brandName, String setName) {
        List<CardSet> cardSets = new ArrayList<>();

        CardYear cardYear = null;
        Brand brand = null;

        if(year != null)
            cardYear = this.cardYearRepository.findByCardYear(year);

        if(brandName != null)
            brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);

        if(cardYear != null && brand != null && setName != null) {
            CardSet cardSet = this.cardSetRepository.findByCardYearAndBrandAndSetName(cardYear, brand, setName);
            if(cardSet != null)
                cardSets.add(cardSet);
        }

        if(cardSets.isEmpty()) {
            if(brand != null && setName != null)
                cardSets = this.cardSetRepository.findByBrandAndSetNameOrderByCardYearAsc(brand, setName);
        }

        if(cardSets.isEmpty()) {
            cardSets = this.cardSetRepository.findAllByOrderByCardYearAscBrandAscSetNameAsc();
        }

        return cardSets;
    }
}
