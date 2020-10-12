package com.tmi.FoodService.Repositories;

import com.tmi.FoodService.Models.Food;
import com.tmi.FoodService.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
    User findByEmail(String email);
    User findByPhone(String phone);
}
