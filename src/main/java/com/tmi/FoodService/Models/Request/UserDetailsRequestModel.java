package com.tmi.FoodService.Models.Request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailsRequestModel {

    @NotNull(message = "Login cannot be null")
    private String login;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull
    private String phone;

    @NotNull
    @Size(min = 8, max = 16, message = "The password must be equal or greater than 8 characters and less than 16 characters")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
