package com.chrisrecinos.model.data.controller;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.repository.*;
import com.chrisrecinos.model.data.entity.Brand;
import com.chrisrecinos.model.data.entity.CardYear;
import com.chrisrecinos.model.data.entity.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 *
 *
 * TODO - Refactor all common logic into service class for all controllers
 */

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardSetRepository cardSetRepository;

    @Autowired
    private CardYearRepository cardYearRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    String getResultsForH2Test(@RequestParam(required = false) String brandName,
                               @RequestParam(required = false) String setName,
                               @RequestParam(required = false) Integer year,
                               @RequestParam(required = false) String cardNum,
                               @RequestParam(required = false) String...playerNames) {
        String results = "";
        List<Card> cardList = new ArrayList<>();
        CardSet cardSet = null;
        Brand brand = null;
        CardYear cardYear = null;
        List<Player> players = new ArrayList<>();

        if(playerNames != null) {
            for(String playerName : playerNames) {
                String[] names = playerName.split(" ");
                if(names.length >= 2) {
                    String suffix = null;
                    if(names.length >= 3)
                        suffix = names[2];
                    String firstName = names[0];
                    String lastName = names[1];

                    if(firstName != null && lastName != null && suffix != null) {
                        Player player = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndSuffixIgnoreCase(firstName, lastName, suffix);
                        if(player != null)
                            players.add(player);
                    } else if(firstName != null && lastName != null) {
                        List<Player> nameSearchResults = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName);

                        List<Player> invertedNameSearchResults = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCase(lastName, firstName);
                        List<Player> useList;

                        if (nameSearchResults != null && invertedNameSearchResults != null) {
                            if (invertedNameSearchResults.size() > nameSearchResults.size()) {
                                useList = invertedNameSearchResults;
                            } else {
                                useList = nameSearchResults;
                            }

                            for (Player p : useList) {
                                players.add(p);
                            }
                        }
                    }
                } else {
                    List<Player> firstNameSearchResults = this.playerRepository.findByFirstNameIgnoreCase(names[0]);
                    List<Player> lastNameSearchResults = this.playerRepository.findByLastNameIgnoreCase(names[0]);

                    if(firstNameSearchResults != null && lastNameSearchResults != null) {
                        if(lastNameSearchResults.size() > firstNameSearchResults.size()) {
                            for(Player p : lastNameSearchResults)
                                players.add(p);
                        } else {
                            for(Player p : firstNameSearchResults)
                                players.add(p);
                        }
                    }
                }
            }
        }

        if(brandName != null)
            brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);

        if(brand != null && setName != null)
            cardSet = this.cardSetRepository.findByBrandAndSetNameIgnoreCase(brand, setName);

        if(year != null)
            cardYear = this.cardYearRepository.findByCardYear(year);

        if(cardSet != null && cardYear != null && players.size() != 0) {
            Card card = this.cardRepository.findByCardSetAndCardNumAndPlayersInIgnoreCase(cardSet, cardNum, players);
            if(card != null)
                cardList.add(card);
        } else if(cardSet != null && cardYear != null && cardNum != null) {
            Card card = this.cardRepository.findByCardSetAndCardYearAndCardNumIgnoreCase(cardSet, cardYear, cardNum);
            if(card != null)
                cardList.add(card);
        } else if(players.size() != 0) {
            cardList = this.cardRepository.findByPlayers(players);
        } else if (cardSet != null && cardYear != null) {
            cardList = this.cardRepository.findByCardSetAndCardYear(cardSet, cardYear);
        } else if (cardSet != null && cardNum != null) {
            cardList = this.cardRepository.findByCardSetAndCardNumIgnoreCase(cardSet, cardNum);
        } else if (cardYear != null && cardNum != null) {
            cardList = this.cardRepository.findByCardYearAndCardNumIgnoreCase(cardYear, cardNum);
        }

        if(cardList.size() == 0)
            cardList = this.cardRepository.findAll();

        for(Card card : cardList) {
            results += card + "<br/>";
        }

        return results;
    }
}
