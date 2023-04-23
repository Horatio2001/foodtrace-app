package com.example.foodtrace.dao;

import com.example.foodtrace.entity.SaveInfo;
import com.example.foodtrace.entity.ShareInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShareDao {
    Integer addShareInfo(@Param("shareInfo") ShareInfo shareInfo);

    Integer deleteShareInfo(@Param("fruitInfoID") String fruitInfoID);

    Integer modifyShareInfo(@Param("shareInfo") ShareInfo shareInfo);

    ShareInfo queryShareInfo(@Param("fruitInfoID") String fruitInfoID);

    List<ShareInfo> queryShareInfosByPage(@Param("pageNum") int pageNum);
}
