package com.tmi.FoodService.Services.Implementation;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Repositories.FoodRepository;
import com.tmi.FoodService.Services.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService implements IFoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food Add(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAll() {
        return foodRepository.findAll();
    }

    @Override
    public Food FindByName(String name) {
        return foodRepository.findByTitle(name);
    }

    @Override
    public Food FindById(Integer id) {
        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteById(Integer id) {
        foodRepository.deleteById(id);
    }
}
