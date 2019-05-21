package com.jinchao.jwtdemo.service.Impl;

import com.jinchao.jwtdemo.dao.UserDao;
import com.jinchao.jwtdemo.domain.User;
import com.jinchao.jwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(@Autowired UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public User saveUser(User user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }
}
