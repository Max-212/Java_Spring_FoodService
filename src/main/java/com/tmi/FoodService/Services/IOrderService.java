package com.tmi.FoodService.Services;

import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.Request.OrderRequestModel;
import com.tmi.FoodService.Models.Response.OrderResponseModel;
import com.tmi.FoodService.Models.User;

import java.util.List;

public interface IOrderService {

    Order Add(Order order);

    Order Add(OrderRequestModel requestOrder, User user);

    Order GetById(Integer id);

    List<Order> GetAll();

    List<Order> GetByUser(User user);

    List<OrderResponseModel> GetAllResponseModel();

    List<OrderResponseModel> GetByUserResponseModel(User user);
}
