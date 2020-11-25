package com.tmi.FoodService.Models.Request;

import com.tmi.FoodService.Models.FoodOrder;
import com.tmi.FoodService.Models.FoodOrderId;
import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderRequestModel {

    List<FoodOrderRequestModel> foods;

    public List<FoodOrderRequestModel> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodOrderRequestModel> foods) {
        this.foods = foods;
    }

//    public Order AddOrderInfo(Order order)
//    {
//        List<FoodOrder> foodOrders= new LinkedList<>();
//
//        for (FoodOrderRequestModel el:foods) {
//            FoodOrder foodOrder = new FoodOrder();
//            foodOrder.setCount(el.getCount());
//            foodOrder.setFoodOrderId(new FoodOrderId(el.getFoodId(),order.getId()));
//            foodOrders.add(foodOrder);
//        }
//
//        order.setFoodOrders(foodOrders);
//        return order;
//    }

}
