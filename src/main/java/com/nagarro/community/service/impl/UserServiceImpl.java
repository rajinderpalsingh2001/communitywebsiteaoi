package com.nagarro.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.nagarro.community.dto.UserDto;
import com.nagarro.community.entity.User;
import com.nagarro.community.repository.UserRepository;
import com.nagarro.community.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserDto dto;

    public UserDto checkUser(String email, String password){
        User user = repo.findByEmailAndPassword(email, password);
        if(user!=null){
            return dto.toObj(user);
        }
        return null;
    }

    public UserDto addUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return dto.toObj(repo.save(user));
    }

    public User findByEmail(String email){
        return repo.findByEmail(email);
    }

    public User findUserById(int id){
        return repo.findById(id).orElse(null);
    }
    
}
