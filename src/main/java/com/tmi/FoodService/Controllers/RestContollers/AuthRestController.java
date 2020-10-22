package com.tmi.FoodService.Controllers.RestContollers;

import com.tmi.FoodService.Exceptions.UserValidationException;
import com.tmi.FoodService.Models.Request.AuthorizationRequestModel;
import com.tmi.FoodService.Models.Request.RegistrationRequestModel;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Security.Jwt.JwtTokenProvider;
import com.tmi.FoodService.Services.IUserService;
import com.tmi.FoodService.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final IUserService userService;

    private final UserValidator userValidator;

    @Autowired
    public AuthRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, IUserService userService, UserValidator userValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> Register(@Valid @RequestBody RegistrationRequestModel userDetails, BindingResult errors) throws MethodArgumentNotValidException {

        userValidator.validate(userDetails, errors);

        if(errors.hasErrors()){
            throw new UserValidationException(errors);
        }

        User user = userDetails.ToUser();
        userService.Register(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody AuthorizationRequestModel requestModel) {
        try {
            String username = requestModel.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestModel.getPassword()));
            User user = userService.FindByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
