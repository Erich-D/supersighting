/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cec.superhero.dao;

import com.cec.superhero.dao.SuperHeroDaoDbImpl.Models;
//import com.cec.superhero.models.Power;
import com.cec.superhero.models.Location;
import com.cec.superhero.models.Organization;
import com.cec.superhero.models.Sighting;
import com.cec.superhero.models.Super;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author etdeh
 */
@SpringBootTest
public class SuperHeroDaoDbImplTest {
    
    @Autowired
    SuperHeroDao dao;
    
    public SuperHeroDaoDbImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        /*
        List<Super> sups = dao.findAllSups();
        for(Super sup:sups){dao.deleteById(Models.SUPERS, sup.getId());}
        List<Sighting> sts = dao.findAllSight();
        for(Sighting st:sts){dao.deleteById(Models.SIGHTINGS, st.getId());}
        List<Power> pows = dao.findAllPow();
        for(Power pow:pows){dao.deleteById(Models.POWERS, pow.getId());}
        List<Organization> orgs = dao.findAllOrgs();
        for(Organization org:orgs){dao.deleteById(Models.ORGANIZATIONS, org.getId());}
        List<Location> locs = dao.findAllLocs();
        for(Location loc:locs){dao.deleteById(Models.LOCATIONS, loc.getId());}
        */
    }
    
    @AfterEach
    public void tearDown() {
    }
    
}
