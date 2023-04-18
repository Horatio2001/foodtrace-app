package com.example.foodtrace.service;

import com.example.foodtrace.dao.EnterDao;
import com.example.foodtrace.entity.EnterInfo;
import com.example.foodtrace.entity.SaveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterService {
    @Autowired
    private EnterDao enterDao;

    public Integer addEnterInfo(EnterInfo enterInfo) {
        return enterDao.addEnterInfo(enterInfo);
    }
}
