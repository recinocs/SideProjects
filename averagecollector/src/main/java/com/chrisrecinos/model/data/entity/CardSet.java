package com.chrisrecinos.model.data.entity;

import javax.persistence.*;

/**
 * @author - Christopher Recinos
 *
 * This class represents a Card Set that has been released.
 * Because a set might be released over multiple years, there
 * is no associated year for the set.
 */

@Entity
@Table(name = "CARD_SET")
public class CardSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @Column(name = "SET_NAME")
    private String setName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String toString() {
        return brand.getBrandName() + " " + setName;
    }
}
