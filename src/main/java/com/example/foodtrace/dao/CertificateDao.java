package com.example.foodtrace.dao;

import com.example.foodtrace.entity.Certificate;
import com.example.foodtrace.entity.FruitInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CertificateDao {
    Integer generateCertificate(@Param("certificate") Certificate certificate);

    Certificate queryCertificate(@Param("fruitInfoID") String fruitInfoID);

    Certificate queryCertificateByHash(@Param("hash") String hash);
}
