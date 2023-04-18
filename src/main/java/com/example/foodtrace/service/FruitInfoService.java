package com.example.foodtrace.service;

import com.example.foodtrace.dao.FruitInfoDao;
import com.example.foodtrace.entity.FruitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitInfoService {

    @Autowired
    private FruitInfoDao fruitInfoDao;

    public void createFruitInfo(String fruitInfoID) {
        fruitInfoDao.createFruitInfo(fruitInfoID);
    }
}
