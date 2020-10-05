package com.tmi.FoodService.Repositories;

import com.tmi.FoodService.Models.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Integer> {
}
