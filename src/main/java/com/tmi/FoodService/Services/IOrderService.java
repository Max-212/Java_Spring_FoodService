package com.tmi.FoodService.Services;

import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.User;

import java.util.List;

public interface IOrderService {

    Order Add(Order order);

    List<Order> GetAll();

    List<Order> GetByUsername(User user);

}
