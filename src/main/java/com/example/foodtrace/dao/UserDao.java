package com.example.foodtrace.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {
    Integer register(@Param("ID") String ID, @Param("Pwd") String pwd);
    String logIn(@Param("ID") String ID);
    Integer logOff(@Param("ID") String ID);
}
