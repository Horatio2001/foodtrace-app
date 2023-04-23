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

    public void deleteFruitInfo(String fruitInfoID) {
        fruitInfoDao.deleteFruitInfo(fruitInfoID);
    }

    public void refuseCollect(String fruitInfoID) {
        fruitInfoDao.refuseCollect(fruitInfoID);
    }

    public void refuseSave(String fruitInfoID) {
        fruitInfoDao.refuseSave(fruitInfoID);
    }

    public void refuseEnter(String fruitInfoID) {
        fruitInfoDao.refuseEnter(fruitInfoID);
    }

    public void refuseShare(String fruitInfoID) {
        fruitInfoDao.refuseShare(fruitInfoID);
    }

    public FruitInfo getStatus(String fruitInfoID) {
        return fruitInfoDao.getStatus(fruitInfoID);
    }

    public void setStatus(String fruitInfoID, int status) {
        fruitInfoDao.setStatus(fruitInfoID, status);
    }

    public void loadInfo(String fruitInfoID, String txHash) {
        fruitInfoDao.loadInfo(fruitInfoID, txHash);
    }

    public int fruitCount() {
        return fruitInfoDao.fruitCount();
    }

}
