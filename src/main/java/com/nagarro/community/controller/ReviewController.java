package com.nagarro.community.controller;


import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.community.dto.ReviewDto;
import com.nagarro.community.entity.Review;
import com.nagarro.community.service.ReviewService;
import com.nagarro.community.service.UserService;

@RestController
@RequestMapping(path = "/api/reviews")
@CrossOrigin("*")
public class ReviewController {
    @Autowired
    private ReviewService  service;

    @Autowired
    ReviewDto dto;

    @Autowired
    private UserService userService;
    
    
    @PostMapping(path = "")
    @RolesAllowed("USER")
    public ResponseEntity<ReviewDto> addReview(@RequestBody Review review) {
        try {
            ReviewDto reviewRes = service.addReview(review);
            return new ResponseEntity<ReviewDto>(reviewRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ReviewDto>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/approve/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<ReviewDto> approveReview(@PathVariable int id) {
        try {
            ReviewDto reviewRes = service.approveReview(id);

            if(reviewRes==null){
                return new ResponseEntity<ReviewDto>(null,null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<ReviewDto>(reviewRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ReviewDto>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable int id) {
        
        try {
            ReviewDto reviewRes = service.deleteReview(id);
            
            if(reviewRes==null){
                return new ResponseEntity<ReviewDto>(null,null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<ReviewDto>(reviewRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ReviewDto>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/approved/{pid}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable int pid) {
        try {
            List<ReviewDto> reviewRes = service.getReviews(pid);
            for(int i=0;i<reviewRes.size();i++){
                int userid = reviewRes.get(i).getUser().getId();
                reviewRes.get(i).getUser().setName(userService.findUserById(userid).getName());
            }
            return new ResponseEntity<List<ReviewDto>>(reviewRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReviewDto>>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/nonApproved")
    public ResponseEntity<List<ReviewDto>> getNonApprovedReviews() {
        try {
            List<ReviewDto> reviewRes = service.getNonApprovedReviews();
            for(int i=0;i<reviewRes.size();i++){
                int userid = reviewRes.get(i).getUser().getId();
                reviewRes.get(i).getUser().setName(userService.findUserById(userid).getName());
            }
            return new ResponseEntity<List<ReviewDto>>(reviewRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ReviewDto>>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
