package com.example.foodtrace.entity;

import java.sql.Timestamp;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SaveInfo {
    private String SaveID;
    private Integer MainPreference;
    private String MainUse;
    private String PreservationFacility;
    private String GermplasmType;
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

    public Integer getMainPreference() {
        return MainPreference;
    }

    public void setMainPreference(Integer mainPreference) {
        MainPreference = mainPreference;
    }

    public String getMainUse() {
        return MainUse;
    }

    public void setMainUse(String mainUse) {
        MainUse = mainUse;
    }

    public String getPreservationFacility() {
        return PreservationFacility;
    }

    public void setPreservationFacility(String preservationFacility) {
        PreservationFacility = preservationFacility;
    }

    public String getGermplasmType() {
        return GermplasmType;
    }

    public void setGermplasmType(String germplasmType) {
        GermplasmType = germplasmType;
    }

    public void setSaveYear(Integer saveYear) {
        SaveYear = saveYear;
    }
}
