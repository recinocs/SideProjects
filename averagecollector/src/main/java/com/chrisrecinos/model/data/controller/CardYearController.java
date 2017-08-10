package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardYear;

import com.chrisrecinos.model.data.services.CardYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author - Christopher Recinos
 */

@RestController
public class CardYearController {

    @Autowired
    private CardYearService cardYearService;

    @RequestMapping(value = "/years", method = RequestMethod.GET)
    public List<CardYear> getYears(@RequestParam(required = false) Integer year) {
        return this.cardYearService.getYearsWithYearName(year);
    }
}
