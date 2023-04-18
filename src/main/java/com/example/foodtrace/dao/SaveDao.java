package com.example.foodtrace.dao;

import com.example.foodtrace.entity.SaveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Mapper
public interface SaveDao {
    Integer addSaveInfo(@Param("saveInfo") SaveInfo saveInfo);
}
