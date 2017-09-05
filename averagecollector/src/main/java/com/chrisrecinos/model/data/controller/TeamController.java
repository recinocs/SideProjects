package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Team;

import com.chrisrecinos.model.data.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/**
 * @author - Christopher Recinos
 *
 * This class handles all of the communication with the front end
 * for the "/teams" endpoint.
 */

@Controller
@RequestMapping(value = "/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) String city,
                               @RequestParam(required = false) String teamName,
                               Model model) {
        List<Team> teams = teamService.getTeams(city, teamName);

        model.addAttribute("teams", teams);

        return "teams";
    }
}
