package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Player;
import com.chrisrecinos.model.data.services.PlayerService;
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
 * Handles the communication between the front end and the database
 */

@Controller
@RequestMapping(value = "/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET)
    String getPlayers(@RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String suffix,
                               Model model) {

        List<Player> players = this.playerService.getPlayers(firstName, lastName, suffix);

        model.addAttribute("players", players);

        return "players";

    }
}
