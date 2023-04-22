package com.example.foodtrace.entity;

import java.sql.Timestamp;

public class Certificate {
    private String ID;
    private String Name;
    private String Hash;
    private String CollectUnit;
    private Timestamp CertifyTime;
    private int MainUse;
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

    public Timestamp getCertifyTime() {
        return CertifyTime;
    }

    public void setCertifyTime(Timestamp certifyTime) {
        CertifyTime = certifyTime;
    }

    public int getMainUse() {
        return MainUse;
    }

    public void setMainUse(int mainUse) {
        MainUse = mainUse;
    }

    public String getResourceRemark() {
        return ResourceRemark;
    }

    public void setResourceRemark(String resourceRemark) {
        ResourceRemark = resourceRemark;
    }
}
