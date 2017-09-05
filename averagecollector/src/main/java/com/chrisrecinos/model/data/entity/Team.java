package com.chrisrecinos.model.data.entity;

import javax.persistence.*;

/**
 * @author - Christopher Recinos
 *
 * This class represents a Baseball Team
 */

@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "TEAM_NAME")
    private String teamName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String toString() {
        return city + " " + teamName;
    }
}
