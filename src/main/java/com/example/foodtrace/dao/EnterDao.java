package com.example.foodtrace.dao;

import com.example.foodtrace.entity.EnterInfo;
import com.example.foodtrace.entity.SaveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EnterDao {
    Integer addEnterInfo(@Param("enterInfo") EnterInfo enterInfo);
}
