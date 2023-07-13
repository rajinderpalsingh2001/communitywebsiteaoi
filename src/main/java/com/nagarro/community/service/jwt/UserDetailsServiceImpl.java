package com.nagarro.community.service.jwt;

import com.nagarro.community.entity.User;
import com.nagarro.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Configuration
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;
    private User user;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {

        // Write Logic to get the user from the DB
        user = service.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found", null);
        }

        // return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        //         new ArrayList<>());
        return user;
    }

}