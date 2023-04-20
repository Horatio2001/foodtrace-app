package com.example.foodtrace.entity;

import java.sql.Timestamp;

public class FruitInfo {
    private String ID;
    private int Status;
    private int IsContradict;
    private int IsDeleted;
    private int IsLoaded;
    private String BlockHash;
    private Timestamp CreateTime;

    public Timestamp getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Timestamp createTime) {
        CreateTime = createTime;
    }

    public String getID() {
        return ID;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getIsContradict() {
        return IsContradict;
    }

    public void setIsContradict(int isContradict) {
        IsContradict = isContradict;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        IsDeleted = isDeleted;
    }

    public int getIsLoaded() {
        return IsLoaded;
    }

    public void setIsLoaded(int isLoaded) {
        IsLoaded = isLoaded;
    }

    public String getBlockHash() {
        return BlockHash;
    }

    public void setBlockHash(String blockHash) {
        BlockHash = blockHash;
    }

}
