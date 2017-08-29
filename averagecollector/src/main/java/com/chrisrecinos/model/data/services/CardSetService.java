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

    /**
     * 1. Year, brand, set
     * 2. Brand, set
     * 3. Year, set
     * 4. Year, brand
     * 5. Set
     * 6. Year
     * 7. Brand
     * @param year
     * @param brandName
     * @param setName
     * @return
     */
    public List<CardSet> getSets(Integer year, String brandName, String setName) {
        List<CardSet> cardSets = new ArrayList<>();
        boolean realYear = false, realBrand = false, realSetName = false;

        CardYear cardYear = null;
        Brand brand = null;

        if(year != null) {
            cardYear = this.cardYearRepository.findByCardYear(year);
            if(cardYear != null)
                realYear = true;
        }

        if(brandName != null && !brandName.equals("")) {
            brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);
            if(brand != null)
                realBrand = true;
        }

        if(setName != null && !setName.equals(""))
            realSetName = true;

        if(realYear && realBrand) {
            CardSet cardSet;

            if(realSetName)
                cardSet = getResultsWithYearAndBrandAndSetName(cardYear, brand, setName);
            else
                cardSet = getResultsWithYearAndBrandAndSetName(cardYear, brand, "Base Set");

            if(cardSet != null)
                cardSets.add(cardSet);

            if(cardSets.isEmpty() && realSetName) {
                cardSets = this.getResultsWithYearAndBrandAndSetNameSubstring(cardYear, brand, setName);
                if(cardSets.isEmpty())
                    cardSets = this.getResultsWithYearAndBrandAndSetNameFirstChar(cardYear, brand, setName);
            }
        }

        if(cardSets.isEmpty() && realBrand && realSetName) {
            cardSets = this.getResultsWithBrandAndSetName(brand, setName);
            if(cardSets.isEmpty()) {
                cardSets = this.getResultsWithBrandAndSetNameSubstring(brand, setName);
                if(cardSets.isEmpty())
                    cardSets = this.getResultsWithBrandAndSetNameFirstChar(brand, setName);
            }
        }

        if(cardSets.isEmpty() && realYear && realSetName) {
            cardSets = this.getResultsWithYearAndSetName(cardYear, setName);
            if(cardSets.isEmpty()) {
                cardSets = this.getResultsWithYearAndSetNameSubstring(cardYear, setName);
                if(cardSets.isEmpty())
                    cardSets = this.getResultsWithYearAndSetNameFirstChar(cardYear, setName);
            }
        }

        if(cardSets.isEmpty() && realBrand && realYear)
            cardSets = this.getResultsWithYearAndBrand(cardYear, brand);

        if(cardSets.isEmpty() && realSetName) {
            cardSets = this.getResultsWithSetName(setName);
            if(cardSets.isEmpty()) {
                cardSets = this.getResultsWithSetNameSubstring(setName);
                if(cardSets.isEmpty())
                    cardSets = this.getResultsWithSetNameFirstChar(setName);
            }
        }

        if(cardSets.isEmpty() && realYear)
            cardSets = this.getResultsWithYear(cardYear);

        if(cardSets.isEmpty() && realBrand)
            cardSets = this.getResultsWithBrand(brand);

        if(cardSets.isEmpty() && brandName != null && !brandName.equals("")) {
            cardSets = this.getResultsWithSetName(brandName);
            if(cardSets.isEmpty()) {
                cardSets = this.getResultsWithSetNameSubstring(brandName);
                if(cardSets.isEmpty())
                    cardSets = this.getResultsWithSetNameFirstChar(brandName);
            }
        }

        if(cardSets.isEmpty() && realSetName) {
            Brand reverseBrand = this.brandRepository.findByBrandNameIgnoreCase(setName);
            if(reverseBrand != null)
                cardSets = this.getResultsWithBrand(reverseBrand);
        }

        if(cardSets.isEmpty()) {
            cardSets = this.cardSetRepository.findAllByOrderByBrandAscSetNameAsc();
        }

        return cardSets;
    }

    private CardSet getResultsWithYearAndBrandAndSetName(CardYear year, Brand brand, String setName) {
        return this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, setName);
    }

    private List<CardSet> getResultsWithYearAndBrandAndSetNameSubstring(CardYear year, Brand brand, String setName) {
        return this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCaseStartingWithOrderBySetNameAsc(year, brand, setName);
    }

    private List<CardSet> getResultsWithYearAndBrandAndSetNameFirstChar(CardYear year, Brand brand, String setName) {
        return this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCaseStartingWithOrderBySetNameAsc(year, brand, setName.charAt(0));
    }

    private List<CardSet> getResultsWithBrandAndSetName(Brand brand, String setName) {
        return this.cardSetRepository.findByBrandAndSetNameIgnoreCaseOrderByCardYearAsc(brand, setName);
    }

    private List<CardSet> getResultsWithBrandAndSetNameSubstring(Brand brand, String setName) {
        return this.cardSetRepository.findByBrandAndSetNameIgnoreCaseStartingWithOrderBySetNameAsc(brand, setName);
    }

    private List<CardSet> getResultsWithBrandAndSetNameFirstChar(Brand brand, String setName) {
        return this.cardSetRepository.findByBrandAndSetNameIgnoreCaseStartingWithOrderBySetNameAsc(brand, setName.charAt(0));
    }

    private List<CardSet> getResultsWithYearAndSetName(CardYear year, String setName) {
        return this.cardSetRepository.findByCardYearAndSetNameIgnoreCaseOrderByBrand(year, setName);
    }

    private List<CardSet> getResultsWithYearAndSetNameSubstring(CardYear year, String setName) {
        return this.cardSetRepository.findByCardYearAndSetNameIgnoreCaseStartingWithOrderByBrandAscSetNameAsc(year, setName);
    }

    private List<CardSet> getResultsWithYearAndSetNameFirstChar(CardYear year, String setName) {
        return this.cardSetRepository.findByCardYearAndSetNameIgnoreCaseStartingWithOrderByBrandAscSetNameAsc(year, setName.charAt(0));
    }

    private List<CardSet> getResultsWithYearAndBrand(CardYear cardYear, Brand brand) {
        return this.cardSetRepository.findByCardYearAndBrandOrderBySetNameAsc(cardYear, brand);
    }

    private List<CardSet> getResultsWithSetName(String setName) {
        return this.cardSetRepository.findBySetNameIgnoreCaseOrderByBrandAsc(setName);
    }

    private List<CardSet> getResultsWithSetNameSubstring(String setName) {
        return this.cardSetRepository.findBySetNameIgnoreCaseStartingWithOrderByBrandAscSetNameAsc(setName);
    }

    private List<CardSet> getResultsWithSetNameFirstChar(String setName) {
        return this.cardSetRepository.findBySetNameIgnoreCaseStartingWithOrderByBrandAscSetNameAsc(setName.charAt(0));
    }

    private List<CardSet> getResultsWithYear(CardYear year) {
        return this.cardSetRepository.findByCardYearOrderByBrandAscSetNameAsc(year);
    }

    private List<CardSet> getResultsWithBrand(Brand brand) {
        return this.cardSetRepository.findByBrandOrderBySetNameAsc(brand);
    }
}
