package com.tmi.FoodService.Repositories;

import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
