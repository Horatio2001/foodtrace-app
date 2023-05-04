package com.example.foodtrace.service;

import com.example.foodtrace.dao.UserDao;
import com.example.foodtrace.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public void register(String ID, String Pwd) {
        userDao.register(ID, Pwd);
    }

    public String logIn(String ID) {
        return userDao.logIn(ID);
    }

    public User getUserInfo(String ID) {
        return userDao.getUserInfo(ID);
    }
}
