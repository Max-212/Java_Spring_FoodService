package com.tmi.FoodService.Services;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFoodService {

    Food Add(Food food);

    List<Food> getAll();

    Page<Food> getPage(Pageable pageable);

    Food FindByName(String name);

    Food FindById(Integer id);

    void DeleteById(Integer id);
}
