package com.chrisrecinos.model.data.repository;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.entity.CardSet;

import com.chrisrecinos.model.data.entity.CardYear;
import com.chrisrecinos.model.data.entity.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Card findByCardSetAndCardNumAndPlayersInIgnoreCase(CardSet cardSet, String cardNum, List<Player> players);
    Card findByCardSetAndCardYearAndCardNumIgnoreCase(CardSet cardSet, CardYear cardYear, String cardNum);
    List<Card> findByCardSetAndCardYear(CardSet cardSet, CardYear cardYear);
    List<Card> findByCardSetAndCardNumIgnoreCase(CardSet cardSet, String cardNum);
    List<Card> findByCardYearAndCardNumIgnoreCase(CardYear cardYear, String cardNum);
    List<Card> findByPlayers(List<Player> players);
    List<Card> findAll();
}
