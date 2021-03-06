package com.tmi.FoodService.Repositories;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    Food findByTitle(String title);

}
