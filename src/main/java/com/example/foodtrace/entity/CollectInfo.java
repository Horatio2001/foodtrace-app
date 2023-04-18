package com.example.foodtrace.entity;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class CollectInfo {
    private String CollectID;
    private Integer Type;
    private String Name;
    private String GermplasmName;
    private String GermplasmNameEn;
    private String SectionName;
    private String GenericName;
    private String ScientificName;
    private Integer ResourceType;
    private Integer CollectMethod;
    private String GermplasmSource;
    private String SourceCountry;
    private String SourceProvince;
    private String Source;
    private String SourceOrg;
    private String OriginCountry;
    private String OriginPlace;
    private String CollectPlaceLongitude;
    private String CollectPlaceLatitude;
    private String CollectPlaceAltitude;
    private Integer CollectPlaceSoilType;
    private Integer CollectPlaceEcologyType;
    private Integer CollectMaterialType;
    private String CollectPeople;
    private String CollectUnit;
    private Date CollectTime;
    private String SpeciesName;
    private String Image;
    private String CollectRemark;
    private String CollectHash;
    private Map<String, Timestamp> CollectTransactions;
    public String getCollectID() {
        return CollectID;
    }

    public void setCollectID(String collectID) {
        CollectID = collectID;
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

    public String getGermplasmName() {
        return GermplasmName;
    }

    public void setGermplasmName(String germplasmName) {
        GermplasmName = germplasmName;
    }

    public String getGermplasmNameEn() {
        return GermplasmNameEn;
    }

    public void setGermplasmNameEn(String germplasmNameEn) {
        GermplasmNameEn = germplasmNameEn;
    }

    public String getSectionName() {
        return SectionName;
    }

    public void setSectionName(String sectionName) {
        SectionName = sectionName;
    }

    public String getGenericName() {
        return GenericName;
    }

    public void setGenericName(String genericName) {
        GenericName = genericName;
    }

    public String getScientificName() {
        return ScientificName;
    }

    public void setScientificName(String scientificName) {
        ScientificName = scientificName;
    }

    public Integer getResourceType() {
        return ResourceType;
    }

    public void setResourceType(Integer resourceType) {
        ResourceType = resourceType;
    }

    public Integer getCollectMethod() {
        return CollectMethod;
    }

    public void setCollectMethod(Integer collectMethod) {
        CollectMethod = collectMethod;
    }

    public String getGermplasmSource() {
        return GermplasmSource;
    }

    public void setGermplasmSource(String germplasmSource) {
        GermplasmSource = germplasmSource;
    }

    public String getSourceCountry() {
        return SourceCountry;
    }

    public void setSourceCountry(String sourceCountry) {
        SourceCountry = sourceCountry;
    }

    public String getSourceProvince() {
        return SourceProvince;
    }

    public void setSourceProvince(String sourceProvince) {
        SourceProvince = sourceProvince;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getSourceOrg() {
        return SourceOrg;
    }

    public void setSourceOrg(String sourceOrg) {
        SourceOrg = sourceOrg;
    }

    public String getOriginCountry() {
        return OriginCountry;
    }

    public void setOriginCountry(String originCountry) {
        OriginCountry = originCountry;
    }

    public String getOriginPlace() {
        return OriginPlace;
    }

    public void setOriginPlace(String originPlace) {
        OriginPlace = originPlace;
    }

    public String getCollectPlaceLongitude() {
        return CollectPlaceLongitude;
    }

    public void setCollectPlaceLongitude(String collectPlaceLongitude) {
        CollectPlaceLongitude = collectPlaceLongitude;
    }

    public String getCollectPlaceLatitude() {
        return CollectPlaceLatitude;
    }

    public void setCollectPlaceLatitude(String collectPlaceLatitude) {
        CollectPlaceLatitude = collectPlaceLatitude;
    }

    public String getCollectPlaceAltitude() {
        return CollectPlaceAltitude;
    }

    public void setCollectPlaceAltitude(String collectPlaceAltitude) {
        CollectPlaceAltitude = collectPlaceAltitude;
    }

    public Integer getCollectPlaceSoilType() {
        return CollectPlaceSoilType;
    }

    public void setCollectPlaceSoilType(Integer collectPlaceSoilType) {
        CollectPlaceSoilType = collectPlaceSoilType;
    }

    public Integer getCollectPlaceEcologyType() {
        return CollectPlaceEcologyType;
    }

    public void setCollectPlaceEcologyType(Integer collectPlaceEcologyType) {
        CollectPlaceEcologyType = collectPlaceEcologyType;
    }

    public Integer getCollectMaterialType() {
        return CollectMaterialType;
    }

    public void setCollectMaterialType(Integer collectMaterialType) {
        CollectMaterialType = collectMaterialType;
    }

    public String getCollectPeople() {
        return CollectPeople;
    }

    public void setCollectPeople(String collectPeople) {
        CollectPeople = collectPeople;
    }

    public String getCollectUnit() {
        return CollectUnit;
    }

    public void setCollectUnit(String collectUnit) {
        CollectUnit = collectUnit;
    }

    public Date getCollectTime() {
        return CollectTime;
    }

    public void setCollectTime(Date collectTime) {
        CollectTime = collectTime;
    }

    public String getSpeciesName() {
        return SpeciesName;
    }

    public void setSpeciesName(String speciesName) {
        SpeciesName = speciesName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCollectRemark() {
        return CollectRemark;
    }

    public void setCollectRemark(String collectRemark) {
        CollectRemark = collectRemark;
    }

    public String getCollectHash() {
        return CollectHash;
    }

    public void setCollectHash(String collectHash) {
        CollectHash = collectHash;
    }
}
