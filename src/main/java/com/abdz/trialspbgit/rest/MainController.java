package com.abdz.trialspbgit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abdz.trialspbgit.service.PowerService;
@Controller
@RequestMapping("/")
public class MainController {

    public PowerService powerService;
     @Autowired
     public MainController(PowerService powerService) {
         this.powerService = powerService;
     }

    @GetMapping("/")
    public String hello(){
        return "redirect:/test/login";
    }
    
}
