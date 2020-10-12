package com.tmi.FoodService.Validator;

import com.tmi.FoodService.Models.Request.UserDetailsRequestModel;
import com.tmi.FoodService.Services.IUserService;
import com.tmi.FoodService.Services.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDetailsRequestModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDetailsRequestModel user = (UserDetailsRequestModel) o;

        if(userService.FindByUsername(user.getLogin()) != null) {
            errors.rejectValue("login", "", "This login is already in use");
        }

        if(userService.FindByEmail(user.getEmail()) != null){
            errors.rejectValue("email", "", "This Email is already in use");
        }

        if(userService.FindByPhone(user.getPhone()) != null){
            errors.rejectValue("phone", "", "This phone is already in use");
        }
    }
}
