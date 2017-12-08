package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.Team;
import com.chrisrecinos.model.data.form.SearchForm;
import com.chrisrecinos.model.data.repository.TeamRepository;
import com.chrisrecinos.model.data.services.CardSetService;
import com.chrisrecinos.model.data.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = "/")
public class StartController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private CardSetService cardSetService;

    @RequestMapping(method = RequestMethod.GET)
    String getIntroPage() {
        return "index";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    String navToHomePage(Model model) {
        model.addAttribute("teams", getAllTeams());
        model.addAttribute("sets", getAllSets());
        model.addAttribute("searchForm", new SearchForm());
        return "homepage";
    }

    protected List<Team> getAllTeams() {
        return this.teamService.getTeams("", "");
    }

    protected List<CardSet> getAllSets() {
        List<CardSet> sets = this.cardSetService.getSets(null, null, null);

        return cardSetService.sortYears(sets);
    }
}
