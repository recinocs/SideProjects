package com.chrisrecinos.model.data.services;

import com.chrisrecinos.model.data.entity.Card;
import com.chrisrecinos.model.data.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Service
public class SearchService {

    private CardService cardService;

    @Autowired
    public SearchService(CardService cardService) {
        this.cardService = cardService;
    }

    public List<Card> search(SearchForm searchForm) {
        List<Card> results;

        String[] playerNames = searchForm.getPlayer().split(" ");
        String firstName, lastName = "", suffix = "";

        Long teamId = searchForm.getTeamId();
        Long setId = searchForm.getSetId();
        String cardNum = searchForm.getCardNum();
        String memType = searchForm.getMemType();
        String insertType = searchForm.getInsertType();
        String serialNum = searchForm.getSerialNum();

        switch (playerNames.length) {
            case 1:
                firstName = playerNames[0];
                break;
            case 2:
                firstName = playerNames[0];
                lastName = playerNames[1];
                break;
            default:
                firstName = playerNames[0];
                lastName = playerNames[1];
                suffix = playerNames[2];
        }

        results = this.cardService.getCards(firstName, lastName, suffix, teamId, setId, cardNum,
                                            memType, insertType, serialNum);

        return results;
    }
}
