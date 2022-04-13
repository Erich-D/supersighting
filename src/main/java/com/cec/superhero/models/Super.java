/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cec.superhero.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author etdeh
 */
@Entity
@Table (name="supers")
public class Super {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(nullable = false)
    private String name;
    @Column
    private String descr;
    @Column
    private Boolean hero;
    @ManyToMany
    @JoinTable(name = "powers_has_super",
            joinColumns = {@JoinColumn(name = "super_id")},
            inverseJoinColumns = {@JoinColumn(name = "powers_id")})
    private List<Power> powers;
    @ManyToMany
    @JoinTable(name = "super_has_organization",
            joinColumns = {@JoinColumn(name = "super_id")},
            inverseJoinColumns = {@JoinColumn(name = "organization_id")})
    private List<Organization> organizations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Boolean getHero() {
        return hero;
    }

    public void setHero(Boolean hero) {
        this.hero = hero;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        return "Super{" + "id=" + id + ", name=" + name + ", descr=" + descr + ", hero=" + hero + ", powers=" + powers + ", organizations=" + organizations + '}';
    }
    
    
}
