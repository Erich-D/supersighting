/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cec.superhero.controller;

import com.cec.superhero.dao.SuperHeroDao;
import com.cec.superhero.dao.SuperHeroDaoDbImpl;
import com.cec.superhero.dao.SuperHeroDaoDbImpl.Models;
import com.cec.superhero.repositories.LocationRepository;
import com.cec.superhero.repositories.OrganizationRepository;
import com.cec.superhero.repositories.PowerRepository;
import com.cec.superhero.repositories.SightingRepository;
import com.cec.superhero.repositories.SuperRepository;
import javax.servlet.http.HttpServletRequest;
import com.cec.superhero.models.Super;
import com.cec.superhero.models.Location;
import com.cec.superhero.models.Power;
import com.cec.superhero.models.Sighting;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author etdeh
 */
@Controller
public class MainController {
    
    @Autowired
    LocationRepository locations;  
    
    @Autowired
    SuperRepository supers;
    /*
    @Autowired
    SightingRepository sighting;
    @Autowired
    PowerRepository power;
    @Autowired
    OrganizationRepository organ;
    */
    @Autowired
    SuperHeroDao dao;
    
    String name = "John";
    int number = 2;

    private final String DELIMITER = ",";
    
    private class Field{
        private String name;
        private String value;
        private String size;

        public Field(String name, String value, String size) {
            this.name = name;
            this.value = value;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
        
        
    }
    
    @GetMapping("index")
    public String testPage(Model model) {
       //number = (number > supers.count()) ? (int)supers.count():number;
        Super sup = (Super)dao.findById(Models.SUPERS,number);
        System.out.println(dao.getLocsWhereSuperSeen(sup).toString());
        Location loc = (Location)dao.findById(Models.LOCATIONS, 2);
        System.out.println(dao.getSupersSeenAtLoc(loc).toString());
        List<Sighting> sightings = dao.findAllSight();
        Field tf = new Field("a name","a value","a size");
        Field tf1 = new Field("another name","another value","another size");
        List<Field> fields = new ArrayList<Field>();
        fields.add(tf);
        fields.add(tf1);
        //model.addAttribute("super", sup);
        model.addAttribute("fields", fields);
        model.addAttribute("sightings",sightings);
        return "index";
    }

    @PostMapping("testForm")
    public String testForm(HttpServletRequest request) {
        name = request.getParameter("formFirstName");
        number = Integer.parseInt(request.getParameter("formNumber"));
        
        return "redirect:/test";
    }

    @GetMapping("heroes")
    public String heroesPage(Model model){
        //add fields
        Field tf = new Field("a name","a value","a size");
        Field tf1 = new Field("another name","another value","another size");
        List<Field> fields = new ArrayList<Field>();
        fields.add(tf);
        fields.add(tf1);

        // display supers
        List<Super> supers = dao.findAllSups();
        List<java.lang.reflect.Field> fds = Arrays.stream(Super.class.getDeclaredFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .collect(Collectors.toList());
        // model.addAttribute("headerText", "Superheroes and Villians");
        // model.addAttribute("fields", fields);
        model.addAttribute("supers",supers);
        return "heroes";
    } 

    @PostMapping("addHero")
    public String addHero(Super superhero, HttpServletRequest request){
        /* String name = request.getParameter("name");
        String description = request.getParameter("description");
        String superpowers = request.getParameter("superpower");
        String superpowersDesc = request.getParameter("superpowerDesc");
        String organizations = request.getParameter("organizations");
        
        String[] superpowerNames =  superpowers.split(DELIMITER);
        String[] superpowerDescs = superpowersDesc.split(DELIMITER);
        if(superpowerNames.length != superpowerDescs.length){
            return "redirect:/error";
        }

        Super super = new Super();
        super.setName(name);
        super.setDescr(description);


        for(int i = 0; i != superpowerNames.length; i++){
            Power power = new Power();
            power.setName(superpowerNames[i]);
            power.setDescr(superpowerDescs[i]);
            power.addSuper(name);

            power.add
        } */

        supers.save(superhero);

        return "redirect:/heroes";
    }

    @GetMapping("locations")
    public String locationsPage(Model model){
        model.addAttribute("locations", locations.findAll());
        return "locations";
    }
}
