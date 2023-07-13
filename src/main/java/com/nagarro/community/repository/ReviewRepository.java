package com.nagarro.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.community.entity.Review;
import com.nagarro.community.entity.User;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>{

    List<Review> findByApprovedTrueAndPid(int pid);

    List<Review> findByApprovedFalse();

    long countByApprovedTrue();
}
