package com.nagarro.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.community.dto.ReviewDto;
import com.nagarro.community.entity.Review;
import com.nagarro.community.repository.ReviewRepository;
import com.nagarro.community.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ReviewRepository repo;

    @Autowired
    private ReviewDto dto;

    @Override
    public ReviewDto addReview(Review review) {
        return dto.toObj(repo.save(review));
    }

    @Override
    public ReviewDto approveReview(int id) {
    
        Review review = repo.findById(id).orElse(null);

        if(review==null){
            return null;
        }

        review.setApproved(true);

        return dto.toObj(repo.save(review));
    }

    @Override
    public ReviewDto deleteReview(int id) {
        Review review = repo.findById(id).orElse(null);

        ReviewDto reviewDao = dto.toObj(review);

        if(review==null){
            return null;
        }

        repo.delete(review);

        return reviewDao;
    }

    @Override
    public List<ReviewDto> getReviews(int pid){
        return dto.toObj((List<Review>) repo.findByApprovedTrueAndPid(pid));
    }

    @Override
    public List<ReviewDto> getNonApprovedReviews(){
        return dto.toObj((List<Review>) repo.findByApprovedFalse());
    }

    

    // @Override
    // public Review requestReview(Review review) {


    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'requestReview'");
    // }
    
}
