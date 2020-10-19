package com.tmi.FoodService.Services.Implementation;


import com.tmi.FoodService.Models.Role;
import com.tmi.FoodService.Models.User;
import com.tmi.FoodService.Repositories.RoleRepository;
import com.tmi.FoodService.Repositories.UserRepository;
import com.tmi.FoodService.Services.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User Register(User user) {

        Role roleUser = roleRepository.findByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User FindByUsername(String username) {
        return userRepository.findByUsername(username);
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
