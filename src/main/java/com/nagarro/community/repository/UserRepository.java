package com.nagarro.community.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.community.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
