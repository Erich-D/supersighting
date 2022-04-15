/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cec.superhero.controller;

import com.cec.superhero.dao.SuperHeroDao;
import com.cec.superhero.dao.SuperHeroDaoDbImpl.Models;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.cec.superhero.models.Super;
import com.cec.superhero.models.Location;
import com.cec.superhero.models.Sighting;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
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
    SuperHeroDao dao;
    
    @GetMapping("index")
    public String homePage(Model model, HttpSession session) {
        String head = (false) ? model.getAttribute("test").toString():"Superhero Sightings";
        List<Sighting> sightings = dao.findAllSight().stream().limit(10)
                .collect(Collectors.toList());
        System.out.println(session.getAttribute("test"));
        //Puts text in header
        model.addAttribute("headerText", head);
        //Sightings for the ticker tape
        model.addAttribute("sightings",sightings);
        
        return "index";
    }

    @GetMapping("heroes")
    public String heroesPage(Model model){
        List<Super> supers = dao.findAllSups();
        List<java.lang.reflect.Field> fds = Arrays.stream(Super.class.getDeclaredFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .collect(Collectors.toList());
        model.addAttribute("headerText", "Superheroes and Villians");
        model.addAttribute("supers",supers);
        return "heroes";
    } 
    
    @PostMapping("heroes")
     public String supersForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/heroes";
    }
     
    @GetMapping("superpowers")
    public String powersPage(Model model, HttpSession session){
        model.addAttribute("test","Hello World");
        session.setAttribute("test", "Hello World");
        return "superpowers";
    }
    
    @PostMapping("superpowers")
     public String powersForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/superpowers";
    }
     
    @GetMapping("locations")
    public String locationsPage(Model model){
        return "locations";
    }
    
    @PostMapping("locations")
     public String locationsForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/locations";
    } 
     
    @GetMapping("organizations")
    public String organizationsPage(Model model){
        return "organizations";
    }
    
    @PostMapping("organizations")
     public String organizationsForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/organizations";
    }
     
    @GetMapping("sightings")
    public String sightingsPage(Model model){
        Field date = new Field("Date:",LocalDateTime.now().toString(),"","datetime-local");
        Field tf1 = new Field("another name","another value","another size","text");
        List<Field> fields = new ArrayList<Field>();
        fields.add(date);
        fields.add(tf1);
        //model.addAttribute("super", sup);
        model.addAttribute("fields", fields);
        return "sightings";
    }
    
    @PostMapping("sightings")
     public String sightingsForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/sightings";
     }  
}
