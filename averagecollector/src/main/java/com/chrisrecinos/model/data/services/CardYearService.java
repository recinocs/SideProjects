package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.CardYear;

import com.chrisrecinos.model.data.repository.CardYearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @author - Christopher Recinos
 */

@Service
public class CardYearService {

    private List<CardYear> cardYears = new ArrayList<>();

    private CardYearRepository cardYearRepository;

    @Autowired
    public CardYearService(CardYearRepository cardYearRepository) {
        this.cardYearRepository = cardYearRepository;
    }

    public List<CardYear> getYearsWithYearName(Integer year) {
        CardYear cardYear;
        if(year != null) {
            cardYear = this.cardYearRepository.findByCardYear(year);
            if(cardYear != null)
                cardYears.add(cardYear);
        }

        if(cardYears.isEmpty())
            cardYears = this.cardYearRepository.findAll();

        return cardYears;
    }
}
