package com.tmi.FoodService.Controllers.RestContollers;

import com.tmi.FoodService.Exceptions.RestValidationException;
import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Models.Order;
import com.tmi.FoodService.Models.Request.AddFoodRequestModel;
import com.tmi.FoodService.Models.Request.UpdateFoodRequestModel;
import com.tmi.FoodService.Models.Response.OrderResponseModel;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Services.IFoodService;
import com.tmi.FoodService.Services.IOrderService;
import com.tmi.FoodService.Services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class AdminRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IFoodService foodService;

    @RequestMapping(value = "users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> GetAllUsers() {

        List<User> users = userService.getAll();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @RequestMapping(value = "orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponseModel>> GetAllOrders(){

        List<OrderResponseModel> orders = orderService.GetAllResponseModel();

        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @RequestMapping(value = "orders/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponseModel> AcceptOrder(@PathVariable(value = "id") Integer id){

        if(orderService.GetById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Order order = orderService.GetById(id);
        orderService.SetDelivered(order);


        OrderResponseModel response = new OrderResponseModel(order);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @RequestMapping(value = "food", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food> AddFood(@Valid @RequestBody AddFoodRequestModel foodDto, BindingResult errors){

        if(errors.hasErrors()){
            throw new RestValidationException(errors);
        }

        Food food = foodDto.ToFood();
        foodService.Add(food);

        return new ResponseEntity<>(food,HttpStatus.OK);
    }

    @RequestMapping(value = "food", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Food> UpdateFood(@Valid @RequestBody UpdateFoodRequestModel foodDto, BindingResult errors) {

        if(errors.hasErrors()){
            throw new RestValidationException(errors);
        }

        Food food = foodService.FindById(foodDto.getId());
        food.setTitle(foodDto.getTitle());
        food.setImage(foodDto.getImage());
        food.setPrice(foodDto.getPrice());
        food.setWeight(foodDto.getWeight());

        food = foodService.Add(food);

        return new ResponseEntity<>(food,HttpStatus.OK);
    }

    @RequestMapping(value = "food/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Food>> DeleteFood(@PathVariable(value = "id") Integer id){

        if(foodService.FindById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        foodService.DeleteById(id);

        return new ResponseEntity<>(foodService.getAll(),HttpStatus.OK);
    }
}
