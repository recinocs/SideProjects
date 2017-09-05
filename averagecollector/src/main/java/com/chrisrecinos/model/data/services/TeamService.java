package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.Team;
import com.chrisrecinos.model.data.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 *
 * This class handles the business logic for the Team Controller
 */

@Service
public class TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    /**
     * This method handles all of the business logic behind finding as accurate
     * search results as possible.
     *
     * Because a team name is unique to one team, the method searches first for
     * a Team with a given teamName, before searching by city if a city value is
     * entered and nothing is found by teamName.
     *
     * If teamName is a valid parameter, i.e. teamName != null, the method tries to
     * find a Team with the associated teamName. If no Team is found, the method then
     * tries to find all Teams associated with the city (since there can be multiple
     * teams in a city, such as the LA Dodgers and the LA Angels, this returns a List
     * rather than a single element). If no results are found from the city search, the
     * method then tries to find all Teams that start with the first letter of teamName,
     * and if that still fails, then all Teams that start with the first letter of city.
     *
     * If still no results are found, all Teams are returned.
     *
     * @param city
     * @param teamName
     * @return - A List of Teams based upon the city and teamName entered
     */
    public List<Team> getTeams(String city, String teamName) {
        List<Team> teams = new ArrayList<>();

        boolean realTeamName = false, realCity = false;

        if(teamName != null && !teamName.equals(""))
            realTeamName = true;

        if(city != null && !city.equals(""))
            realCity = true;

        if(realTeamName || realCity) {
            Team team = getTeamWithTeamName(teamName);
            if(team != null) {
                teams.add(team);
            } else {
                teams = this.getTeamsWithCity(city);
            }
        }

        if(teams.isEmpty()) {
            if(realTeamName) {
                teams = this.getTeamsStartingWithSubstring(teamName);
                if(teams.isEmpty())
                    teams = this.getTeamsStartingWithChar(teamName);
            }

            if(teams.isEmpty() && realCity) {
                teams = this.getTeamsStartingWithCitySubstring(city);
                if(teams.isEmpty())
                    teams = this.getTeamsStartingWithCityChar(city);
                if(teams.isEmpty())
                    teams = this.getTeamsStartingWithSubstring(city);
            }

            if(teams.isEmpty() && realTeamName)
                teams = this.getTeamsStartingWithCityChar(teamName);
        }

        if(teams.isEmpty())
            teams = this.teamRepository.findAllByOrderByCityAsc();

        return teams;
    }

    private Team getTeamWithTeamName(String teamName) {
        return this.teamRepository.findByTeamNameIgnoreCase(teamName);
    }

    private List<Team> getTeamsWithCity(String city) {
        return this.teamRepository.findByCityIgnoreCaseOrderByCityAsc(city);
    }

    private List<Team> getTeamsStartingWithSubstring(String teamName) {
        return this.teamRepository.findByTeamNameIgnoreCaseStartingWithOrderByTeamNameAsc(teamName);
    }

    private List<Team> getTeamsStartingWithCitySubstring(String city) {
        return this.teamRepository.findByCityIgnoreCaseStartingWithOrderByTeamNameAsc(city);
    }

    private List<Team> getTeamsStartingWithChar(String teamName) {
        return this.teamRepository.findByTeamNameIgnoreCaseStartingWithOrderByTeamNameAsc(teamName.charAt(0));
    }

    private List<Team> getTeamsStartingWithCityChar(String city) {
        return this.teamRepository.findByCityIgnoreCaseStartingWithOrderByTeamNameAsc(city.charAt(0));
    }
}
