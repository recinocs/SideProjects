package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.Team;
import com.chrisrecinos.model.data.form.SearchForm;
import com.chrisrecinos.model.data.repository.CardSetRepository;
import com.chrisrecinos.model.data.repository.TeamRepository;
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

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    StartController startController;

    @RequestMapping(method = RequestMethod.GET)
    String runSearch(@ModelAttribute("searchForm") SearchForm searchForm,
                     Model model) {
        List<Card> results = searchService.search(searchForm);

        model.addAttribute("teams", startController.getAllTeams());
        model.addAttribute("sets", startController.getAllSets());
        model.addAttribute("results", results);
        model.addAttribute("prev_search", "You searched for: " + getPreviousSearch(searchForm));
        model.addAttribute("result_size", results.size());

        return "results";
    }

    private String getPreviousSearch(SearchForm searchForm) {
        String ret_str = "";

        if(searchForm.getPlayer() != null)
            ret_str += searchForm.getPlayer() + " ";
        if(searchForm.getTeamId() != null) {
            Team team = this.teamRepository.findById(searchForm.getTeamId());
            if(team != null)
                ret_str += team + " ";
        }
        if(searchForm.getSetId() != null) {
            CardSet cardSet = this.cardSetRepository.findById(searchForm.getSetId());
            if(cardSet != null)
                ret_str += cardSet + " " ;
        }

        ret_str += searchForm.getCardNum() + " " + searchForm.getInsertType() + " " +
                searchForm.getSerialNum() + " ";

        if(!searchForm.getMemType().equals("none"))
            ret_str += searchForm.getMemType();

        return ret_str;
    }
}
