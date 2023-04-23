package com.example.foodtrace.service;

import com.example.foodtrace.dao.SaveDao;
import com.example.foodtrace.entity.SaveInfo;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveService {
    @Autowired
    private SaveDao saveDao;

    public Integer addSaveInfo(SaveInfo saveInfo) {
        return saveDao.addSaveInfo(saveInfo);
    }

    public void deleteSaveInfo(String fruitInfoID) {
        saveDao.deleteSaveInfo(fruitInfoID);
    }

    public Integer modifySaveInfo(SaveInfo saveInfo) {
        return saveDao.modifySaveInfo(saveInfo);
    }

    public SaveInfo querySaveInfo(String fruitInfoID) {
        return saveDao.querySaveInfo(fruitInfoID);
    }

    public List<SaveInfo> querySaveInfosByPage(int pageNum) {
        int index = (pageNum - 1) * 10;
        return saveDao.querySaveInfosByPage(index);
    }

    public int saveCount() {
        return saveDao.saveCount();
    }

}
