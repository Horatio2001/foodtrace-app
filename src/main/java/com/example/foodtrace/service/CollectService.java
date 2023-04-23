package com.example.foodtrace.service;

import com.example.foodtrace.dao.CollectDao;
import com.example.foodtrace.entity.CollectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CollectService {
    @Autowired
    private CollectDao collectDao;

    public void addCollectInfo(CollectInfo collectInfo) {
        collectDao.addCreateInfo(collectInfo.getCollectID(), collectInfo.getType(), collectInfo.getName()
                , collectInfo.getGermplasmName(), collectInfo.getGermplasmNameEn(), collectInfo.getSectionName()
                , collectInfo.getGenericName(), collectInfo.getScientificName(), collectInfo.getResourceType()
                , collectInfo.getCollectMethod(), collectInfo.getGermplasmSource(), collectInfo.getSourceCountry()
                , collectInfo.getSourceProvince(), collectInfo.getSource(), collectInfo.getSourceOrg()
                , collectInfo.getOriginCountry(), collectInfo.getOriginPlace(), collectInfo.getCollectPlaceLongitude()
                , collectInfo.getCollectPlaceLatitude(), collectInfo.getCollectPlaceAltitude()
                , collectInfo.getCollectPlaceSoilType(), collectInfo.getCollectPlaceEcologyType()
                , collectInfo.getCollectMaterialType(), collectInfo.getCollectPeople(), collectInfo.getCollectUnit()
                , collectInfo.getCollectTime(), collectInfo.getSpeciesName(), collectInfo.getImage()
                , collectInfo.getCollectRemark()
        );
    }

    public void deleteCollectInfo(String fruitInfoID) {
        collectDao.deleteCollectInfo(fruitInfoID);
    }

    public void modifyCollectInfo(CollectInfo collectInfo) {
        collectDao.modifyCollectInfo(collectInfo);
    }

    public CollectInfo queryCollectInfo(String fruitInfoID) {
        return collectDao.queryCollectInfo(fruitInfoID);
    }

    public List<CollectInfo> queryInfosByPage(int pageNum) {
        int index = (pageNum - 1) * 10;
        return collectDao.queryInfosByPage(index);
    }

    public List<CollectInfo> queryDocumentedInfosByPage(int pageNum) {
        int index = (pageNum - 1) * 10;
        return collectDao.queryDocumentedInfosByPage(index);
    }

    public List<CollectInfo> queryCollectInfosByPage(int pageNum) {
        int index = (pageNum - 1) * 10;
        return collectDao.queryCollectInfosByPage(index);
    }
}
