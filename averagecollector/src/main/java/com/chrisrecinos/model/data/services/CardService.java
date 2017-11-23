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
    private TeamRepository teamRepository;
    private CardSetRepository cardSetRepository;

    private PlayerService playerService;

    @Autowired
    public CardService(CardRepository cardRepository, PlayerService playerService,
                       TeamRepository teamRepository, CardSetRepository cardSetRepository) {
        this.cardRepository = cardRepository;
        this.playerService = playerService;
        this.teamRepository = teamRepository;
        this.cardSetRepository = cardSetRepository;
    }

    /**
     * Order to find by
     * 1. cardset and cardnum and insert and player - DONE
     * 2. cardset and cardnum and insert - DONE
     * 3. cardset and cardnum - DONE
     * 4. cardset and player - DONE
     * 5. player and mem - DONE
     * 6. player and cardnum - DONE
     * 7. cardset and insert
     * 8. cardset and team
     * 9. team and mem
     * 10. team and insert
     * 11. cardset - DONE
     * 12. cardnum
     * 13. player
     * 14. team
     * 15. mem
     * 16. insert
     * 17. all - DONE
     */
    public List<Card> getCards(String firstName, String lastName, String suffix, Long teamId,
                               Long setId, String cardNum, String memType,
                               String insertType, String parallelType) {
        List<Card> cards = new ArrayList<>();
        List<Card> results;
        List<Player> players = this.playerService.getPlayers(firstName, lastName, suffix);

        Team team = this.teamRepository.findById(teamId);
        CardSet set = this.cardSetRepository.findById(setId);

        boolean hasNum = strIsValid(cardNum);
        boolean hasMem = strIsValid(memType);
        boolean hasInsert = strIsValid(insertType);
        boolean hasParallel = strIsValid(parallelType);

        if(set != null) {
            if(hasNum) {
                if(hasInsert) {
                    if(!players.isEmpty()) {
                        for(Player p : players) {
                            results = getCardWithSetAndNumAndInsertAndPlayer(set, cardNum, insertType, p);
                            for(Card res : results)
                                cards.add(res);
                        }
                    }

                    if(cards.isEmpty()) {
                        cards = getCardWithSetAndNumAndInsert(set, cardNum, insertType);
                    }
                }

                if(cards.isEmpty()) {
                    cards = getCardsWithSetAndNum(set, cardNum);
                }
            }

            if(cards.isEmpty() && !players.isEmpty()) {
                for(Player p : players) {
                    results = getCardsWithSetAndPlayer(set, p);
                    for(Card res : results)
                        cards.add(res);
                }
            }
        }

        if(cards.isEmpty() && !players.isEmpty()) {
            if(hasMem) {
                for(Player p : players) {
                    results = getCardsWithPlayerAndMem(p, memType);
                    for(Card res : results)
                        cards.add(res);
                }
            }

            if(cards.isEmpty() && hasNum) {
                for(Player p : players) {
                    results = getCardsWithPlayerAndCardNum(p, cardNum);
                    for(Card res : results)
                        cards.add(res);
                }
            }
        }

        if(cards.isEmpty())
            cards = this.cardRepository.findAll();

        return sortYears(cards);
    }

    /****************************
     * HELPER METHODS
     ***************************/

    private boolean strIsValid(String str) {
        return str != null && !str.equals("") && !str.equals("none");
    }

    private List<Card> getCardWithSetAndNumAndInsertAndPlayer(CardSet set, String cardNum, String insertType, Player player) {
        List<Card> cards = new ArrayList<>();
        Card card = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseAndPlayer(set, cardNum, insertType, player);
        if(card != null)
            cards.add(card);
        else {
            cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWithAndPlayer(set, cardNum, insertType, player);
            if(cards.isEmpty())
                cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWithAndPlayer(set, cardNum, insertType.charAt(0), player);
        }

        return cards;
    }

    private List<Card> getCardWithSetAndNumAndInsert(CardSet cardSet, String cardNum, String insertType) {
        List<Card> cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCase(cardSet, cardNum, insertType);
        if(cards.isEmpty()) {
            cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWith(cardSet, cardNum, insertType);
            if(cards.isEmpty()) {
                cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWith(cardSet, cardNum, insertType.charAt(0));
            }
        }

        return cards;
    }

    private List<Card> getCardsWithSetAndNum(CardSet cardSet, String cardNum) {
        List<Card> cards = this.cardRepository.findByCardSetAndCardNumIgnoreCase(cardSet, cardNum);
        if(cards.isEmpty()) {
            cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseStartingWith(cardSet, cardNum);
            if(cards.isEmpty())
                cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseStartingWith(cardSet, cardNum.charAt(0));
        }

        return cards;
    }

    private List<Card> getCardsWithSetAndPlayer(CardSet cardSet, Player player) {
        return this.cardRepository.findByCardSetAndPlayer(cardSet, player);
    }

    private List<Card> getCardsWithPlayerAndMem(Player player, String memType) {
        return this.cardRepository.findByPlayerAndMemTypeIgnoreCase(player, memType);
    }

    private List<Card> getCardsWithPlayerAndCardNum(Player player, String cardNum) {
        List<Card> results = this.cardRepository.findByPlayerAndCardNumIgnoreCase(player, cardNum);
        if(results.isEmpty())
            results = this.cardRepository.findByPlayerAndCardNumIgnoreCaseStartingWith(player, cardNum);
        return results;
    }
    /**
     * Helper method that sorts the results by the year value of their respective card year.
     * Because of how Spring orders foreign keys based upon their id's, I couldn't use
     * the OrderBy keyword, and so I wrote this collection of helper methods instead
     * to handle the sorting for me
     * @param cards - List of Cards being sorted
     * @return - a List of Cards sorted numerically by year, then alphabetically by
     * brand, then set, then insert type, then player
     */
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

    /**
     * Sorts List of Cards alphabetically according to Brand name before passing it
     * back to year sort
     * @param cards - List of Cards being sorted
     * @return - a List of Cards sorted alphabetically by brand, then set, then insert type, then
     * player
     */
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

    /**
     * Sorts List of Cards alphabetically by set name before passing it
     * back to brand sort
     * @param cards - List of Cards being sorted
     * @return - a List of Cards sorted alphabetically by set, then insert type, then player
     */
    private List<Card> sortSets(List<Card> cards) {
        Map<String, List<Card>> setsToCards = new HashMap<>();

        for(Card c : cards) {
            String c_set = c.getCardSet().getSetName();
            if(setsToCards.get(c_set) == null)
                setsToCards.put(c_set, new ArrayList<>());
            setsToCards.get(c_set).add(c);
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

    /**
     * Sorts List of Cards alphabetically by insert type before passing it
     * back to set sort
     * @param cards - List of Cards being sorted
     * @return - a List of Cards sorted alphabetically by insert type, then player
     */
    private List<Card> sortInsert(List<Card> cards) {
        Map<String, List<Card>> insertsToCards = new HashMap<>();

        for(Card c : cards) {
            String c_insert = c.getInsertType();
            if(insertsToCards.get(c_insert) == null)
                insertsToCards.put(c_insert, new ArrayList<>());
            insertsToCards.get(c_insert).add(c);
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

    /**
     * Sorts List of Cards by player before passing it back to
     * insert sort
     * @param cards - List of Cards being sorted
     * @return - a List of Cards sorted alphabetically by player name
     */
    private List<Card> sortFirstName(List<Card> cards) {
        Map<String, List<Card>> playersToCards = new HashMap<>();

        for(Card c : cards) {
            String c_name = c.getPlayer().getFirstName();
            if(playersToCards.get(c_name) == null)
                playersToCards.put(c_name, new ArrayList<>());
            playersToCards.get(c_name).add(c);
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
