package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.*;
import com.chrisrecinos.model.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author - Christopher Recinos
 */

//TODO - Add functionality for multiple possible players

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
     */
    public List<Card> getCards(Integer cardYear, String brandName, String setName, String cardNum,
                               String firstName, String lastName, String suffix, String teamName,
                               String city, String insertType, String memType) {
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

        if(brand != null && year != null) {
            if(setName != null && !setName.equals(""))
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, setName);
            else
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, "Base Set");
        }

        boolean playerChecked = false;

        if(firstName != null && !firstName.equals("") && lastName != null && !lastName.equals("")) {
            List<Player> players;
            if(suffix != null && !suffix.equals(""))
                player = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndSuffixIgnoreCase(firstName, lastName, suffix);
            if(player == null) {
                players = this.playerRepository.findByFirstNameIgnoreCaseAndLastNameIgnoreCaseOrderByDobAsc(firstName, lastName);
                if(players.size() == 1)
                    player = players.get(0);
            }

            if(player == null) {
                player = this.findPlayerByFirstName(firstName);
                if(player == null) {
                    player = this.findPlayerByLastName(lastName);
                }
            }

            playerChecked = true;
        }

        if(player == null && !playerChecked) {
            if(firstName != null && !firstName.equals("")) {
                player = this.findPlayerByFirstName(firstName);
                if(player == null)
                    player = this.findPlayerByLastName(firstName);
            }

            if(player == null && lastName != null && !lastName.equals("")) {
                player = this.findPlayerByLastName(lastName);
                if(player == null)
                    player = this.findPlayerByFirstName(lastName);
            }
        }

        /*if(teamName != null && !teamName.equals("")) {
            team = this.teamRepository.findByTeamNameIgnoreCase(teamName);
            if(team == null)
                team = this.findTeamByTeamName(teamName);
            if(team == null)
                team = this.findTeamByCity(teamName);
        }

        if(team == null && city != null && !city.equals("")) {
            team = this.findTeamByCity(city);
            if(team == null)
                team = this.findTeamByTeamName(city);
        }*/

        if(teamName != null && !teamName.equals("") && city != null && !city.equals(""))
            team = this.teamRepository.findByCityIgnoreCaseAndTeamNameIgnoreCase(city, teamName);

        System.out.println(city);
        System.out.println(teamName);

        if(team == null) {
            if(teamName != null && !teamName.equals("")) {
                team = this.findTeamByTeamName(teamName);
                if(team == null)
                    team = this.findTeamByCity(teamName);
            }

            if(team == null && city != null && !city.equals("")) {
                team = this.findTeamByCity(city);
                if(team == null)
                    team = this.findTeamByCity(teamName);
            }
        }

        System.out.println(team);

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

        if(cards.isEmpty() && realPlayer) {
            if(hasMem)
                cards = getCardsWithPlayerAndMem(player, memType);
            if(cards.isEmpty() && realNum)
                cards = getCardsWithPlayerAndNum(player, cardNum);
        }

        if(cards.isEmpty() && realSet) {
            if(realInsert)
                cards = this.getCardsWithSetAndInsert(set, insertType);
            if(cards.isEmpty() && realTeam)
                cards = this.getCardsWithSetAndTeam(set, team);
        }

        if(cards.isEmpty() && realTeam) {
            if(hasMem)
                cards = this.getCardsWithTeamAndMem(team, memType);
            if(cards.isEmpty() && realInsert)
                cards = this.getCardsWithTeamAndInsert(team, insertType);
        }

        if(cards.isEmpty() && realSet)
            cards = this.getCardsWithSet(set);

        if(cards.isEmpty() && realNum)
            cards = this.getCardsWithCardNum(cardNum);

        if(cards.isEmpty() && realPlayer)
            cards = this.getCardsWithPlayer(player);

        if(cards.isEmpty() && realTeam)
            cards = this.getCardsWithTeam(team);

        if(cards.isEmpty() && hasMem)
            cards = this.getCardsWithMem(memType);

        if(cards.isEmpty() && realInsert)
            cards = this.getCardsWithInsert(insertType);

        if(cards.isEmpty())
            cards = this.cardRepository.findAll();

        return sortYears(cards);
    }

    private Player findPlayerByFirstName(String firstName) {
        Player player = null;

        List<Player> players = this.playerRepository.findByFirstNameIgnoreCaseOrderByLastNameAscDobAsc(firstName);

        if(players.size() == 1)
            player = players.get(0);
        else if(players.size() == 0) {
            players = this.playerRepository.findByFirstNameIgnoreCaseStartingWithOrderByLastNameAscDobAsc(firstName);
            if(players.size() == 1)
                player = players.get(0);
        }

        return player;
    }

    private Player findPlayerByLastName(String lastName) {
        Player player = null;

        List<Player> players = this.playerRepository.findByLastNameIgnoreCaseOrderByFirstNameAscDobAsc(lastName);
        if(players.size() == 1)
            player = players.get(0);
        else if(players.size() == 0) {
            players = this.playerRepository.findByLastNameIgnoreCaseStartingWithOrderByFirstNameAscDobAsc(lastName);
            if(players.size() == 1)
                player = players.get(0);
        }

        return player;
    }

    private Team findTeamByTeamName(String teamName) {
        Team team = null;

        List<Team> teams = this.teamRepository.findByTeamNameIgnoreCase(teamName);
        if(teams.size() == 1)
            team = teams.get(0);
        else if(teams.size() == 0) {
            teams = this.teamRepository.findByTeamNameIgnoreCaseStartingWithOrderByTeamNameAsc(teamName);
            if(teams.size() == 1)
                team = teams.get(0);
        }

        return team;
    }

    private Team findTeamByCity(String city) {
        Team team = null;

        List<Team> teams = this.teamRepository.findByCityIgnoreCaseOrderByCityAsc(city);
        if(teams.size() == 1)
            team = teams.get(0);
        else if(teams.size() == 0) {
            teams = this.teamRepository.findByCityIgnoreCaseStartingWithOrderByTeamNameAsc(city);
            if(teams.size() == 1)
                team = teams.get(0);
        }

        return team;
    }

    private Card getCardWithSetAndNumAndInsertAndPlayer(CardSet cardSet, String cardNum, String insertType, Player player) {
        Card card = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseAndPlayer(cardSet, cardNum, insertType, player);

        if(card == null) {
            List<Card> cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWithAndPlayer(cardSet, cardNum, insertType, player);
            if(cards.size() == 1)
                card = cards.get(0);
            else if(cards.size() == 0) {
                cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWithAndPlayer(cardSet, cardNum, insertType.charAt(0), player);
                if(cards.size() == 1)
                    card = cards.get(0);
            }
        }

        return card;
    }

    private List<Card> getCardsWithSetAndNumAndInsert(CardSet cardSet, String cardNum, String insertType) {
        List<Card> cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCase(cardSet, cardNum, insertType);

        if(cards.isEmpty()) {
            cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWith(cardSet, cardNum, insertType);
            if(cards.isEmpty())
                cards = this.cardRepository.findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWith(cardSet, cardNum, insertType.charAt(0));
        }

        return cards;
    }

    private List<Card> getCardsWithSetAndNum(CardSet cardSet, String cardNum) {
        return this.cardRepository.findByCardSetAndCardNumIgnoreCase(cardSet, cardNum);
    }

    private List<Card> getCardsWithSetAndPlayer(CardSet cardSet, Player player) {
        return this.cardRepository.findByCardSetAndPlayerOrderByMemTypeAsc(cardSet, player);
    }

    private List<Card> getCardsWithPlayerAndMem(Player player, String memType) {
        return this.cardRepository.findByPlayerAndMemTypeIgnoreCaseContaining(player, memType);
    }

    private List<Card> getCardsWithPlayerAndNum(Player player, String cardNum) {
        return this.cardRepository.findByPlayerAndCardNumIgnoreCase(player, cardNum);
    }

    private List<Card> getCardsWithSetAndInsert(CardSet cardSet, String insertType) {
        List<Card> cards = this.cardRepository.findByCardSetAndInsertTypeIgnoreCase(cardSet, insertType);

        if(cards.isEmpty()) {
            cards = this.cardRepository.findByCardSetAndInsertTypeIgnoreCaseStartingWith(cardSet, insertType);
            if(cards.isEmpty())
                cards = this.cardRepository.findByCardSetAndInsertTypeIgnoreCaseStartingWith(cardSet, insertType.charAt(0));
        }

        return cards;
    }

    private List<Card> getCardsWithSetAndTeam(CardSet cardSet, Team team) {
        return this.cardRepository.findByCardSetAndTeam(cardSet, team);
    }

    private List<Card> getCardsWithTeamAndMem(Team team, String memType) {
        return this.cardRepository.findByTeamAndMemTypeIgnoreCaseContaining(team, memType);
    }

    private List<Card> getCardsWithTeamAndInsert(Team team, String insertType) {
        List<Card> cards = this.cardRepository.findByTeamAndInsertTypeIgnoreCase(team, insertType);

        if(cards.isEmpty()) {
            cards = this.cardRepository.findByTeamAndInsertTypeIgnoreCaseStartingWith(team, insertType);
            if(cards.isEmpty())
                cards = this.cardRepository.findByTeamAndInsertTypeIgnoreCaseStartingWith(team, insertType.charAt(0));
        }

        return cards;
    }

    private List<Card> getCardsWithSet(CardSet cardSet) {
        return this.cardRepository.findByCardSet(cardSet);
    }

    private List<Card> getCardsWithCardNum(String cardNum) {
        return this.cardRepository.findByCardNumIgnoreCase(cardNum);
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
        List<Card> cards = this.cardRepository.findByInsertTypeIgnoreCase(insertType);

        if(cards.isEmpty()) {
            cards = this.cardRepository.findByInsertTypeIgnoreCaseStartingWith(insertType);
            if(cards.isEmpty())
                cards = this.cardRepository.findByInsertTypeIgnoreCaseStartingWith(insertType.charAt(0));
        }

        return cards;
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
