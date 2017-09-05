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
    Card findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCaseAndPlayer(CardSet cardSet, String cardNum, String insertType, Player player);
    List<Card> findByCardSetAndCardNumIgnoreCaseAndInsertTypeIgnoreCase(CardSet cardSet, String cardNum, String insertType);
    List<Card> findByCardSetAndCardNumIgnoreCase(CardSet cardSet, String cardNum);
    List<Card> findByCardSetAndPlayerOrderByMemTypeAsc(CardSet cardSet, Player player);
    List<Card> findByPlayerAndMemTypeIgnoreCase(Player player, String memType);
    List<Card> findByPlayerAndCardNumIgnoreCase(Player player, String cardNum);
    List<Card> findByCardSetAndInsertTypeIgnoreCase(CardSet cardSet, String insertType);
    List<Card> findByCardSetAndTeam(CardSet cardSet, Team team);
    List<Card> findAll();
}
