package com.tmi.FoodService.Controllers.RestContollers;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.Request.FoodOrderRequestModel;
import com.tmi.FoodService.Models.Request.OrderRequestModel;
import com.tmi.FoodService.Models.Request.RegistrationRequestModel;
import com.tmi.FoodService.Models.Response.OrderResponseModel;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Security.Jwt.JwtTokenProvider;
import com.tmi.FoodService.Services.IFoodService;
import com.tmi.FoodService.Services.IOrderService;
import com.tmi.FoodService.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponseModel>> Orders(HttpServletRequest request) {

        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.FindByUsername(username);

        List<OrderResponseModel> orders = orderService.GetByUserResponseModel(user);

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> AddOrder(@RequestBody OrderRequestModel orderRequestModel, HttpServletRequest request){

        //needed to JSON validation

        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.FindByUsername(username);

        Order order = orderService.Add(orderRequestModel,user);

        return new ResponseEntity<>(order,HttpStatus.OK);
    }
}
