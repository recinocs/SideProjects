package com.chrisrecinos.model.data.entity;

import javax.persistence.*;

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

    @Column(name = "SERIAL_NUM")
    private String serialNum;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "MEM_TYPE")
    private String memType;

    @Column(name = "IMAGE_FRONT")
    private String imageFront;

    @Column(name = "INSERT_TYPE")
    private String insertType;

    @Column(name = "PARALLEL_TYPE")
    private String parallelType;

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

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
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

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
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

    public String getInsertType() {
        return insertType;
    }

    public void setInsertType(String insertType) {
        this.insertType = insertType;
    }

    public String getParallelType() {
        return parallelType;
    }

    public void setParallelType(String parallelType) {
        this.parallelType = parallelType;
    }

    public String toString() {
        String str = cardSet + " " + player + " #" + cardNum;

        if(!(insertType.equals("n/a")))
            str += " " + insertType;

        if(!(parallelType.equals("n/a")))
            str += " " + parallelType;

        if(!(memType.equals("n/a")))
            str += " " + memType;

        if(!(serialNum.equals("n/a")))
            str += " " + serialNum;


        return str;
    }
}
