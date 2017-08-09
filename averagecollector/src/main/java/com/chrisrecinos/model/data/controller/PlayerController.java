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
                               @RequestParam(required = false) String suffix,
                               @RequestParam(required = false) Date dob) {
        String results = "";
        List<Player> playerList = new ArrayList<>();

        if(firstName != null && lastName != null) {
            if(suffix != null) {
                Player player = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndSuffixIgnoreCase(firstName, lastName, suffix);
                if (player != null) {
                    playerList.add(player);
                } else {
                    suffix = "";
                }
            }

            if(suffix == null || suffix.equals("")) {
                if(dob != null) {
                    Player player = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDob(firstName, lastName, dob);
                    if(player != null)
                        playerList.add(player);
                } else {
                    playerList = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
                    if(playerList.size() == 0) {
                        List<Player> firstNameList = this.playerRepository.findByFirstNameIgnoreCase(firstName);
                        List<Player> lastNameList = this.playerRepository.findByLastNameIgnoreCase(lastName);

                        if(firstNameList != null && lastNameList != null) {
                            if(lastNameList.size() > firstNameList.size()) {
                                playerList = lastNameList;
                            } else {
                                playerList = firstNameList;
                            }
                        }
                    }
                }
            }
        } else if (firstName != null) {
            playerList = this.playerRepository.findByFirstNameIgnoreCase(firstName);
        } else if(lastName != null) {
            playerList = this.playerRepository.findByLastNameIgnoreCase(lastName);
        }

        if(playerList.size() == 0)
            playerList = this.playerRepository.findAll();

        for(Player p : playerList) {
            results += p + "<br/>";
        }

        return results;
    }
}
