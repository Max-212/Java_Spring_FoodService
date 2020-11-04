package com.tmi.FoodService.Services.Implementation;

import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Repositories.OrderRepository;
import com.tmi.FoodService.Services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order Add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> GetAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> GetByUsername(User user) {
        return orderRepository.findByUser(user);
    }
}
