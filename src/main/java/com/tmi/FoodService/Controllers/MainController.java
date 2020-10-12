package com.tmi.FoodService.Controllers;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private FoodRepository foodRepository;


    @GetMapping("/")
    public String Index(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String LoginPage(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String RegisterPage(Model model){
        return "register";
    }

    public MainController() {
    }


}
