package com.example.foodtrace.dao;

import com.example.foodtrace.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@Mapper
public interface FruitInfoDao {
    int createFruitInfo(@Param("fruitInfoID") String fruitInfoID);

    Integer deleteFruitInfo(@Param("fruitInfoID") String fruitInfoID);

    FruitInfo getStatus(@Param("fruitInfoID") String fruitInfoID);

    int refuseCollect(@Param("fruitInfoID") String fruitInfoID);

    int refuseSave(@Param("fruitInfoID") String fruitInfoID);

    int refuseEnter(@Param("fruitInfoID") String fruitInfoID);

    int refuseShare(@Param("fruitInfoID") String fruitInfoID);

    int setStatus(@Param("fruitInfoID") String fruitInfoID, @Param("status") int status);

    int loadInfo(@Param("fruitInfoID") String fruitInfoID);

}
