package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.*;
import com.chrisrecinos.model.data.repository.*;
import org.apache.commons.lang.StringUtils;
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
     * 1. cardset and cardnum and insert and player - DONE, Test Written
     * 2. cardset and cardnum and insert - DONE, Test Written
     * 3. cardset and cardnum - DONE, Test Written
     * 4. cardset and player - DONE, Test Written
     * 5. player and mem - DONE, Test Written
     * 6. player and cardnum - DONE
     * 7. cardset and insert - DONE
     * 8. cardset and team - DONE
     * 9. team and mem - DONE
     * 10. team and insert - DONE
     * 11. cardset - DONE
     * 12. cardnum - DONE
     * 13. player - DONE
     * 14. team - DONE
     * 15. mem - DONE
     * 16. insert - DONE
     * 17. all - DONE
     *
     * After all scenarios have been run through, the list is then
     * filtered based on the parallel option chosen
     */
    public List<Card> getCards(String firstName, String lastName, String suffix, Long teamId,
                               Long setId, String cardNum, String memType,
                               String insertType, String serialNum) {
        List<Card> cards = new ArrayList<>();
        List<Card> results;
        List<Player> players = new ArrayList<>();

        if(firstName != null && !firstName.equalsIgnoreCase(""))
            players = this.playerService.getPlayers(firstName, lastName, suffix);

        Team team = this.teamRepository.findById(teamId);
        CardSet set = this.cardSetRepository.findById(setId);

        boolean hasNum = StringUtils.isNotEmpty(cardNum);
        boolean hasMem = StringUtils.isNotEmpty(memType);
        boolean hasInsert = StringUtils.isNotEmpty(insertType);
        boolean hasSerial = StringUtils.isNotEmpty(serialNum);

        if(set != null) {
            if(hasNum) {
                if(hasInsert) {
                    if(!players.isEmpty()) {
                        for(Player p : players) {
                            results = getCardWithSetAndNumAndInsertAndPlayer(set, cardNum, insertType, p);
                            cards.addAll(results);
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
                    cards.addAll(results);
                }
            }
        }

        if(cards.isEmpty() && !players.isEmpty()) {
            if(hasMem) {
                for(Player p : players) {
                    results = getCardsWithPlayerAndMem(p, memType);
                    cards.addAll(results);
                }
            }

            if(cards.isEmpty() && hasNum) {
                for(Player p : players) {
                    results = getCardsWithPlayerAndCardNum(p, cardNum);
                    cards.addAll(results);
                }
            }
        }

        if(cards.isEmpty() && set != null) {
            if(hasInsert)
                cards = getCardsWithSetAndInsert(set, insertType);

            if(cards.isEmpty() && team != null)
                cards = getCardsWithSetAndTeam(set, team);
        }

        if(cards.isEmpty() && team != null) {
            if(hasMem)
                cards = getCardsWithTeamAndMem(team, memType);

            if(cards.isEmpty() && hasInsert)
                cards = getCardsWithTeamAndInsert(team, insertType);
        }

        if(cards.isEmpty() && set != null)
            cards = getCardsWithSet(set);

        if(cards.isEmpty() && hasNum)
            cards = getCardsWithNum(cardNum);

        if(cards.isEmpty() && !players.isEmpty()) {
            for(Player p : players) {
                results = getCardsWithPlayer(p);
                cards.addAll(results);
            }
        }

        if(cards.isEmpty() && team != null)
            cards = getCardsWithTeam(team);

        if(cards.isEmpty() && hasMem)
            cards = getCardsWithMem(memType);

        if(cards.isEmpty() && hasInsert)
            cards = getCardsWithInsert(insertType);

        if(cards.isEmpty())
            cards = this.cardRepository.findAll();

        if(hasSerial)
            cards = serialFilter(cards, serialNum);

        if(cards.size() == this.cardRepository.findAll().size())
            cards = new ArrayList<>();

        return sortYears(cards);
    }

    /****************************
     * HELPER METHODS
     ***************************/

    private List<Card> getCardWithSetAndNumAndInsertAndPlayer(CardSet set, String cardNum, String insertType, Player player) {
        List<Card> cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseStartingWithAndInsertTypeIgnoreCaseStartingWithAndPlayer(set, cardNum, insertType, player);

        if(cards.isEmpty())
            cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseStartingWithAndInsertTypeIgnoreCaseStartingWithAndPlayer(set, cardNum, insertType.charAt(0), player);

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
        List<Card> cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseStartingWith(cardSet, cardNum);

            if(cards.isEmpty())
                cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseStartingWith(cardSet, cardNum.charAt(0));

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

    private List<Card> getCardsWithSetAndInsert(CardSet cardSet, String insert) {
        List<Card> results = this.cardRepository.findByCardSetAndInsertTypeIgnoreCaseStartingWith(cardSet, insert);
        if(results.isEmpty())
            results = this.cardRepository.findByCardSetAndInsertTypeIgnoreCaseStartingWith(cardSet, insert.charAt(0));
        return results;
    }

    private List<Card> getCardsWithSetAndTeam(CardSet cardSet, Team team) {
        return this.cardRepository.findByCardSetAndTeam(cardSet, team);
    }

    private List<Card> getCardsWithTeamAndMem(Team team, String memType) {
        return this.cardRepository.findByTeamAndMemTypeIgnoreCaseContaining(team, memType);
    }

    private List<Card> getCardsWithTeamAndInsert(Team team, String insertType) {
        List<Card> results = this.cardRepository.findByTeamAndInsertTypeIgnoreCaseStartingWith(team, insertType);
        if(results.isEmpty())
            results = this.cardRepository.findByTeamAndInsertTypeIgnoreCaseStartingWith(team, insertType.charAt(0));
        return results;
    }

    private List<Card> getCardsWithSet(CardSet cardSet) {
        return this.cardRepository.findByCardSet(cardSet);
    }

    private List<Card> getCardsWithNum(String cardNum) {
        return this.cardRepository.findByCardNumIgnoreCaseContaining(cardNum);
    }

    private List<Card> getCardsWithPlayer(Player player) {
        return this.cardRepository.findByPlayer(player);
    }

    private List<Card> getCardsWithTeam(Team team) {
        return this.cardRepository.findByTeam(team);
    }

    private List<Card> getCardsWithMem(String memType) {
        return this.cardRepository.findByMemTypeIgnoreCaseContaining(memType);
    }

    private List<Card> getCardsWithInsert(String insertType) {
        List<Card> results = this.cardRepository.findByInsertTypeIgnoreCaseStartingWith(insertType);
        if(results.isEmpty())
            results = this.cardRepository.findByInsertTypeIgnoreCaseStartingWith(insertType.charAt(0));
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
            yearsToCards.putIfAbsent(c_year, new ArrayList<>());
            yearsToCards.get(c_year).add(c);
        }

        List<Integer> sortedKeys = new ArrayList<>(yearsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(Integer key : sortedKeys) {
            List<Card> temp = sortBrands(yearsToCards.get(key));
            sortedCards.addAll(temp);
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
            brandsToCards.putIfAbsent(c_brand, new ArrayList<>());
            brandsToCards.get(c_brand).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(brandsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortSets(brandsToCards.get(key));
            sortedCards.addAll(temp);
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
            setsToCards.putIfAbsent(c_set, new ArrayList<>());
            setsToCards.get(c_set).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(setsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortInsert(setsToCards.get(key));
            sortedCards.addAll(temp);
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
            insertsToCards.putIfAbsent(c_insert, new ArrayList<>());
            insertsToCards.get(c_insert).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(insertsToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = sortFirstName(insertsToCards.get(key));
            sortedCards.addAll(temp);
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
            playersToCards.putIfAbsent(c_name, new ArrayList<>());
            playersToCards.get(c_name).add(c);
        }

        List<String> sortedKeys = new ArrayList<>(playersToCards.keySet());
        Collections.sort(sortedKeys);

        List<Card> sortedCards = new ArrayList<>();

        for(String key : sortedKeys) {
            List<Card> temp = playersToCards.get(key);
            sortedCards.addAll(temp);
        }

        return sortedCards;
    }

    private List<Card> serialFilter(List<Card> cards, String serial) {
        List<Card> filteredCards = new ArrayList<>();

        for(Card c : cards) {
            String cardSerialNumber = c.getSerialNum().split("/")[1];
            if(cardSerialNumber.equals(serial))
                filteredCards.add(c);
        }

        return filteredCards;
    }
}
