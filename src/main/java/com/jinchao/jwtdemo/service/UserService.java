package com.jinchao.jwtdemo.service;

import com.jinchao.jwtdemo.domain.User;

public interface UserService {
    User findById(String id);

    boolean deleteUser(String id);

    User saveUser(User user);
}
