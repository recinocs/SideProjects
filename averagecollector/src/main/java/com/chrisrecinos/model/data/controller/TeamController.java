package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.repository.TeamRepository;
import com.chrisrecinos.model.data.entity.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
/**
 * @author - Christopher Recinos
 */

@RestController
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) String city,
                               @RequestParam(required = false) String teamName) {
        String results = "";
        List<Team> teamList = new ArrayList<>();

        if(teamName != null) {
            Team team = this.teamRepository.findByTeamName(teamName);
            if(team != null)
                teamList.add(team);
        } else if(city != null) {
            teamList = this.teamRepository.findByCity(city);
        } else {
            teamList = this.teamRepository.findAll();
        }

        for(Team team: teamList) {
            results += team + "<br/>";
        }

        return results;
    }
}
