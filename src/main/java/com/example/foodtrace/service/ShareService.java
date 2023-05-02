package com.example.foodtrace.service;

import com.example.foodtrace.dao.ShareDao;
import com.example.foodtrace.entity.SaveInfo;
import com.example.foodtrace.entity.ShareInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {
    @Autowired
    private ShareDao shareDao;
    public Integer addShareInfo(ShareInfo saveInfo) {
        return shareDao.addShareInfo(saveInfo);
    }

    public void deleteShareInfo(String fruitInfoID) {
        shareDao.deleteShareInfo(fruitInfoID);
    }

    public Integer modifyShareInfo(ShareInfo saveInfo) {
        return shareDao.modifyShareInfo(saveInfo);
    }

    public ShareInfo queryShareInfo(String fruitInfoID) {
        return shareDao.queryShareInfo(fruitInfoID);
    }

    public List<ShareInfo> queryShareInfosByPage(int pageNum, int pageIdx) {
        int index = (pageNum - 1) * pageIdx;
        return shareDao.queryShareInfosByPage(index, pageIdx);
    }

    public int shareCount() {
        return shareDao.shareCount();
    }

}
