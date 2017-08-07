package com.chrisrecinos.model.data.entity;

import javax.persistence.*;

/**
 * @author - Christopher Recinos
 */

@Entity
@Table(name="CARD_YEAR")
public class CardYear {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "CARD_YEAR")
    private int cardYear;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCardYear() {
        return cardYear;
    }

    public void setCardYear(int cardYear) {
        this.cardYear = cardYear;
    }

    public String toString() {
        return Integer.toString(cardYear);
    }
}
