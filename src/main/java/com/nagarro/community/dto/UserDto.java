package com.nagarro.community.dto;

import org.springframework.stereotype.Component;

import com.nagarro.community.entity.User;

@Component
public class UserDto{

    private int id;
    private String name;

    public UserDto toObj(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
