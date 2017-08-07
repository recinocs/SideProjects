package com.chrisrecinos.model.data.entity;

import javax.persistence.*;

/**
 * @author - Christopher Recinos
 */

@Entity
@Table (name = "BRAND")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "BRAND_NAME")
    private String brandName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String toString() {
        return getBrandName();
    }
}
