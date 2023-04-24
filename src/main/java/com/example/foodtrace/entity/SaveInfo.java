package com.example.foodtrace.entity;

import java.sql.Timestamp;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SaveInfo {
    private String SaveID;
    private String MainPreference;
    private Integer MainUse;
    private Integer PreservationFacility;
    private Integer GermplasmType;
    private Integer SaveQuantity;
    private Integer MeasuringUnit;
    private String SaveUnit;
    private String SaveVault;
    private String SavePlace;
    private Date WarehousingYear;
    private String SaveProperty;
    private String ResourceDescription;
    private String ResourceRemark;
    private String GermplasmImage;
    private String SaveHash;
    private Integer SaveYear;
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

    public Integer getMainUse() {
        return MainUse;
    }

    public void setMainUse(Integer mainUse) {
        MainUse = mainUse;
    }

    public Integer getPreservationFacility() {
        return PreservationFacility;
    }

    public void setPreservationFacility(Integer preservationFacility) {
        PreservationFacility = preservationFacility;
    }

    public Integer getGermplasmType() {
        return GermplasmType;
    }

    public void setGermplasmType(Integer germplasmType) {
        GermplasmType = germplasmType;
    }

    public Integer getSaveQuantity() {
        return SaveQuantity;
    }

    public void setSaveQuantity(Integer saveQuantity) {
        SaveQuantity = saveQuantity;
    }

    public Integer getMeasuringUnit() {
        return MeasuringUnit;
    }

    public void setMeasuringUnit(Integer measuringUnit) {
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

    public Integer getSaveYear() {
        return SaveYear;
    }

    public void setSaveYear(Integer saveYear) {
        SaveYear = saveYear;
    }
}
