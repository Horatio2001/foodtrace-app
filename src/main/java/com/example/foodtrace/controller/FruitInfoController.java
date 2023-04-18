package com.example.foodtrace.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.foodtrace.entity.*;
import com.example.foodtrace.service.*;
import com.example.foodtrace.util.DateParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@Api("初始化信息用")
public class FruitInfoController {
    @Autowired
    private FruitInfoService fruitInfoService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private SaveService saveService;
    @Autowired
    private EnterService enterService;
    @Autowired
    private ShareService shareService;

    @ApiOperation(value = "创建初始化信息")
    @PostMapping("Info/CreateFruitInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> createFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        fruitInfoService.createFruitInfo(fruitInfoID);
        ret.put("msg", "200");
        ret.put("description", "发布商品成功");
        return ret;
    }

    @ApiOperation(value = "添加采集信息")
    @PostMapping("Info/AddCollectInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CollectID", value = "CollectID", required = true),
            @ApiImplicitParam(name = "Type", value = "Type", required = true),
            @ApiImplicitParam(name = "Name", value = "Name", required = true),
            @ApiImplicitParam(name = "GermplasmName", value = "GermplasmName", required = true),
            @ApiImplicitParam(name = "GermplasmNameEn", value = "GermplasmNameEn"),
            @ApiImplicitParam(name = "SectionName", value = "SectionName"),
            @ApiImplicitParam(name = "GenericName", value = "GenericName", required = true),
            @ApiImplicitParam(name = "ScientificName", value = "ScientificName"),
            @ApiImplicitParam(name = "ResourceType", value = "ResourceType"),
            @ApiImplicitParam(name = "CollectMethod", value = "CollectMethod", required = true),
            @ApiImplicitParam(name = "GermplasmSource", value = "GermplasmSource", required = true),
            @ApiImplicitParam(name = "SourceCountry", value = "SourceCountry", required = true),
            @ApiImplicitParam(name = "SourceProvince", value = "SourceProvince", required = true),
            @ApiImplicitParam(name = "Source", value = "Source"),
            @ApiImplicitParam(name = "SourceOrg", value = "SourceOrg", required = true),
            @ApiImplicitParam(name = "OriginCountry", value = "OriginCountry", required = true),
            @ApiImplicitParam(name = "OriginPlace", value = "OriginPlace"),
            @ApiImplicitParam(name = "CollectPlaceLongitude", value = "CollectPlaceLongitude", required = true),
            @ApiImplicitParam(name = "CollectPlaceLatitude", value = "CollectPlaceLatitude", required = true),
            @ApiImplicitParam(name = "CollectPlaceAltitude", value = "CollectPlaceAltitude", required = true),
            @ApiImplicitParam(name = "CollectPlaceSoilType", value = "CollectPlaceSoilType", required = true),
            @ApiImplicitParam(name = "CollectPlaceEcologyType", value = "CollectPlaceEcologyType"),
            @ApiImplicitParam(name = "CollectMaterialType", value = "CollectMaterialType"),
            @ApiImplicitParam(name = "CollectPeople", value = "CollectPeople", required = true),
            @ApiImplicitParam(name = "CollectUnit", value = "CollectUnit", required = true),
            @ApiImplicitParam(name = "CollectTime", value = "CollectTime"),
            @ApiImplicitParam(name = "SpeciesName", value = "SpeciesName"),
            @ApiImplicitParam(name = "Image", value = "Image"),
            @ApiImplicitParam(name = "CollectRemark", value = "CollectRemark")
    })
    public Map<String, Object> addCollectInfoInSql(@RequestParam("CollectID") String CollectID,
                                                   @RequestParam("Type") Integer Type,
                                                   @RequestParam("Name") String Name,
                                                   @RequestParam("GermplasmName") String GermplasmName,
                                                   @RequestParam(value = "GermplasmNameEn", required = false) String GermplasmNameEn,
                                                   @RequestParam(value = "SectionName", required = false) String SectionName,
                                                   @RequestParam("GenericName") String GenericName,
                                                   @RequestParam(value = "ScientificName", required = false) String ScientificName,
                                                   @RequestParam(value = "ResourceType", required = false) Integer ResourceType,
                                                   @RequestParam("CollectMethod") Integer CollectMethod,
                                                   @RequestParam("GermplasmSource") String GermplasmSource,
                                                   @RequestParam("SourceCountry") String SourceCountry,
                                                   @RequestParam("SourceProvince") String SourceProvince,
                                                   @RequestParam(value = "Source", required = false) String Source,
                                                   @RequestParam("SourceOrg") String SourceOrg,
                                                   @RequestParam("OriginCountry") String OriginCountry,
                                                   @RequestParam(value = "OriginPlace", required = false) String OriginPlace,
                                                   @RequestParam("CollectPlaceLongitude") String CollectPlaceLongitude,
                                                   @RequestParam("CollectPlaceLatitude") String CollectPlaceLatitude,
                                                   @RequestParam("CollectPlaceAltitude") String CollectPlaceAltitude,
                                                   @RequestParam("CollectPlaceSoilType") Integer CollectPlaceSoilType,
                                                   @RequestParam(value = "CollectPlaceEcologyType", required = false) Integer CollectPlaceEcologyType,
                                                   @RequestParam(value = "CollectMaterialType", required = false) Integer CollectMaterialType,
                                                   @RequestParam("CollectPeople") String CollectPeople,
                                                   @RequestParam("CollectUnit") String CollectUnit,
                                                   @RequestParam(value = "CollectTime", required = false) String CollectTime,
                                                   @RequestParam(value = "SpeciesName", required = false) String SpeciesName,
                                                   @RequestParam(value = "Image", required = false) String Image,
                                                   @RequestParam(value = "CollectRemark", required = false) String CollectRemark) {
        Map<String, Object> ret = new HashMap<>();
        CollectInfo collectInfo = new CollectInfo();
        collectInfo.setCollectID(CollectID);
        collectInfo.setType(Type);
        collectInfo.setName(Name);
        collectInfo.setGermplasmName(GermplasmName);
        collectInfo.setGermplasmNameEn(GermplasmNameEn);
        collectInfo.setSectionName(SectionName);
        collectInfo.setGenericName(GenericName);
        collectInfo.setScientificName(ScientificName);
        collectInfo.setResourceType(ResourceType);
        collectInfo.setCollectMethod(CollectMethod);
        collectInfo.setGermplasmSource(GermplasmSource);
        collectInfo.setSourceCountry(SourceCountry);
        collectInfo.setSourceProvince(SourceProvince);
        collectInfo.setSource(Source);
        collectInfo.setSourceOrg(SourceOrg);
        collectInfo.setOriginCountry(OriginCountry);
        collectInfo.setOriginPlace(OriginPlace);
        collectInfo.setCollectPlaceLongitude(CollectPlaceLongitude);
        collectInfo.setCollectPlaceLatitude(CollectPlaceLatitude);
        collectInfo.setCollectPlaceAltitude(CollectPlaceAltitude);
        collectInfo.setCollectPlaceSoilType(CollectPlaceSoilType);
        collectInfo.setCollectPlaceEcologyType(CollectPlaceEcologyType);
        collectInfo.setCollectMaterialType(CollectMaterialType);
        collectInfo.setCollectPeople(CollectPeople);
        collectInfo.setCollectUnit(CollectUnit);

        if (CollectTime != null) {
            DateParser dateParser = new DateParser();
            collectInfo.setCollectTime(dateParser.convert(CollectTime));
        }

        collectInfo.setSpeciesName(SpeciesName);
        collectInfo.setImage(Image);
        collectInfo.setCollectRemark(CollectRemark);

        collectService.addCollectInfo(collectInfo);
        ret.put("msg", "200");
        ret.put("description", "成功添加收集信息");
        return ret;
    }

    @ApiOperation(value = "添加保存信息")
    @PostMapping("Info/AddSaveInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SaveID", value = "SaveID", required = true),
            @ApiImplicitParam(name = "MainPreference", value = "MainPreference", required = true),
            @ApiImplicitParam(name = "MainUse", value = "MainUse", required = true),
            @ApiImplicitParam(name = "PreservationFacility", value = "PreservationFacility", required = true),
            @ApiImplicitParam(name = "GermplasmType", value = "GermplasmType", required = true),
            @ApiImplicitParam(name = "SaveQuantity", value = "SaveQuantity", required = true),
            @ApiImplicitParam(name = "MeasuringUnit", value = "MeasuringUnit", required = true),
            @ApiImplicitParam(name = "SaveUnit", value = "SaveUnit", required = true),
            @ApiImplicitParam(name = "SaveVault", value = "SaveVault", required = true),
            @ApiImplicitParam(name = "SavePlace", value = "SavePlace", required = true),
            @ApiImplicitParam(name = "WarehousingYear", value = "WarehousingYear", required = true),
            @ApiImplicitParam(name = "SaveProperty", value = "SaveProperty", required = true),
            @ApiImplicitParam(name = "ResourceDescription", value = "ResourceDescription", required = true),
            @ApiImplicitParam(name = "ResourceMark", value = "ResourceMark", required = true),
            @ApiImplicitParam(name = "GermplasmImage", value = "GermplasmImage", required = true),
    })
    public Map<String, Object> addSaveInfoInSql(@RequestParam("SaveID") String SaveID,
                                                @RequestParam("MainPreference") String MainPreference,
                                                @RequestParam("MainUse") int MainUse,
                                                @RequestParam("PreservationFacility") int PreservationFacility,
                                                @RequestParam("GermplasmType") int GermplasmType,
                                                @RequestParam("SaveQuantity") int SaveQuantity,
                                                @RequestParam("MeasuringUnit") int MeasuringUnit,
                                                @RequestParam("SaveUnit") String SaveUnit,
                                                @RequestParam("SaveVault") String SaveVault,
                                                @RequestParam("SavePlace") String SavePlace,
                                                @RequestParam("WarehousingYear") String WarehousingYear,
                                                @RequestParam("SaveProperty") String SaveProperty,
                                                @RequestParam("ResourceDescription") String ResourceDescription,
                                                @RequestParam("ResourceMark") String ResourceRemark,
                                                @RequestParam("GermplasmImage") String GermplasmImage) {
        Map<String, Object> ret = new HashMap<>();
        SaveInfo saveInfo = new SaveInfo();
        saveInfo.setSaveID(SaveID);
        saveInfo.setMainPreference(MainPreference);
        saveInfo.setMainUse(MainUse);
        saveInfo.setPreservationFacility(PreservationFacility);
        saveInfo.setGermplasmType(GermplasmType);
        saveInfo.setSaveQuantity(SaveQuantity);
        saveInfo.setMeasuringUnit(MeasuringUnit);
        saveInfo.setSaveUnit(SaveUnit);
        saveInfo.setSaveVault(SaveVault);
        saveInfo.setSavePlace(SavePlace);

        if (WarehousingYear != null) {
            DateParser dateParser = new DateParser();
            saveInfo.setWarehousingYear(dateParser.convert(WarehousingYear));
        }

        saveInfo.setSaveProperty(SaveProperty);
        saveInfo.setResourceDescription(ResourceDescription);
        saveInfo.setResourceRemark(ResourceRemark);
        saveInfo.setGermplasmImage(GermplasmImage);
        saveService.addSaveInfo(saveInfo);
        ret.put("msg", "200");
        ret.put("description", "成功添加保存信息");
        return ret;
    }

    @ApiOperation(value = "添加录入信息")
    @PostMapping("Info/AddEnterInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "EnterID", value = "EnterID", required = true),
            @ApiImplicitParam(name = "Certifier", value = "Certifier", required = true),
            @ApiImplicitParam(name = "CertifyOrg", value = "CertifyOrg", required = true),
            @ApiImplicitParam(name = "CertifyPlace", value = "CertifyPlace", required = true),
            @ApiImplicitParam(name = "CertifyYear", value = "CertifyYear", required = true),
            @ApiImplicitParam(name = "OperationRange", value = "OperationRange", required = true),
            @ApiImplicitParam(name = "EnterRemark", value = "EnterRemark", required = true)
    })
    public Map<String, Object> addEnterInfoInSql(@RequestParam("EnterID") String EnterID,
                                                 @RequestParam("Certifier") String Certifier,
                                                 @RequestParam("CertifyOrg") String CertifyOrg,
                                                 @RequestParam("CertifyPlace") String CertifyPlace,
                                                 @RequestParam("CertifyYear") String CertifyYear,
                                                 @RequestParam("OperationRange") String OperationRange,
                                                 @RequestParam("EnterRemark") String EnterRemark) {
        Map<String, Object> ret = new HashMap<>();
        EnterInfo enterInfo = new EnterInfo();
        enterInfo.setEnterID(EnterID);
        enterInfo.setCertifier(Certifier);
        enterInfo.setCertifyOrg(CertifyOrg);
        enterInfo.setCertifyPlace(CertifyPlace);

        if (CertifyYear != null) {
            DateParser dateParser = new DateParser();
            enterInfo.setCertifyYear(dateParser.convert(CertifyYear));
        }

        enterInfo.setOperationRange(OperationRange);
        enterInfo.setEnterRemark(EnterRemark);
        enterService.addEnterInfo(enterInfo);
        ret.put("msg", "200");
        ret.put("description", "成功添加保存信息");
        return ret;
    }

    @ApiOperation(value = "添加分享信息")
    @PostMapping("Info/AddShareInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ShareID", value = "ShareID", required = true),
            @ApiImplicitParam(name = "ShareObj", value = "ShareObj", required = true),
            @ApiImplicitParam(name = "ContactInfo", value = "ContactInfo", required = true),
            @ApiImplicitParam(name = "ShareMode", value = "ShareMode", required = true),
            @ApiImplicitParam(name = "ShareUse", value = "ShareUse", required = true),
            @ApiImplicitParam(name = "ShareNum", value = "ShareNum", required = true),
            @ApiImplicitParam(name = "ShareBeginTime", value = "ShareBeginTime", required = true),
            @ApiImplicitParam(name = "ShareEndTime", value = "ShareEndTime", required = true),

    })
    public Map<String, Object> addShareInfoInSql(@RequestParam("ShareID") String ShareID,
                                                 @RequestParam("ShareObj") String ShareObj,
                                                 @RequestParam("ContactInfo") String ContactInfo,
                                                 @RequestParam("ShareMode") int ShareMode,
                                                 @RequestParam("ShareUse") int ShareUse,
                                                 @RequestParam("ShareNum") int ShareNum,
                                                 @RequestParam("ShareBeginTime") String ShareBeginTime,
                                                 @RequestParam("ShareEndTime") String ShareEndTime) {
        Map<String, Object> ret = new HashMap<>();
        ShareInfo shareInfo = new ShareInfo();
        shareInfo.setShareID(ShareID);
        shareInfo.setShareObj(ShareObj);
        shareInfo.setContactInfo(ContactInfo);
        shareInfo.setShareMode(ShareMode);
        shareInfo.setShareUse(ShareUse);
        shareInfo.setShareNum(ShareNum);

        if (ShareBeginTime != null) {
            DateParser dateParser = new DateParser();
            shareInfo.setShareBeginTime(dateParser.convert(ShareBeginTime));
        }

        if (ShareEndTime != null) {
            DateParser dateParser = new DateParser();
            shareInfo.setShareEndTime(dateParser.convert(ShareEndTime));
        }
        shareService.addShareInfo(shareInfo);
        ret.put("msg", "200");
        ret.put("description", "成功添加分享信息");
        return ret;
    }
}
