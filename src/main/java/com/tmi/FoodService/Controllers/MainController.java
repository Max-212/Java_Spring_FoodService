package com.tmi.FoodService.Controllers;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/")
    public String Index(Model model) {
        Iterable<Food> foods = foodRepository.findAll();
        model.addAttribute("foods", foods);
        return "index";
    }
}
