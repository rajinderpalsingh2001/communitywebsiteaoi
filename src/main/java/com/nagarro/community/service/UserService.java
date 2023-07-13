package com.nagarro.community.service;

import com.nagarro.community.dto.UserDto;
import com.nagarro.community.entity.User;

public interface UserService {


    UserDto checkUser(String email, String password);

    User findByEmail(String email);

    UserDto addUser(User user);

    User findUserById(int id);
}
