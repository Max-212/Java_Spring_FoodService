package com.tmi.FoodService.Services.Implementation;

import com.tmi.FoodService.Models.FoodOrder;
import com.tmi.FoodService.Models.FoodOrderId;
import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.Request.FoodOrderRequestModel;
import com.tmi.FoodService.Models.Request.OrderRequestModel;
import com.tmi.FoodService.Models.Response.OrderResponseModel;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Repositories.OrderRepository;
import com.tmi.FoodService.Services.IFoodService;
import com.tmi.FoodService.Services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.LinkedList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    OrderRepository orderRepository;

    @Autowired
    private IFoodService foodService;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order Add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order Add(OrderRequestModel requestOrder, User user) {

        Order order = new Order();
        order.setUser(user);
        orderRepository.save(order);

        List<FoodOrder> foodOrders= new LinkedList<>();
        for (FoodOrderRequestModel el:requestOrder.getFoods()) {
            FoodOrder foodOrder = new FoodOrder(order,foodService.FindById(el.getFoodId()),el.getCount());
            foodOrder.setFoodOrderId(new FoodOrderId(el.getFoodId(),order.getId()));
            foodOrders.add(foodOrder);
        }
        order.setFoodOrders(foodOrders);

        return orderRepository.save(order);
    }

    @Override
    public Order GetById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> GetAll() {
        return orderRepository.findAll();
    }


    @Override
    public List<Order> GetByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public List<OrderResponseModel> GetAllResponseModel() {
        List<OrderResponseModel> orders = new LinkedList<>();

        for (Order el: GetAll()) {
            orders.add(new OrderResponseModel(el));
        }

        return orders;
    }


    @Override
    public List<OrderResponseModel> GetByUserResponseModel(User user) {
        List<OrderResponseModel> orders = new LinkedList<>();

        for (Order el: GetByUser(user)) {
            orders.add(new OrderResponseModel(el));
        }

        return orders;
    }
}
