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
                           @RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName,
                           @RequestParam(required = false) String suffix,
                           @RequestParam(required = false) String teamName,
                           @RequestParam(required = false) String insertType,
                           Model model) {
        List<Card> cards = this.cardService.getCards(year, brandName, setName, cardNum, firstName,
                lastName, suffix, teamName, insertType);
        model.addAttribute("cards", sortYears(cards));
        return "cards";
    }

    private List<Card> sortYears(List<Card> cards) {
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
            List<Card> temp = sortBrands(yearsToCards.get(key));
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }

    private List<Card> sortBrands(List<Card> cards) {
        Map<String, List<Card>> brandsToCards = new HashMap<>();

        for(Card c : cards) {
            String c_brand = c.getCardSet().getBrand().getBrandName();
            if(brandsToCards.get(c_brand) == null)
                brandsToCards.put(c_brand, new ArrayList<>());
            brandsToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(brandsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortSets(brandsToCards.get(key));
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }

    private List<Card> sortSets(List<Card> cards) {
        Map<String, List<Card>> setsToCards = new HashMap<>();

        for(Card c : cards) {
            String c_brand = c.getCardSet().getSetName();
            if(setsToCards.get(c_brand) == null)
                setsToCards.put(c_brand, new ArrayList<>());
            setsToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(setsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortFirstName(setsToCards.get(key));
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }

    private List<Card> sortFirstName(List<Card> cards) {
        Map<String, List<Card>> playersToCards = new HashMap<>();

        for(Card c : cards) {
            String c_brand = c.getPlayer().getFirstName();
            if(playersToCards.get(c_brand) == null)
                playersToCards.put(c_brand, new ArrayList<>());
            playersToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(playersToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = playersToCards.get(key);
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }
}
