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

    public List<Card> getCards(Integer cardYear, String brandName, String setName, String cardNum) {
        List<Card> cards = new ArrayList<>();

        CardYear year = null;
        Brand brand = null;
        CardSet set = null;

        if(cardYear != null)
            year = this.cardYearRepository.findByCardYear(cardYear);

        if(brandName != null)
            brand = this.brandRepository.findByBrandNameIgnoreCase(brandName);

        if(brand != null && year != null) {
            if(setName != null)
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, setName);
            else
                set = this.cardSetRepository.findByCardYearAndBrandAndSetNameIgnoreCase(year, brand, "Base Set");
        }

        if(set != null && cardNum != null) {
            Card card = this.cardRepository.findByCardSetAndCardNum(set, cardNum);
            if(card != null)
                cards.add(card);
        }

        if(cards.isEmpty())
            cards = this.cardRepository.findAllByOrderByCardSetAscCardNumAsc();

        return cards;
    }
}
