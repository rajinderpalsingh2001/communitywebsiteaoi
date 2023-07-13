package com.nagarro.community.service;

import java.util.List;

import com.nagarro.community.dto.ReviewDto;
import com.nagarro.community.entity.Review;

public interface ReviewService {
    ReviewDto addReview(Review review);
    ReviewDto approveReview(int id);
    ReviewDto deleteReview(int id);
    List<ReviewDto> getReviews(int pid);
    List<ReviewDto> getNonApprovedReviews();


    // Review requestReview(Review review);
}
