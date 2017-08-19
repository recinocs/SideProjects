package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.entity.Player;
import com.chrisrecinos.model.data.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards() {
        return this.cardRepository.findAllByOrderByCardSetAscCardNumAsc();
    }
}
