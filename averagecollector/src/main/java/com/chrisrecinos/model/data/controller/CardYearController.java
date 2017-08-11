package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardYear;

import com.chrisrecinos.model.data.services.CardYearService;
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
@RequestMapping(value = "/years")
public class CardYearController {

    @Autowired
    private CardYearService cardYearService;

    @RequestMapping(method = RequestMethod.GET)
    public String getYears(@RequestParam(required = false) Integer year,
                                   Model model) {
        List<CardYear> cardYears = this.cardYearService.getYearsWithYearName(year);
        model.addAttribute("cardYears", cardYears);

        return "years";
    }
}