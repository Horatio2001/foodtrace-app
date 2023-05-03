package com.example.foodtrace.service;

import com.example.foodtrace.dao.CertificateDao;
import com.example.foodtrace.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {
    @Autowired
    private CertificateDao certificateDao;
    public void generateCertificate(Certificate certificate) {
        certificateDao.generateCertificate(certificate);
    }

    public Certificate queryCertificate(String fruitInfoID) {
        return certificateDao.queryCertificate(fruitInfoID);
    }

    public Certificate queryCertificateByHash(String hash) {
        return certificateDao.queryCertificateByHash(hash);
    }
}
