package com.example.foodtrace.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShareInfo {
    private String ShareID;
    private String ShareObj;
    private String ContactInfo;
    private int ShareMode;
    private int ShareUse;
    private int ShareNum;
    private Date ShareBeginTime;
    private Date ShareEndTime;
    private String ShareHash;

    private Map<String, Timestamp> ShareTransactions;


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

    public int getShareMode() {
        return ShareMode;
    }

    public void setShareMode(int shareMode) {
        ShareMode = shareMode;
    }

    public int getShareUse() {
        return ShareUse;
    }

    public void setShareUse(int shareUse) {
        ShareUse = shareUse;
    }

    public int getShareNum() {
        return ShareNum;
    }

    public void setShareNum(int shareNum) {
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
}
