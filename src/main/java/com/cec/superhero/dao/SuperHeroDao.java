/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cec.superhero.dao;

import com.cec.superhero.dao.SuperHeroDaoDbImpl.Models;
import com.cec.superhero.models.Location;
import com.cec.superhero.models.Organization;
import com.cec.superhero.models.Power;
import com.cec.superhero.models.Sighting;
import com.cec.superhero.models.Super;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author etdeh
 */
public interface SuperHeroDao {
    List<Super> getSupersSeenAtLoc(Location loc);
    List<Location> getLocsWhereSuperSeen(Super sup);
    List<Sighting> getSightingsByDate(LocalDateTime date);
    List<Super> getMembersOfOrg(String name);
    List<Organization> getSuperBelongsTo(String name);
    Sighting reportNewSighting(Location loc, Super sup);
    //views objects
    Object findById(Models type, int id);
    //for creating or updating objects
    Object saveOrUpdate(Models type, Object ob);
    //for deleting objects
    Boolean deleteById(Models type, int id);
    //checks if object exists
    Boolean existsById(Models type, int id);
    //finds all objects of type
    List<Location> findAllLocs();
    List<Organization> findAllOrgs();
    List<Power> findAllPow();
    List<Sighting> findAllSight();
    List<Super> findAllSups();
    //gets object count
    long count(Models type);
}
