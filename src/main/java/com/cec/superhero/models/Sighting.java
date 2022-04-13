/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cec.superhero.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author etdeh
 */
@Entity
@Table (name="sightings")
public class Sighting {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(name = "location_id", insertable = false, updatable = false)
    private int locationId;
    @Column(name = "super_id", insertable = false, updatable = false) 
    private int superId;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @ManyToOne
    @JoinColumn(name = "super_id", nullable = false)
    private Super superp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Super getSuperp() {
        return superp;
    }

    public void setSuperp(Super superp) {
        this.superp = superp;
    }
    
    
}
