package com.example.foodtrace.dao;

import com.example.foodtrace.entity.EnterInfo;
import com.example.foodtrace.entity.SaveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EnterDao {
    Integer addEnterInfo(@Param("enterInfo") EnterInfo enterInfo);

    Integer deleteEnterInfo(@Param("fruitInfoID") String fruitInfoID);

    Integer modifyEnterInfo(@Param("enterInfo") EnterInfo enterInfo);

    EnterInfo queryEnterInfo(@Param("fruitInfoID") String fruitInfoID);

    List<EnterInfo> queryEnterInfosByPage(@Param("pageNum") int pageNum, @Param("pageIdx") int pageIdx);

    int enterCount();

}
