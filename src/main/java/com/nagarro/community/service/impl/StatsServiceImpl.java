package com.nagarro.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.community.dto.StatsDto;
import com.nagarro.community.repository.ProductRepository;
import com.nagarro.community.repository.ReviewRepository;
import com.nagarro.community.repository.UserRepository;
import com.nagarro.community.service.StatsService;
import com.nagarro.community.service.UserService;

@Service
public class StatsServiceImpl implements StatsService{

    @Autowired
    UserRepository userRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ReviewRepository reviewRepo;

    @Override
    public StatsDto getStats() {
        StatsDto stats = new StatsDto();

        stats.setUserCount(userRepo.count());
        stats.setProductCount(productRepo.count());
        stats.setReviewCount(reviewRepo.countByApprovedTrue());

        return stats;
    }
    
}
