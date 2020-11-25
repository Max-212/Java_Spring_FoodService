package com.tmi.FoodService.Models.Response;

import com.tmi.FoodService.Models.FoodOrder;
import com.tmi.FoodService.Models.Order;

import java.util.*;

public class OrderResponseModel {

    private String username;
    private List<Map<String,String>> foods;

    public OrderResponseModel(Order order) {

        foods = new LinkedList<>();
        username = order.getUser().getUsername();

        for(FoodOrder el: order.getFoodOrders()){
            Map<String,String> food = new LinkedHashMap<>();
            food.put("Title",el.getFood().getTitle());
            food.put("Count",el.getCount().toString());
            foods.add(food);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Map<String, String>> getFoods() {
        return foods;
    }

    public void setFoods(List<Map<String, String>> foods) {
        this.foods = foods;
    }
}
