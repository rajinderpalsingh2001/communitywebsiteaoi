package com.nagarro.community.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.community.controller.UserController;
import com.nagarro.community.entity.Review;
import com.nagarro.community.service.UserService;

@Component
public class ReviewDto {
    int id;
    int pid;
    
    @Autowired
    UserDto user;

    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }


    int rating;
    // Boolean approved;
    String message;
    String date;
    String heading;
    

    public String getHeading() {
        return heading;
    }
    public void setHeading(String heading) {
        this.heading = heading;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // public Boolean getApproved() {
    //     return approved;
    // }

    // public void setApproved(Boolean approved) {
    //     this.approved = approved;
    // }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ReviewDto> toObj(List<Review> reviews) {
        List<ReviewDto> result = new ArrayList<>();

        for (Review review : reviews) {
            ReviewDto dto = new ReviewDto();

            dto.setId(review.getId());
            dto.setPid(review.getPid());

            user.setId(review.getUserid());
            dto.setUser(user); 
       
        
            dto.setRating(review.getRating());
            dto.setHeading(review.getHeading());
            // dto.setApproved(review.getApproved());
            dto.setMessage(review.getMessage());
            dto.setDate(review.getDate());

            result.add(dto);
        }
        return result;
    }

    public ReviewDto toObj(Review review) {
        ReviewDto dto = new ReviewDto();

        dto.setId(review.getId());
        dto.setPid(review.getPid());
        dto.setRating(review.getRating());
        dto.setHeading(review.getHeading());
        // dto.setApproved(review.getApproved());
        dto.setMessage(review.getMessage());

        user.setId(review.getUserid());
        dto.setUser(user);  

        dto.setDate(review.getDate());
        return dto;
    }
}
