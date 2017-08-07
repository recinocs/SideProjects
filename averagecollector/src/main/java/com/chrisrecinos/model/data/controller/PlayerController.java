package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Player;
import com.chrisrecinos.model.data.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author - Christopher Recinos
 */

@RestController
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) Date dob) {
        String results = "";
        List<Player> playerList = new ArrayList<>();

        if(firstName == null || lastName == null) {
            playerList = this.playerRepository.findAll();
        } else {
            if(dob == null)
                playerList = this.playerRepository.findByFirstNameAndLastName(firstName, lastName);
            else {
                Player player = this.playerRepository.findByFirstNameAndLastNameAndDob(firstName, lastName, dob);
                if(player != null)
                    playerList.add(player);
            }
        }

        for(Player p : playerList) {
            results = results + p + "<br>";
        }

        return results;
    }
}
