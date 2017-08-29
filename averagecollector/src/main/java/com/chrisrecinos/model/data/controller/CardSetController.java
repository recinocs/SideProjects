package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.CardYear;
import com.chrisrecinos.model.data.services.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = "/sets")
public class CardSetController {

    @Autowired
    private CardSetService cardSetService;

    @RequestMapping(method = RequestMethod.GET)
    public String getSets(@RequestParam(required = false) Integer year,
                          @RequestParam(required = false) String brandName,
                          @RequestParam(required = false) String setName,
                          Model model) {
        List<CardSet> cardSetList = this.cardSetService.getSets(year, brandName, setName);

        model.addAttribute("sets", sort(cardSetList));

        return "sets";
    }

    private List<CardSet> sort(List<CardSet> sets) {
        Map<Integer, List<CardSet>> yearsToSets = new HashMap<>();

        for(CardSet c : sets) {
            Integer c_year = c.getCardYear().getCardYear();
            if(yearsToSets.get(c_year) == null) {
                yearsToSets.put(c_year, new ArrayList<>());
                yearsToSets.get(c_year).add(c);
            }
        }

        List<Integer> sortedKeys = new ArrayList<>(yearsToSets.keySet());
        Collections.sort(sortedKeys);

        List<CardSet> sortedSets = new ArrayList<>();

        for(Integer key : sortedKeys) {
            List<CardSet> temp = yearsToSets.get(key);
            for(CardSet set : temp)
                sortedSets.add(set);
        }

        return sortedSets;
    }
}
