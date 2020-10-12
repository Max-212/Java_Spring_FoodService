package com.tmi.FoodService.Services.Implementation;


import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Repositories.UserRepository;
import com.tmi.FoodService.Services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User Register(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User FindByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public User FindById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User FindByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User FindByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public void DeleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
