package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.form.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(method = RequestMethod.GET)
    String runSearch(@ModelAttribute("searchForm") SearchForm searchForm,
                     Model model) {

        model.addAttribute("teamId", searchForm.getTeamId());

        return "results";
    }
}
