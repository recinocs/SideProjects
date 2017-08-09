package com.chrisrecinos.model.data.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author - Christopher Recinos
 */

@Entity
@Table(name = "CARD")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "YEAR_ID")
    private CardYear cardYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SET_ID")
    private CardSet cardSet;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PLAYER_TO_CARD",
               joinColumns =
                    @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID"),
               inverseJoinColumns =
                    @JoinColumn(name = "CARD_ID", referencedColumnName = "ID"))
    private List<Player> players;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TEAM_TO_CARD",
            joinColumns =
                @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID"),
            inverseJoinColumns =
                @JoinColumn(name = "CARD_ID", referencedColumnName = "ID"))
    private List<Team> teams;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CardYear getCardYear() {
        return cardYear;
    }

    public void setCardYear(CardYear cardYear) {
        this.cardYear = cardYear;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String toString() {
        String res = cardYear + " " + cardSet + " ";

        for(Player player : players)
            res += player + " ";

        res += "#" + cardNum;

        return res;
    }
}
