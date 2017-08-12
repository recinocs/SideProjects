package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.CardYear;

import com.chrisrecinos.model.data.repository.CardYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 *
 * This class handles all of the business logic
 * for the CardYear Controller
 */

@Service
public class CardYearService {

    private CardYearRepository cardYearRepository;

    @Autowired
    public CardYearService(CardYearRepository cardYearRepository) {
        this.cardYearRepository = cardYearRepository;
    }

    /**
     * This method handles all of the business logic behind finding as accurate
     * search results as possible.
     *
     * If a year is entered, i.e. year != null, the method tries to grab an associated
     * CardYear with that year value. If no CardYear is found, the method returns
     * all CardYears.
     *
     * @param year - the year being searched for
     * @return - a List of CardYears based on the entered year value
     */
    public List<CardYear> getYears(Integer year) {
        List<CardYear> cardYears = new ArrayList<>();

        if(year != null) {
            CardYear cardYear = getYearWithYear(year);
            if(cardYear != null)
                cardYears.add(cardYear);
        }

        if(cardYears.isEmpty())
            cardYears = this.cardYearRepository.findAll();

        return cardYears;
    }

    private CardYear getYearWithYear(Integer year) {
        return this.cardYearRepository.findByCardYear(year);
    }
}
