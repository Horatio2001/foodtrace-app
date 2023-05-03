package com.example.foodtrace.entity;

import java.sql.Timestamp;
import java.util.Date;

public class Certificate {
    private String ID;
    private String Name;
    private String Hash;
    private String CollectUnit;
    private Date CertifyTime;
    private String MainUse;
    private String ResourceRemark;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        Hash = hash;
    }

    public String getCollectUnit() {
        return CollectUnit;
    }

    public void setCollectUnit(String collectUnit) {
        CollectUnit = collectUnit;
    }

    public Date getCertifyTime() {
        return CertifyTime;
    }

    public void setCertifyTime(Date certifyTime) {
        CertifyTime = certifyTime;
    }

    public String getMainUse() {
        return MainUse;
    }

    public void setMainUse(String mainUse) {
        MainUse = mainUse;
    }

    public String getResourceRemark() {
        return ResourceRemark;
    }

    public void setResourceRemark(String resourceRemark) {
        ResourceRemark = resourceRemark;
    }
}
