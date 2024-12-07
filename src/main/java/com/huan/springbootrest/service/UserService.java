package com.huan.springbootrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.huan.springbootrest.model.User;
import com.huan.springbootrest.repo.UserRepo;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return repo.save(user);

    }

    public boolean login(String username, String password) {
        Optional<User> userOptional = Optional.ofNullable(repo.findByUsername(username));

        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }
}
