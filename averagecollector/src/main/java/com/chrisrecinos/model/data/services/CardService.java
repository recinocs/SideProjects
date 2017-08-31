package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.*;
import com.chrisrecinos.model.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Service
public class CardService {

    private CardRepository cardRepository;
    private CardSetRepository cardSetRepository;
    private PlayerRepository playerRepository;
    private BrandRepository brandRepository;
    private CardYearRepository cardYearRepository;
    private TeamRepository teamRepository;

    @Autowired
    public CardService(CardRepository cardRepository, CardSetRepository cardSetRepository,
                       PlayerRepository playerRepository, BrandRepository brandRepository,
                       CardYearRepository cardYearRepository, TeamRepository teamRepository) {
        this.cardRepository = cardRepository;
        this.cardSetRepository = cardSetRepository;
        this.playerRepository = playerRepository;
        this.brandRepository = brandRepository;
        this.cardYearRepository = cardYearRepository;
        this.teamRepository = teamRepository;
    }

    //TODO Add searching for city and lists
    public List<Card> getCards(Integer cardYear, String brandName, String setName, String cardNum,
                               String firstName, String lastName, String suffix, String teamName) {
        List<Card> cards = new ArrayList<>();

        CardYear year = null;
        Brand brand = null;
        CardSet set = null;
        Player player = null;
        Team team = null;

        boolean realSet = false;
        boolean realNum = false;
        boolean realPlayer = false;
        boolean realTeam = false;

        if(cardYear != null)
            year = this.cardYearRepository.findByCardYear(cardYear);

        if(brandName != null && !brandName.equals(""))
            brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);

        if(brand != null && year != null) {
            if(setName != null && !setName.equals(""))
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, setName);
            else
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, "Base Set");
        }

        if(firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("")) {
            if(suffix != null && !suffix.equals(""))
                player = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndSuffixIgnoreCase(firstName, lastName, suffix);
        }

        if(teamName != null && !teamName.equals("")) {
            team = this.teamRepository.findByTeamNameIgnoreCase(teamName);
        }

        if(player != null)
            realPlayer = true;

        if(team != null)
            realTeam = true;

        if(set != null)
            realSet = true;

        if(cardNum != null && !cardNum.equals(""))
            realNum = true;

        if(realSet && realNum) {
            Card card = getCardWithSetAndCardNum(set, cardNum);
            if(card != null)
                cards.add(card);
        }

        if(cards.isEmpty() && realPlayer && realNum)
            cards = this.getCardsWithPlayerAndCardNum(player, cardNum);

        if(cards.isEmpty() && realPlayer && realSet)
            cards = this.getCardsWithSetAndPlayer(set, player);

        if(cards.isEmpty())
            cards = this.cardRepository.findAllByOrderByCardSetAscCardNumAsc();

        return cards;
    }

    private Card getCardWithSetAndCardNum(CardSet set, String cardNum) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCase(set, cardNum);
    }

    private List<Card> getCardsWithPlayerAndCardNum(Player player, String cardNum) {
        return this.cardRepository.findByPlayerAndCardNumIgnoreCase(player, cardNum);
    }

    private List<Card> getCardsWithSetAndPlayer(CardSet set, Player player) {
        return this.cardRepository.findByCardSetAndPlayerSortByCardNumAsc(set, player);
    }
}
