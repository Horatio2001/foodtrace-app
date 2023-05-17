package com.example.foodtrace.dao;

import com.example.foodtrace.entity.CollectInfo;
import com.example.foodtrace.entity.SaveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface SaveDao {
    Integer addSaveInfo(@Param("saveInfo") SaveInfo saveInfo);

    Integer deleteSaveInfo(@Param("fruitInfoID") String fruitInfoID);

    Integer modifySaveInfo(@Param("saveInfo") SaveInfo saveInfo);

    SaveInfo querySaveInfo(@Param("fruitInfoID") String fruitInfoID);

    List<SaveInfo> querySaveInfoByBlurID(@Param("BlurID") String BlurID);

    List<SaveInfo> querySaveInfosByPage(@Param("pageNum") int pageNum, @Param("pageIdx") int pageIdx);

    int saveCount();

}
