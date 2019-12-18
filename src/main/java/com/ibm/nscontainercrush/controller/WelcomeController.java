package com.ibm.nscontainercrush.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("Sanjoy SA", "Vijay Bajaj", "Sanjib Pal", "Amitava Nandy", "Arkoprava Mukherjee");

    @GetMapping("/")
    public ModelAndView main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return new ModelAndView("welcome"); //view
    }
    
    @GetMapping("/launchKoolApp")
    public ModelAndView launchKoolApp(){
          return new ModelAndView("koolApp"); //view
    }
    
}
