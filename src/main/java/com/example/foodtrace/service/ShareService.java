package com.example.foodtrace.service;

import com.example.foodtrace.dao.ShareDao;
import com.example.foodtrace.entity.SaveInfo;
import com.example.foodtrace.entity.ShareInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareService {
    @Autowired
    private ShareDao shareDao;
    public Integer addShareInfo(ShareInfo saveInfo) {
        return shareDao.addShareInfo(saveInfo);
    }
}
