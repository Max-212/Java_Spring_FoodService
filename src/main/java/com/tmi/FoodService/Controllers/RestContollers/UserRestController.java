package com.tmi.FoodService.Controllers.RestContollers;

import javax.validation.Valid;

import com.tmi.FoodService.Exceptions.UserValidationException;
import com.tmi.FoodService.Models.Request.UserDetailsRequestModel;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Services.IUserService;
import com.tmi.FoodService.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> GetAllUsers() {

        List<User> users = userService.getAll();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> Register(@Valid @RequestBody UserDetailsRequestModel userDetails, BindingResult errors) throws MethodArgumentNotValidException {

        userValidator.validate(userDetails, errors);

        if(errors.hasErrors()){
            throw new UserValidationException(errors);
        }

        if(userDetails == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = new User(userDetails.getLogin(),userDetails.getEmail(),userDetails.getPhone(),userDetails.getPassword());
        userService.Register(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
}
