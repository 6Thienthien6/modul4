package com.example.sandwichcondiments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CondimentsController {
    @GetMapping("/sandwich")
    public String listCondiments(){
        return "index";
    }
    @PostMapping("/save")
    public String saveCondiments(@RequestParam("condiment") String[] condiment, Model model){
        model.addAttribute("condiment", condiment);
        return "save";
    }
}
