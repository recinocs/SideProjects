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
    List<Card> findAllByOrderByCardSetAscCardNumAsc();
}
