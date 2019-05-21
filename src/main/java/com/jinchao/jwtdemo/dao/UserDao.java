package com.jinchao.jwtdemo.dao;

import com.jinchao.jwtdemo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends MongoRepository<User, String> {
    Optional<User> findById(String id);
}
