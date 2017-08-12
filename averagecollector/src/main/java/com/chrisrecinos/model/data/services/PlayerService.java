package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.Player;
import com.chrisrecinos.model.data.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 * Handles the business logic for the player controller
 */

@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Returns player or players based upon the input parameters
     *
     * Method checks for Players in this order:
     *  1. Checks first name, last name, and suffix
     *  2. Checks first name and last name
     *  3. Checks first name
     *  4. Checks last name
     *  5. Checks variations of first name (if Tom, checks Tommy, etc.)
     *  6. Checks variations of last name
     *  7. Check if first name is a valid last name variation
     *  8. Check if last name is a valid first name variation
     *  9. Checks all players with first letter of first name
     *  10. Checks all players with first letter of last name
     *  11. Returns all players
     *
     * @param firstName
     * @param lastName
     * @param suffix
     * @return - The player or players most closely associated with the parameters
     */
    public List<Player> getPlayers(String firstName, String lastName, String suffix) {
        List<Player> players = new ArrayList<>();

            if(firstName != null && lastName != null) {
                if (suffix != null) {
                    Player player = getPlayerByFirstNameAndLastNameAndSuffix(firstName, lastName, suffix);
                    if (player != null)
                        players.add(player);
                }
                if (players.isEmpty()) {
                    players = this.getPlayersByFirstNameAndLastName(firstName, lastName);
                }
            }

            if (players.isEmpty()){
                boolean realFirst = false, realSecond = false;
                String tempFirst = "", tempSecond = "";

                if(firstName != null)
                    realFirst = true;
                if(lastName != null)
                    realSecond = true;

                if (players.isEmpty() && realFirst)
                    players = this.getPlayersByFirstName(firstName);

                if (players.isEmpty() && realSecond)
                    players = this.getPlayersByLastName(lastName);

                if (players.isEmpty() && realFirst)
                    players = this.getPlayersByFirstNameLike(firstName);

                if (players.isEmpty() && realSecond)
                    players = this.getPlayerByLastNameLike(lastName);

                if(players.isEmpty() && realSecond)
                    players = this.getPlayersByFirstNameLike(lastName);

                if(players.isEmpty() && realFirst)
                    players = this.getPlayerByLastNameLike(firstName);

                if (players.isEmpty() && realFirst)
                    players = this.getPlayersByFirstNameFirstChar(firstName);

                if (players.isEmpty() && realSecond)
                    players = this.getPlayersByLastNameFirstChar(lastName);
            }

        if(players.isEmpty())
            players = this.playerRepository.findAllByOrderByFirstNameAscLastNameAsc();

        return players;
    }

    private Player getPlayerByFirstNameAndLastNameAndSuffix(String firstName, String lastName, String suffix) {
        return this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndSuffixIgnoreCase(firstName, lastName, suffix);
    }

    private List<Player> getPlayersByFirstNameAndLastName(String firstName, String lastName) {
        return this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);
    }

    private List<Player> getPlayersByFirstName(String firstName) {
        return this.playerRepository.findByFirstNameIgnoreCaseOrderByLastNameAsc(firstName);
    }

    private List<Player> getPlayersByLastName(String lastName) {
        return this.playerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    private List<Player> getPlayersByFirstNameLike(String firstName) {
        return this.playerRepository.findByFirstNameIgnoreCaseStartingWithOrderByLastNameAsc(firstName);
    }

    private List<Player> getPlayerByLastNameLike(String lastName) {
        return this.playerRepository.findByLastNameIgnoreCaseStartingWithOrderByFirstNameAsc(lastName);
    }

    private List<Player> getPlayersByFirstNameFirstChar(String firstName) {
        return this.playerRepository.findByFirstNameIgnoreCaseStartingWithOrderByFirstNameAsc(firstName.charAt(0));
    }

    private List<Player> getPlayersByLastNameFirstChar(String lastName) {
        return this.playerRepository.findByLastNameIgnoreCaseStartingWithOrderByLastNameAsc(lastName.charAt(0));
    }


}
