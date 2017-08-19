package com.chrisrecinos.model.data.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author - Christopher Recinos
 *
 * This class represents a Baseball Card, with all the respective attributes
 * that might make up a card
 */

@Entity
@Table(name = "CARD")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SET_ID")
    private CardSet cardSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "CARD_NUM")
    private String cardNum;

    @Column(name = "PARALLEL_NUM")
    private String parallelNum;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "MEM_TYPE")
    private String memType;

    @Column(name = "IMAGE_NAME")
    private String imageName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CardSet getCardSet() {
        return cardSet;
    }

    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getParallelNum() {
        return parallelNum;
    }

    public void setParallelNum(String parallelNum) {
        this.parallelNum = parallelNum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String toString() {
        return cardSet + " " + player + " #" + cardNum;
    }
}
