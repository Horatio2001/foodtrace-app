package com.example.foodtrace.entity;

import java.sql.Timestamp;

public class FruitInfo {
    private String ID;
    private Integer Type;
    private String Name;
    private int Status;
    private int IsContradict;
    private int IsDeleted;
    private int IsLoaded;
    private String BlockHash;

    private CollectInfo collectInfo;
    private SaveInfo saveInfo;
    private EnterInfo enterInfo;
    private ShareInfo shareInfo;

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

    public CollectInfo getCollectInfo() {
        return collectInfo;
    }

    public void setCollectInfo(CollectInfo collectInfo) {
        this.collectInfo = collectInfo;
    }

    public SaveInfo getSaveInfo() {
        return saveInfo;
    }

    public void setSaveInfo(SaveInfo saveInfo) {
        this.saveInfo = saveInfo;
    }

    public EnterInfo getEnterInfo() {
        return enterInfo;
    }

    public void setEnterInfo(EnterInfo enterInfo) {
        this.enterInfo = enterInfo;
    }

    public ShareInfo getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
