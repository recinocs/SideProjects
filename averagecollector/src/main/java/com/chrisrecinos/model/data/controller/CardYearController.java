package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardYear;
import com.chrisrecinos.model.data.repository.CardYearRepository;
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
public class CardYearController {

    @Autowired
    private CardYearRepository cardYearRepository;

    @RequestMapping(value = "/years", method= RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) Integer year) {
        List<CardYear> cardYears = new ArrayList<>();
        if(year == null) {
            cardYears = this.cardYearRepository.findAll();
        } else {
            CardYear cardYear = this.cardYearRepository.findByCardYear(year);
            if(cardYear != null)
                cardYears.add(cardYear);
        }

        String results = "";

        for(CardYear y: cardYears) {
            results += y + "<br/>";
        }

        return results;
    }
}
