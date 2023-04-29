package com.example.foodtrace.dao;

import com.example.foodtrace.entity.Chaincodes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ChaincodesDao {
    List<Chaincodes> queryChaincodesByPage(@Param("pageNum")int pageNum, @Param("pageIdx")int pageIdx);
}
