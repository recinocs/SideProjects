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

    /**
     * Order to find by
     * 1. cardset and cardnum and insert and player
     * 2. cardset and cardnum and insert
     * 3. cardset and cardnum
     * 4. cardset and player
     * 5. player and mem
     * 6. player and cardnum
     * 7. cardset and insert
     * 8. cardset and team
     * 9. team and mem
     * 10. team and insert
     * 11. cardset
     * 12. cardnum
     * 13. player
     * 14. team
     * 15. mem
     * 16. insert
     * 17. all
     */
    public List<Card> getCards(Integer cardYear, String brandName, String setName, String cardNum,
                               String firstName, String lastName, String suffix, String teamName,
                               String insertType) {
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
        boolean realInsert = false;
        boolean hasMem = false;

        if(cardYear != null)
            year = this.cardYearRepository.findByCardYear(cardYear);

        if(brandName != null && !brandName.equals(""))
            brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);
        System.out.println("Brand: " + brand);

        if(brand != null && year != null) {
            if(setName != null && !setName.equals(""))
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, setName);
            else
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, "Base Set");
        }

        System.out.println("Set: " + set);

        if(firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("")) {
            if(suffix != null && !suffix.equals(""))
                player = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndSuffixIgnoreCase(firstName, lastName, suffix);
            if(player == null) {
                List<Player> players = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseOrderByDobAsc(firstName, lastName);
                if(players.size() == 1)
                    player = players.get(0);
            }
        }

        System.out.println("Player: " + player);

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

        if(insertType != null && !insertType.equals(""))
            realInsert = true;

        if(realSet && realNum) {
            if(realInsert) {
                if(realPlayer) {
                    Card card = getCardWithSetAndNumAndInsertAndPlayer(set, cardNum, insertType, player);
                    if (card != null)
                        cards.add(card);
                }

                if(cards.isEmpty())
                    cards = getCardsWithSetAndNumAndInsert(set, cardNum, insertType);
            }

            if(cards.isEmpty())
                cards = this.getCardsWithSetAndNum(set, cardNum);
        }

        if(cards.isEmpty())
            cards = this.cardRepository.findAllByOrderByInsertTypeAscSerialNumAscCardNumAsc();

        return cards;
    }

    private Card getCardWithSetAndNumAndInsertAndPlayer(CardSet cardSet, String cardNum, String insertType, Player player) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseAndPlayer(cardSet, cardNum, insertType, player);
    }

    private List<Card> getCardsWithSetAndNumAndInsert(CardSet cardSet, String cardNum, String insertType) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCase(cardSet, cardNum, insertType);
    }

    private List<Card> getCardsWithSetAndNum(CardSet cardSet, String cardNum) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCaseOrderByInsertTypeAscSerialNumAsc(cardSet, cardNum);
    }
}
