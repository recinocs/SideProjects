package com.chrisrecinos.model.data.form;

import java.io.Serializable;

/**
 * @author - Christopher Recinos
 */

public class SearchForm implements Serializable {
    private String player;
    private Long teamId;
    private Long setId;
    private String memType;
    private String cardNum;
    private String insertType;
    private String parallelType;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
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
}
