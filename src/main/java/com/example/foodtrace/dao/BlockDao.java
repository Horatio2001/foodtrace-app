package com.example.foodtrace.dao;

import com.example.foodtrace.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface BlockDao {
    List<Organization> getTxByOrg();
}
