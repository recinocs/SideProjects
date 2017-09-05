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
                           @RequestParam(required = false) String city,
                           @RequestParam(required = false) String insertType,
                           @RequestParam(required = false) String memType,
                           Model model) {
        List<Card> cards = this.cardService.getCards(year, brandName, setName, cardNum, firstName,
                lastName, suffix, teamName, city, insertType, memType);
        model.addAttribute("cards", cards);
        return "cards";
    }
}
