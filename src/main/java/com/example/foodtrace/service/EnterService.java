package com.example.foodtrace.service;

import com.example.foodtrace.dao.EnterDao;
import com.example.foodtrace.entity.EnterInfo;
import com.example.foodtrace.entity.SaveInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterService {
    @Autowired
    private EnterDao enterDao;

    public Integer addEnterInfo(EnterInfo enterInfo) {
        return enterDao.addEnterInfo(enterInfo);
    }

    public void deleteEnterInfo(String fruitInfoID) {
        enterDao.deleteEnterInfo(fruitInfoID);
    }

    public Integer modifyEnterInfo(EnterInfo enterInfo) {
        return enterDao.modifyEnterInfo(enterInfo);
    }

    public EnterInfo queryEnterInfo(String fruitInfoID) {
        return enterDao.queryEnterInfo(fruitInfoID);
    }

    public List<EnterInfo> queryEnterInfosByPage(int pageNum) {
        int index = (pageNum - 1) * 10;
        return enterDao.queryEnterInfosByPage(index);
    }

    public int enterCount() {
        return enterDao.enterCount();
    }


}
