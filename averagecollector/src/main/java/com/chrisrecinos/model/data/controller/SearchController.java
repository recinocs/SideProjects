package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.form.SearchForm;
import com.chrisrecinos.model.data.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    String runSearch(@ModelAttribute("searchForm") SearchForm searchForm,
                     Model model) {
        List<Card> results = searchService.search(searchForm);

        model.addAttribute("results", results);

        return "results";
    }
}
