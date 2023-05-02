package com.example.foodtrace.entity;

import java.sql.Timestamp;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EnterInfo {
    private String EnterID;
    private String Certifier;
    private String CertifyOrg;
    private String CertifyPlace;
    private Date CertifyYear;
    private String OperationRange;
    private String EnterRemark;
    private String EnterHash;
    private Integer EnterYear;
    private int IsLoaded;
    private int IsContradict;
    private int Status;
    private Map<String, Timestamp> EnterTransactions;
    public int getIsLoaded() {
        return IsLoaded;
    }

    public void setIsLoaded(int isLoaded) {
        IsLoaded = isLoaded;
    }

    public int getIsContradict() {
        return IsContradict;
    }

    public void setIsContradict(int isContradict) {
        IsContradict = isContradict;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getEnterID() {
        return EnterID;
    }

    public void setEnterID(String enterID) {
        EnterID = enterID;
    }

    public String getCertifier() {
        return Certifier;
    }

    public void setCertifier(String certifier) {
        Certifier = certifier;
    }

    public String getCertifyOrg() {
        return CertifyOrg;
    }

    public void setCertifyOrg(String certifyOrg) {
        CertifyOrg = certifyOrg;
    }

    public String getCertifyPlace() {
        return CertifyPlace;
    }

    public void setCertifyPlace(String certifyPlace) {
        CertifyPlace = certifyPlace;
    }

    public Date getCertifyYear() {
        return CertifyYear;
    }

    public void setCertifyYear(Date certifyYear) {
        CertifyYear = certifyYear;
    }

    public String getOperationRange() {
        return OperationRange;
    }

    public void setOperationRange(String operationRange) {
        OperationRange = operationRange;
    }

    public String getEnterRemark() {
        return EnterRemark;
    }

    public void setEnterRemark(String enterRemark) {
        EnterRemark = enterRemark;
    }

    public String getEnterHash() {
        return EnterHash;
    }

    public void setEnterHash(String enterHash) {
        EnterHash = enterHash;
    }

    public Integer getEnterYear() {
        return EnterYear;
    }

    public void setEnterYear(Integer enterYear) {
        EnterYear = enterYear;
    }
}
