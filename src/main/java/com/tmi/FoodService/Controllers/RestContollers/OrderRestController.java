package com.tmi.FoodService.Controllers.RestContollers;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Services.IFoodService;
import com.tmi.FoodService.Services.IOrderService;
import com.tmi.FoodService.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {

    @Autowired
    private IFoodService foodService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Food>> OrderFoods() {

        List<Food> foods = foodService.getAll();

        if(foods.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(foods,HttpStatus.OK);
    }

    @RequestMapping(value = "userOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> UserOrders() {
        User user = userService.FindByUsername("Trifanov");
        List<Order> orders = orderService.GetByUsername(user);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
