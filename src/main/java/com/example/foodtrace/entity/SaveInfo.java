package com.example.foodtrace.entity;

import java.sql.Timestamp;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SaveInfo {
    private String SaveID;
    private String MainPreference;
    private int MainUse;
    private int PreservationFacility;
    private int GermplasmType;
    private int SaveQuantity;
    private int MeasuringUnit;
    private String SaveUnit;
    private String SaveVault;
    private String SavePlace;
    private Date WarehousingYear;
    private String SaveProperty;
    private String ResourceDescription;
    private String ResourceRemark;
    private String GermplasmImage;
    private String SaveHash;
    private Map<String, Timestamp> SaveTransactions;

    public String getSaveID() {
        return SaveID;
    }

    public void setSaveID(String saveID) {
        SaveID = saveID;
    }

    public String getMainPreference() {
        return MainPreference;
    }

    public void setMainPreference(String mainPreference) {
        MainPreference = mainPreference;
    }

    public int getMainUse() {
        return MainUse;
    }

    public void setMainUse(int mainUse) {
        MainUse = mainUse;
    }

    public int getPreservationFacility() {
        return PreservationFacility;
    }

    public void setPreservationFacility(int preservationFacility) {
        PreservationFacility = preservationFacility;
    }

    public int getGermplasmType() {
        return GermplasmType;
    }

    public void setGermplasmType(int germplasmType) {
        GermplasmType = germplasmType;
    }

    public int getSaveQuantity() {
        return SaveQuantity;
    }

    public void setSaveQuantity(int saveQuantity) {
        SaveQuantity = saveQuantity;
    }

    public int getMeasuringUnit() {
        return MeasuringUnit;
    }

    public void setMeasuringUnit(int measuringUnit) {
        MeasuringUnit = measuringUnit;
    }

    public String getSaveUnit() {
        return SaveUnit;
    }

    public void setSaveUnit(String saveUnit) {
        SaveUnit = saveUnit;
    }

    public String getSaveVault() {
        return SaveVault;
    }

    public void setSaveVault(String saveVault) {
        SaveVault = saveVault;
    }

    public String getSavePlace() {
        return SavePlace;
    }

    public void setSavePlace(String savePlace) {
        SavePlace = savePlace;
    }

    public Date getWarehousingYear() {
        return WarehousingYear;
    }

    public void setWarehousingYear(Date warehousingYear) {
        WarehousingYear = warehousingYear;
    }

    public String getSaveProperty() {
        return SaveProperty;
    }

    public void setSaveProperty(String saveProperty) {
        this.SaveProperty = saveProperty;
    }

    public String getResourceDescription() {
        return ResourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        ResourceDescription = resourceDescription;
    }

    public String getResourceRemark() {
        return ResourceRemark;
    }

    public void setResourceRemark(String resourceRemark) {
        ResourceRemark = resourceRemark;
    }

    public String getGermplasmImage() {
        return GermplasmImage;
    }

    public void setGermplasmImage(String germplasmImage) {
        GermplasmImage = germplasmImage;
    }

    public String getSaveHash() {
        return SaveHash;
    }

    public void setSaveHash(String saveHash) {
        SaveHash = saveHash;
    }
}
