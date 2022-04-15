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
    
    @GetMapping("/")
    public String homePage(Model model, HttpSession session) {
        String head = (false) ? model.getAttribute("test").toString():"Superhero Sightings";
        List<Sighting> sightings = dao.findAllSight().stream().limit(10)
                .collect(Collectors.toList());
        System.out.println(session.getAttribute("test"));
        //Puts text in header
        model.addAttribute("headerText", head);
        //Sightings for the ticker tape
        model.addAttribute("sightings",sightings);
        
        return "home";
    }

    @GetMapping("supers")
    public String supersPage(Model model){
        List<Super> supers = dao.findAllSups();
        List<java.lang.reflect.Field> fds = Arrays.stream(Super.class.getDeclaredFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .collect(Collectors.toList());
        model.addAttribute("headerText", "Superheroes and Villians");
        model.addAttribute("supers",supers);
        return "heroes";
    } 
    
    @PostMapping("supers")
     public String supersForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/supers";
    }
     
    @GetMapping("powers")
    public String powersPage(Model model, HttpSession session){
        model.addAttribute("test","Hello World");
        session.setAttribute("test", "Hello World");
        return "redirect:/?q=powers";
    }
    
    @PostMapping("powers")
     public String powersForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/powers";
    }
     
    @GetMapping("locations")
    public String locationsPage(Model model){
        return "home";
    }
    
    @PostMapping("locations")
     public String locationsForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/locations";
    } 
     
    @GetMapping("organizations")
    public String organizationsPage(Model model){
        return "home";
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
    
<<<<<<< Updated upstream
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
=======
    @PostMapping("sightings")
     public String sightingsForm(HttpServletRequest request) {
         //name = request.getParameter("formFirstName");
         //number = Integer.parseInt(request.getParameter("formNumber"));

         return "redirect:/sightings";
>>>>>>> Stashed changes
    }
}
