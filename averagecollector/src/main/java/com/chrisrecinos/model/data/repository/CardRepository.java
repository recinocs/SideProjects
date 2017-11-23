package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.entity.CardSet;
import com.chrisrecinos.model.data.entity.Player;
import com.chrisrecinos.model.data.entity.Team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    List<Card> findByCardSetAndCardNumIgnoreCaseStartingWithAndInsertTypeIgnoreCaseStartingWithAndPlayer(CardSet cardSet, String cardNum, String insertType, Player player);
    List<Card> findByCardSetAndCardNumIgnoreCaseStartingWithAndInsertTypeIgnoreCaseStartingWithAndPlayer(CardSet cardSet, String cardNum, Character c, Player player);
    List<Card> findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCase(CardSet cardSet, String cardNum, String insertType);
    List<Card> findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWith(CardSet cardSet, String cardNum, String insertType);
    List<Card> findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseStartingWith(CardSet cardSet, String cardNum, Character c);
    List<Card> findByCardSetAndCardNumIgnoreCaseStartingWith(CardSet cardSet, String cardNum);
    List<Card> findByCardSetAndCardNumIgnoreCaseStartingWith(CardSet cardSet, Character c);
    List<Card> findByCardSetAndPlayer(CardSet cardSet, Player player);
    List<Card> findByPlayerAndMemTypeIgnoreCase(Player player, String memType);
    List<Card> findByPlayerAndCardNumIgnoreCase(Player player, String cardNum);
    List<Card> findByPlayerAndCardNumIgnoreCaseStartingWith(Player player, String cardNum);
    List<Card> findByCardSetAndInsertTypeIgnoreCase(CardSet cardSet, String insertType);
    List<Card> findByCardSetAndInsertTypeIgnoreCaseStartingWith(CardSet cardSet, String insertType);
    List<Card> findByCardSetAndInsertTypeIgnoreCaseStartingWith(CardSet cardSet, Character c);
    List<Card> findByCardSetAndTeam(CardSet cardSet, Team team);
    List<Card> findByTeamAndMemTypeIgnoreCaseContaining(Team team, String memType);
    List<Card> findByTeamAndInsertTypeIgnoreCase(Team team, String insertType);
    List<Card> findByTeamAndInsertTypeIgnoreCaseStartingWith(Team team, String insertType);
    List<Card> findByTeamAndInsertTypeIgnoreCaseStartingWith(Team team, Character c);
    List<Card> findByCardSet(CardSet cardSet);
    List<Card> findByCardNumIgnoreCase(String cardNum);
    List<Card> findByPlayer(Player player);
    List<Card> findByTeam(Team team);
    List<Card> findByMemTypeIgnoreCaseContaining(String memType);
    List<Card> findByInsertTypeIgnoreCase(String insertType);
    List<Card> findByInsertTypeIgnoreCaseStartingWith(String insertType);
    List<Card> findByInsertTypeIgnoreCaseStartingWith(Character c);
    List<Card> findAll();
}
