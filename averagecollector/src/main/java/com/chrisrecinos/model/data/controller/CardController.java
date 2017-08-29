package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.services.CardService;

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
@RequestMapping(value = "/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @RequestMapping(method = RequestMethod.GET)
    public String getCards(@RequestParam(required = false) Integer year,
                           @RequestParam(required = false) String brandName,
                           @RequestParam(required = false) String setName,
                           @RequestParam(required = false) String cardNum,
                           Model model) {
        List<Card> cards = this.cardService.getCards(year, brandName, setName, cardNum);
        model.addAttribute("cards", sort(cards));
        return "cards";
    }

    private List<Card> sort(List<Card> cards) {
        Map<Integer, List<Card>> yearsToCards = new HashMap<>();

        for(Card c : cards) {
            Integer c_year = c.getCardSet().getCardYear().getCardYear();
            if(yearsToCards.get(c_year) == null)
                yearsToCards.put(c_year, new ArrayList<>());
            yearsToCards.get(c_year).add(c);
        }

        List<Integer> sortedKeys = new ArrayList<>(yearsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(Integer key : sortedKeys) {
            List<Card> temp = yearsToCards.get(key);
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }
}
