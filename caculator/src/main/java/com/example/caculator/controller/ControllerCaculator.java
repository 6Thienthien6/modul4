package com.example.caculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerCaculator {
    @GetMapping("/calculator")
    public String getCaculator() {
        return "index";
    }

    @PostMapping("/request")
    public String setRequest(@RequestParam("number1") double number1,
                             @RequestParam("number2") double number2,
                             @RequestParam("caculator") char caculator, Model model) {
        double result = 0;
        switch (caculator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                result = number1 / number2;
                break;
        }
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        model.addAttribute("result", result);
        return "index";
    }
}
