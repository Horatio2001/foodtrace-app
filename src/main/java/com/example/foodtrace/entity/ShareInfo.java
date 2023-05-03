package com.example.foodtrace.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShareInfo {
    private String ShareID;

    private Integer Type;
    private String Name;
    private String ShareObj;
    private String ContactInfo;
    private Integer ShareMode;
    private Integer ShareUse;
    private Integer ShareNum;
    private Date ShareBeginTime;
    private Date ShareEndTime;
    private String ShareHash;

    private int IsLoaded;
    private int IsContradict;
    private int Status;
    private Map<String, Timestamp> ShareTransactions;

    private int IsCertified;

    public int getIsCertified() {
        return IsCertified;
    }

    public void setIsCertified(int isCertified) {
        IsCertified = isCertified;
    }


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

    public String getShareID() {
        return ShareID;
    }

    public void setShareID(String shareID) {
        ShareID = shareID;
    }

    public String getShareObj() {
        return ShareObj;
    }

    public void setShareObj(String shareObj) {
        this.ShareObj = shareObj;
    }

    public String getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(String contactInfo) {
        ContactInfo = contactInfo;
    }

    public Integer getShareMode() {
        return ShareMode;
    }

    public void setShareMode(Integer shareMode) {
        ShareMode = shareMode;
    }

    public Integer getShareUse() {
        return ShareUse;
    }

    public void setShareUse(Integer shareUse) {
        ShareUse = shareUse;
    }

    public Integer getShareNum() {
        return ShareNum;
    }

    public void setShareNum(Integer shareNum) {
        ShareNum = shareNum;
    }

    public Date getShareBeginTime() {
        return ShareBeginTime;
    }

    public void setShareBeginTime(Date shareBeginTime) {
        ShareBeginTime = shareBeginTime;
    }

    public Date getShareEndTime() {
        return ShareEndTime;
    }

    public void setShareEndTime(Date shareEndTime) {
        ShareEndTime = shareEndTime;
    }

    public String getShareHash() {
        return ShareHash;
    }

    public void setShareHash(String shareHash) {
        ShareHash = shareHash;
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
