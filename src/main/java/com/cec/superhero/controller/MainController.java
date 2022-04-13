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
    /*
    @Autowired
    LocationRepository location;  
    @Autowired
    SuperRepository supers;
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
    
   @GetMapping("test")
   public String testPage(Model model) {
       //number = (number > supers.count()) ? (int)supers.count():number;
       Super sup = (Super)dao.findById(Models.SUPERS,number);
       System.out.println(dao.getLocsWhereSuperSeen(sup).toString());
       model.addAttribute("super", sup);
       model.addAttribute("firstName", name);

       return "test";
   }
   
   @PostMapping("testForm")
    public String testForm(HttpServletRequest request) {
        name = request.getParameter("formFirstName");
        number = Integer.parseInt(request.getParameter("formNumber"));
        
        return "redirect:/test";
    }
}
