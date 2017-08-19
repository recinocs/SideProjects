package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.services.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        model.addAttribute("sets", cardSetList);

        return "sets";
    }
}
