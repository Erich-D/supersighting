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
import com.cec.superhero.models.Organization;
import com.cec.superhero.models.Power;
import com.cec.superhero.models.Sighting;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
<<<<<<< Updated upstream
        List<Power> powers = dao.findAllPow();
        List<Organization> organs = dao.findAllOrgs();
        model.addAttribute("powers", powers);
        model.addAttribute("organs", organs);
=======
        List<java.lang.reflect.Field> fds = Arrays.stream(Super.class.getDeclaredFields())
                .filter(f -> Modifier.isPublic(f.getModifiers()))
                .collect(Collectors.toList());
        // model.addAttribute("headerText", "Superheroes and Villians");
        model.addAttribute("fields", fields);
>>>>>>> Stashed changes
        model.addAttribute("supers",supers);
        return "heroes";
    }

    @GetMapping("deleteSuper")
    public String deleteSuper(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteById(Models.SUPERS, id);
        return "redirect:/heroes";
    }

    @GetMapping("editSuper")
    public String editSuper(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Super sup = (Super)dao.findById(Models.SUPERS, id);
        List<Power> pows = dao.findAllPow();
        List<Organization> orgs = dao.findAllOrgs();
        model.addAttribute("super", sup);
        model.addAttribute("pows", pows);
        model.addAttribute("orgs", orgs);
        model.addAttribute("headerText", "Editing "+sup.getName());
        return "editPage";
    }

    @PostMapping("editSuper")
    public String updateSuper(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Super sp = (Super)dao.findById(Models.SUPERS, id);
        List<Power> pows = dao.findAllPow();
        List<Organization> orgs = dao.findAllOrgs();
        sp.setName(request.getParameter("name"));
        sp.setDescr(request.getParameter("descr"));
        boolean hr = (request.getParameter("hero").equals("hero")) ? true:false;
        sp.setHero(hr);
        for(Power pow : pows){
            if(request.getParameter(pow.getName()) != null){
                sp.getPowers().add(pow);
            }else{
                if(sp.getPowers().contains(pow)){sp.getPowers().remove(pow);}
            }
        }
        for(Organization org : orgs){
            if(request.getParameter(org.getName()) != null){
                sp.getOrganizations().add(org);
            }
        }
        System.out.println(request.getParameter("Immeasurable Speed"));
        dao.saveOrUpdate(Models.SUPERS, sp);
        return "redirect:/heroes";
    }

    @PostMapping("heroes")
     public String supersForm(HttpServletRequest request) {
        List<Organization> organs = dao.findAllOrgs();
        List<Power> powers = dao.findAllPow();
        //get inputs
        String name = request.getParameter("name");
        String descr = request.getParameter("descr");
        boolean hero = (request.getParameter("hero")=="true") ? true:false;
        String[] powersl = request.getParameterValues("powers");
        String[] organsl = request.getParameterValues("organs");
        //create hero
        Super newSup = new Super();
        newSup.setName(name);
        newSup.setDescr(descr);
        newSup.setHero(hero);
        newSup = (Super)dao.saveOrUpdate(Models.SUPERS, newSup);
        //add powers and orgs if exist
        if(powersl != null){
            for(String power : powersl){
                newSup.getPowers().add(powers.stream()
                        .filter(p -> p.getName().equals(power))
                        .findFirst().orElse(null));
            }
        }
        if(organsl != null){
            for(String organ : organsl){
                newSup.getOrganizations().add(organs.stream()
                         .filter(o -> o.getName().equals(organ))
                        .findFirst().orElse(null));
                System.out.println(organ);
            }
        }

        dao.saveOrUpdate(Models.SUPERS, newSup);
        return "redirect:/heroes";
    }

    @GetMapping("superpowers")
    public String powersPage(Model model, HttpSession session){
        List<Power> powers = dao.findAllPow();
        model.addAttribute("powers",powers);
        session.setAttribute("test", "Hello World");
        return "superpowers";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteById(Models.POWERS, id);
        return "redirect:/superpowers";
    }

    @PostMapping("superpowers")
     public String powersForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        String descr = request.getParameter("descr");
        Power np = new Power();
        np.setName(name);
        np.setDescr(descr);
        dao.saveOrUpdate(Models.POWERS, np);
        return "redirect:/superpowers";
    }

    @GetMapping("locations")
    public String locationsPage(Model model){
<<<<<<< Updated upstream
        List<Location> locs = dao.findAllLocs();
        model.addAttribute("locs",locs);
        return "locations";
    }

    @GetMapping("deleteLocal")
    public String deleteLocal(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteById(Models.LOCATIONS, id);
        return "redirect:/locations";
    }

    @PostMapping("locations")
     public String locationsForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        String descr = request.getParameter("descr");
        String address = request.getParameter("address");
        float lat = Float.parseFloat(request.getParameter("lat"));
        float log = Float.parseFloat(request.getParameter("log"));
        Location nl = new Location();
        nl.setName(name);
        nl.setDescr(descr);
        nl.setAddress(address);
        nl.setLatitude(lat);
        nl.setLongitude(log);
        nl = (Location)dao.saveOrUpdate(Models.LOCATIONS, nl);

        return "redirect:/locations";
    }

    @GetMapping("organizations")
    public String organizationsPage(Model model){
        List<Organization> orgs = dao.findAllOrgs();
        model.addAttribute("orgs",orgs);
        return "organizations";
    }

    @GetMapping("deleteOrg")
    public String deleteOrg(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteById(Models.ORGANIZATIONS, id);
        return "redirect:/organizations";
    }

    @PostMapping("organizations")
     public String organizationsForm(HttpServletRequest request) {
        String name = request.getParameter("name");
        String descr = request.getParameter("descr");
        String address = request.getParameter("address");
        Organization org = new Organization();
        org.setName(name);
        org.setDescr(descr);
        org.setAddress(address);
        dao.saveOrUpdate(Models.ORGANIZATIONS, org);

        return "redirect:/organizations";
    }

    @GetMapping("sightings")
    public String sightingsPage(Model model){
        List<Sighting> sightings = dao.findAllSight();
        model.addAttribute("sightings", sightings);
        return "sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteById(Models.SIGHTINGS, id);
        return "redirect:/sightings";
    }

    @PostMapping("sightings")
     public String sightingsForm(HttpServletRequest request) {
        List<Location> locs = dao.findAllLocs();
        List<Super> supers = dao.findAllSups();
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date"));
        String sp = request.getParameter("supers");
        String lc = request.getParameter("locs");
        Sighting sg = new Sighting();
        sg.setDate(date);
        if(sp != null || !sp.isBlank() ){
            sg.setSuperp(supers.stream()
                        .filter(p -> p.getName().equals(sp))
                        .findFirst().orElse(null));
        }
        if(lc != null || !lc.isBlank()){
            sg.setLocation(locs.stream()
                        .filter(p -> p.getName().equals(lc))
                        .findFirst().orElse(null));
        }
        try{
            dao.saveOrUpdate(Models.SIGHTINGS, sg);
        }catch(DataIntegrityViolationException e){
            System.out.println(e.toString());
        }

        return "redirect:/sightings";
     }
=======
        model.addAttribute("locations", dao.findAllLocs());
        return "locations";
    }

    @GetMapping("organizations")
    public String organizationsPage(Model model){
        model.addAttribute("organizations", dao.findAllOrgs());
        return "organizations";
    }

    @GetMapping("sightings")
    public String sightingsPage(Model model){
        model.addAttribute("sightings", dao.findAllSight());
        return "sightings";
    }

    @GetMapping("superpowers")
    public String superpowersPage(Model model){
        model.addAttribute("superpowers", dao.findAllPow());
        return "superpowers";
    }
>>>>>>> Stashed changes
}
