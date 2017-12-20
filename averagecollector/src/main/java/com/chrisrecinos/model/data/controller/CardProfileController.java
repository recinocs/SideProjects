package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.Team;
import com.chrisrecinos.model.data.form.SearchForm;
import com.chrisrecinos.model.data.services.CardSetService;
import com.chrisrecinos.model.data.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = "/profile")
public class CardProfileController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private CardSetService cardSetService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String getProfile(@PathVariable("id")String id, Model model) {
        model.addAttribute("id", id);
        return "profile";
    }

    @RequestMapping(method = RequestMethod.GET)
    String navToHomePage(Model model) {
        model.addAttribute("teams", getAllTeams());
        model.addAttribute("sets", getAllSets());
        model.addAttribute("searchForm", new SearchForm());
        return "homepage";
    }

    private List<Team> getAllTeams() {
        return this.teamService.getTeams("", "");
    }

    private List<CardSet> getAllSets() {
        List<CardSet> sets = this.cardSetService.getSets(null, null, null);

        return cardSetService.sortYears(sets);
    }
}
