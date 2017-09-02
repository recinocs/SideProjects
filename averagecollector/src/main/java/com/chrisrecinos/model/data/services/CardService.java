package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.*;
import com.chrisrecinos.model.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
     * 1. cardset and cardnum and insert and player - DONE
     * 2. cardset and cardnum and insert - DONE
     * 3. cardset and cardnum - DONE
     * 4. cardset and player - DONE
     * 5. player and mem - DONE
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
                               String insertType, String memType) {
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

        if(memType != null && !memType.equals(""))
            hasMem = true;

        if(realSet) {
            if(realNum) {
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

            if(cards.isEmpty() && realPlayer)
                cards = getCardsWithSetAndPlayer(set, player);
        }

        if(cards.isEmpty() && realPlayer && hasMem)
            cards = getCardsWithPlayerAndMem(player, memType);

        if(cards.isEmpty())
            cards = this.cardRepository.findAllByOrderBySerialNumAscCardNumAsc();

        return sortYears(cards);
    }

    private Card getCardWithSetAndNumAndInsertAndPlayer(CardSet cardSet, String cardNum, String insertType, Player player) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseAndPlayer(cardSet, cardNum, insertType, player);
    }

    private List<Card> getCardsWithSetAndNumAndInsert(CardSet cardSet, String cardNum, String insertType) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCase(cardSet, cardNum, insertType);
    }

    private List<Card> getCardsWithSetAndNum(CardSet cardSet, String cardNum) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCaseOrderBySerialNumAsc(cardSet, cardNum);
    }

    private List<Card> getCardsWithSetAndPlayer(CardSet cardSet, Player player) {
        return this.cardRepository.findByCardSetAndPlayerOrderByMemTypeAsc(cardSet, player);
    }

    private List<Card> getCardsWithPlayerAndMem(Player player, String memType) {
        return this.cardRepository.findByPlayerAndMemTypeIgnoreCase(player, memType);
    }

    private List<Card> sortYears(List<Card> cards) {
        Map<Integer, List<Card>> yearsToCards = new HashMap<>();

        for(Card c : cards) {
            Integer c_year = c.getCardSet().getCardYear().getCardYear();
            if(yearsToCards.get(c_year) == null)
                yearsToCards.put(c_year, new ArrayList<>());
            yearsToCards.get(c_year).add(c);
        }

        List<Integer> sortedKeys = new ArrayList<>(yearsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(Integer key : sortedKeys) {
            List<Card> temp = sortBrands(yearsToCards.get(key));
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }

    private List<Card> sortBrands(List<Card> cards) {
        Map<String, List<Card>> brandsToCards = new HashMap<>();

        for(Card c : cards) {
            String c_brand = c.getCardSet().getBrand().getBrandName();
            if(brandsToCards.get(c_brand) == null)
                brandsToCards.put(c_brand, new ArrayList<>());
            brandsToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(brandsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortSets(brandsToCards.get(key));
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }

    private List<Card> sortSets(List<Card> cards) {
        Map<String, List<Card>> setsToCards = new HashMap<>();

        for(Card c : cards) {
            String c_brand = c.getCardSet().getSetName();
            if(setsToCards.get(c_brand) == null)
                setsToCards.put(c_brand, new ArrayList<>());
            setsToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(setsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortInsert(setsToCards.get(key));
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }

    private List<Card> sortInsert(List<Card> cards) {
        Map<String, List<Card>> insertsToCards = new HashMap<>();

        for(Card c : cards) {
            String c_brand = c.getInsertType();
            if(insertsToCards.get(c_brand) == null)
                insertsToCards.put(c_brand, new ArrayList<>());
            insertsToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(insertsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortFirstName(insertsToCards.get(key));
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }

    private List<Card> sortFirstName(List<Card> cards) {
        Map<String, List<Card>> playersToCards = new HashMap<>();

        for(Card c : cards) {
            String c_brand = c.getPlayer().getFirstName();
            if(playersToCards.get(c_brand) == null)
                playersToCards.put(c_brand, new ArrayList<>());
            playersToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(playersToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = playersToCards.get(key);
            for(Card card : temp)
                sortedCards.add(card);
        }

        return sortedCards;
    }
}
