package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Team;
import com.chrisrecinos.model.data.repository.TeamRepository;
import com.chrisrecinos.model.data.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Controller
@RequestMapping(value = "/")
public class StartController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    String getIntroPage() {
        return "index";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    String navToHomePage(Model model) {
        model.addAttribute("teams", getAllTeams());
        return "homepage";
    }

    private List<Team> getAllTeams() {
        return this.teamService.getTeams("", "");
    }
}
