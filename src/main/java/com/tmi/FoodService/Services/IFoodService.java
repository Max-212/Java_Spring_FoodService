package com.tmi.FoodService.Services;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Models.User;

import java.util.List;

public interface IFoodService {

    Food Add(Food food);

    List<Food> getAll();

    Food FindByName(String name);

    Food FindById(Integer id);

    void DeleteById(Integer id);
}
