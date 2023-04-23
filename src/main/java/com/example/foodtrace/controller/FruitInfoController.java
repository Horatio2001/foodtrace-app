package com.example.foodtrace.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.foodtrace.entity.*;
import com.example.foodtrace.pojo.MyBlockInfo;
import com.example.foodtrace.service.*;
import com.example.foodtrace.util.DateParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.sql.Date;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private ChainCodeService chainCodeService;

//    @ApiOperation(value = "创建初始化信息")
//    @PostMapping("Info/CreateFruitInfoInSql")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
//    })
//    public Map<String, Object> createFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID) {
//        Map<String, Object> ret = new HashMap<>();
//        fruitInfoService.createFruitInfo(fruitInfoID);
//        ret.put("msg", "200");
//        ret.put("description", "发布商品成功");
//        return ret;
//    }

    @ApiOperation(value = "删除信息")
    @PostMapping("Info/DeleteFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> deleteFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo previousStatus = fruitInfoService.getStatus(fruitInfoID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法删除已经存证的收集信息");
            return ret;
        }

        fruitInfoService.deleteFruitInfo(fruitInfoID);
        collectService.deleteCollectInfo(fruitInfoID);
        enterService.deleteEnterInfo(fruitInfoID);
        shareService.deleteShareInfo(fruitInfoID);
        saveService.deleteSaveInfo(fruitInfoID);
        ret.put("msg", "200");
        ret.put("description", "删除商品成功");
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

        fruitInfoService.createFruitInfo(CollectID);
        collectService.addCollectInfo(collectInfo);
        ret.put("msg", "200");
        ret.put("description", "成功添加收集信息");
        return ret;
    }

    @ApiOperation(value = "拒绝增加采集信息")
    @PostMapping("Info/RefuseCollectInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> refuseCollectInfoInSql(@RequestParam("fruitInfoID") String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo previousStatus = fruitInfoService.getStatus(fruitInfoID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法反驳已经存证的收集信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法反驳已经反驳的收集信息");
            return ret;
        }
        if (previousStatus.getStatus() != 0) {
            ret.put("msg", "-202");
            ret.put("description", "当前状态并非收集，无法反驳！");
            return ret;
        }
        fruitInfoService.refuseCollect(fruitInfoID);
        ret.put("msg", "200");
        ret.put("description", "反驳收集成功");
        return ret;
    }

    @ApiOperation(value = "修改采集信息")
    @PostMapping("Info/ModifyCollectInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "CollectID", value = "CollectID", required = true),
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
    public Map<String, Object> modifyCollectInfoInSql(@RequestParam("CollectID") String CollectID,
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
        FruitInfo previousStatus = fruitInfoService.getStatus(CollectID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法修改已经存证的收集信息");
            return ret;
        }
        CollectInfo collectInfo = new CollectInfo();
        collectInfo.setCollectID(CollectID);
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

        collectService.modifyCollectInfo(collectInfo);
        fruitInfoService.setStatus(CollectID, 0);

        ret.put("msg", "200");
        ret.put("description", "成功修改收集信息");
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

        FruitInfo previousStatus = fruitInfoService.getStatus(SaveID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法添加已经存证的保存信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法添加正在反驳的保存信息");
            return ret;
        }
        if (previousStatus.getStatus() != 0) {
            ret.put("msg", "-202");
            ret.put("description", "前一状态状态并非收集，无法保存！");
            return ret;
        }

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
        fruitInfoService.setStatus(SaveID, 1);
        ret.put("msg", "200");
        ret.put("description", "成功添加保存信息");
        return ret;
    }

    @ApiOperation(value = "拒绝添加保存信息")
    @PostMapping("Info/RefuseSaveInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> refuseSaveInfoInSql(@RequestParam("fruitInfoID") String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo previousStatus = fruitInfoService.getStatus(fruitInfoID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法反驳已经存证的保存信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法反驳已经反驳的保存信息");
            return ret;
        }
        if (previousStatus.getStatus() != 1) {
            ret.put("msg", "-202");
            ret.put("description", "当前状态并非保存，无法反驳！");
            return ret;
        }
        fruitInfoService.refuseSave(fruitInfoID);
        ret.put("msg", "200");
        ret.put("description", "反驳保存成功");
        return ret;
    }

    @ApiOperation(value = "修改保存信息")
    @PostMapping("Info/ModifySaveInfoInSql")
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
    public Map<String, Object> modifySaveInfoInSql(@RequestParam("SaveID") String SaveID,
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

        FruitInfo previousStatus = fruitInfoService.getStatus(SaveID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法修改已经存证的保存信息");
            return ret;
        }
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
            saveInfo.setSaveYear(Integer.parseInt(WarehousingYear));
        }

        saveInfo.setSaveProperty(SaveProperty);
        saveInfo.setResourceDescription(ResourceDescription);
        saveInfo.setResourceRemark(ResourceRemark);
        saveInfo.setGermplasmImage(GermplasmImage);
        saveService.modifySaveInfo(saveInfo);
        fruitInfoService.setStatus(SaveID, 1);
        ret.put("msg", "200");
        ret.put("description", "成功修改保存信息");
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

        FruitInfo previousStatus = fruitInfoService.getStatus(EnterID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法添加已经存证的录入信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法添加正在反驳的录入信息");
            return ret;
        }
        if (previousStatus.getStatus() != 1) {
            ret.put("msg", "-202");
            ret.put("description", "前一状态状态并非保存，无法录入！");
            return ret;
        }

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
        fruitInfoService.setStatus(EnterID, 2);
        ret.put("msg", "200");
        ret.put("description", "成功添加录入信息");
        return ret;
    }

    @ApiOperation(value = "修改录入信息")
    @PostMapping("Info/ModifyEnterInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "EnterID", value = "EnterID", required = true),
            @ApiImplicitParam(name = "Certifier", value = "Certifier", required = true),
            @ApiImplicitParam(name = "CertifyOrg", value = "CertifyOrg", required = true),
            @ApiImplicitParam(name = "CertifyPlace", value = "CertifyPlace", required = true),
            @ApiImplicitParam(name = "CertifyYear", value = "CertifyYear", required = true),
            @ApiImplicitParam(name = "OperationRange", value = "OperationRange", required = true),
            @ApiImplicitParam(name = "EnterRemark", value = "EnterRemark", required = true)
    })
    public Map<String, Object> modifyEnterInfoInSql(@RequestParam("EnterID") String EnterID,
                                                    @RequestParam("Certifier") String Certifier,
                                                    @RequestParam("CertifyOrg") String CertifyOrg,
                                                    @RequestParam("CertifyPlace") String CertifyPlace,
                                                    @RequestParam("CertifyYear") String CertifyYear,
                                                    @RequestParam("OperationRange") String OperationRange,
                                                    @RequestParam("EnterRemark") String EnterRemark) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(EnterID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法修改已经存证的录入信息");
            return ret;
        }

        EnterInfo enterInfo = new EnterInfo();
        enterInfo.setEnterID(EnterID);
        enterInfo.setCertifier(Certifier);
        enterInfo.setCertifyOrg(CertifyOrg);
        enterInfo.setCertifyPlace(CertifyPlace);

        if (CertifyYear != null) {
            DateParser dateParser = new DateParser();
            enterInfo.setCertifyYear(dateParser.convert(CertifyYear));
            enterInfo.setEnterYear(Integer.parseInt(CertifyYear));
        }

        enterInfo.setOperationRange(OperationRange);
        enterInfo.setEnterRemark(EnterRemark);
        enterService.modifyEnterInfo(enterInfo);
        fruitInfoService.setStatus(EnterID, 2);
        ret.put("msg", "200");
        ret.put("description", "成功修改录入信息");
        return ret;
    }

    @ApiOperation(value = "拒绝增加录入信息")
    @PostMapping("Info/RefuseEnterInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> refuseEnterInfoInSql(@RequestParam("fruitInfoID") String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo previousStatus = fruitInfoService.getStatus(fruitInfoID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法反驳已经存证的录入信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法反驳已经反驳的录入信息");
            return ret;
        }
        if (previousStatus.getStatus() != 2) {
            ret.put("msg", "-202");
            ret.put("description", "当前状态并非录入，无法反驳！");
            return ret;
        }
        fruitInfoService.refuseEnter(fruitInfoID);
        ret.put("msg", "200");
        ret.put("description", "反驳录入成功");
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

        FruitInfo previousStatus = fruitInfoService.getStatus(ShareID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法添加已经存证的分享信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法添加正在反驳的分享信息");
            return ret;
        }
        if (previousStatus.getStatus() != 2) {
            ret.put("msg", "-202");
            ret.put("description", "前一状态状态并非录入，无法分享！");
            return ret;
        }

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
        fruitInfoService.setStatus(ShareID, 3);
        ret.put("msg", "200");
        ret.put("description", "成功添加分享信息");
        return ret;
    }

    @ApiOperation(value = "修改分享信息")
    @PostMapping("Info/ModifyShareInfoInSql")
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
    public Map<String, Object> modifyShareInfoInSql(@RequestParam("ShareID") String ShareID,
                                                    @RequestParam("ShareObj") String ShareObj,
                                                    @RequestParam("ContactInfo") String ContactInfo,
                                                    @RequestParam("ShareMode") int ShareMode,
                                                    @RequestParam("ShareUse") int ShareUse,
                                                    @RequestParam("ShareNum") int ShareNum,
                                                    @RequestParam("ShareBeginTime") String ShareBeginTime,
                                                    @RequestParam("ShareEndTime") String ShareEndTime) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(ShareID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法修改已经存证的分享信息");
            return ret;
        }

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
        shareService.modifyShareInfo(shareInfo);
        fruitInfoService.setStatus(ShareID, 3);
        ret.put("msg", "200");
        ret.put("description", "成功修改分享信息");
        return ret;
    }

    @ApiOperation(value = "拒绝增加分享信息")
    @PostMapping("Info/RefuseShareInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> refuseShareInfoInSql(@RequestParam("fruitInfoID") String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo previousStatus = fruitInfoService.getStatus(fruitInfoID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法反驳已经存证的分享信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法反驳已经反驳的分享信息");
            return ret;
        }
        if (previousStatus.getStatus() != 3) {
            ret.put("msg", "-202");
            ret.put("description", "当前状态并非分享，无法反驳！");
            return ret;
        }
        fruitInfoService.refuseShare(fruitInfoID);
        ret.put("msg", "200");
        ret.put("description", "反驳分享成功");
        return ret;
    }

    @ApiOperation(value = "信息存证")
    @PostMapping("Info/LoadInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true),
            @ApiImplicitParam(name = "txHash", value = "交易哈希", required = true)

    })
    public Map<String, Object> loadInfoInSql(@RequestParam("fruitInfoID") String fruitInfoID, @RequestParam("txHash") String txHash) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo previousStatus = fruitInfoService.getStatus(fruitInfoID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("msg", "-200");
            ret.put("description", "无法存证已经存证的信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("msg", "-201");
            ret.put("description", "无法存证正在反驳的信息");
            return ret;
        }
        if (previousStatus.getStatus() != 3) {
            ret.put("msg", "-202");
            ret.put("description", "当前状态并非分享，无法存证！");
            return ret;
        }
        fruitInfoService.loadInfo(fruitInfoID, txHash);
        ret.put("msg", "200");
        ret.put("description", "存证成功");
        return ret;
    }

    @ApiOperation(value = "生成数权证书")
    @PostMapping("Info/generateCertificate/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> generateCertificate(@PathVariable String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo fruitInfo = fruitInfoService.getStatus(fruitInfoID);
        if (fruitInfo==null) {
            ret.put("msg", "404");
            ret.put("description", "对象不存在");
            return ret;
        }
        if (fruitInfo.getIsLoaded() == 0) {
            ret.put("msg", "-200");
            ret.put("description", "尚未存证");
            return ret;
        }
        Certificate certificate = new Certificate();
        CollectInfo collectInfo = collectService.queryCollectInfo(fruitInfoID);
        SaveInfo saveInfo = saveService.querySaveInfo(fruitInfoID);
        certificate.setID(fruitInfoID);
        certificate.setHash(fruitInfo.getBlockHash());
        certificate.setName(collectInfo.getName());
        certificate.setCollectUnit(collectInfo.getCollectUnit());
        certificate.setMainUse(saveInfo.getMainUse());
        certificate.setResourceRemark(saveInfo.getResourceRemark());

//        MyBlockInfo myBlockInfo = chainCodeService.ReadFruitInfo(fruitInfoID)
        certificateService.generateCertificate(certificate);
        ret.put("msg", "200");
        ret.put("description", "证书生成成功");
        return ret;
    }

    @ApiOperation(value = "查询证书")
    @GetMapping("Info/queryCertificate/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> queryCertificate(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        Certificate certificate = certificateService.queryCertificate(fruitInfoID);
        if (certificate == null) {
            ret.put("msg", "-200");
            ret.put("description", "证书不存在");
            return ret;
        }
        ret.put("certificate", certificate);
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据id查询信息")
    @PostMapping("Info/QueryInfoByID/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> queryInfoByID(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        CollectInfo collectInfo = collectService.queryCollectInfo(fruitInfoID);
        if (collectInfo == null) {
            ret.put("msg", "-200");
            ret.put("description", "该信息不存在");
            return ret;
        }
        ret.put("CollectInfo", collectInfo);
        ret.put("SaveInfo", saveService.querySaveInfo(fruitInfoID));
        ret.put("EnterInfo", enterService.queryEnterInfo(fruitInfoID));
        ret.put("ShareInfo", shareService.queryShareInfo(fruitInfoID));
        ret.put("Status", fruitInfoService.getStatus(fruitInfoID));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询信息")
    @GetMapping("Info/QueryInfoByPage/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true)
    })
    public Map<String, Object> queryInfoByPage(@PathVariable int pageNum) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("infoList", collectService.queryInfosByPage(pageNum));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询信息(第一页)")
    @GetMapping("Info/QueryInfoByFirstPage")
    public Map<String, Object> queryInfoByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("infoList", collectService.queryInfosByPage(1));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询已存证信息")
    @GetMapping("Info/QueryDocumentedInfoByPage/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true)
    })
    public Map<String, Object> queryDocumentedInfoByPage(@PathVariable int pageNum) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("infoList", collectService.queryDocumentedInfosByPage(pageNum));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询已存证信息{第一页}")
    @GetMapping("Info/QueryDocumentedInfoByFirstPage")
    public Map<String, Object> queryDocumentedInfoByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("infoList", collectService.queryDocumentedInfosByPage(1));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询收集信息")
    @GetMapping("Info/QueryCollectInfosByPage/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true)
    })
    public Map<String, Object> queryCollectInfosByPage(@PathVariable int pageNum) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("CollectList", collectService.queryCollectInfosByPage(pageNum));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询收集信息{第一页}")
    @GetMapping("Info/QueryCollectInfosByFirstPage")
    public Map<String, Object> queryCollectInfosByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("CollectList", collectService.queryCollectInfosByPage(1));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询保存信息")
    @GetMapping("Info/QuerySaveInfosByPage/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true)
    })
    public Map<String, Object> querySaveInfosByPage(@PathVariable int pageNum) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("SaveList", saveService.querySaveInfosByPage(pageNum));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询保存信息{第一页}")
    @GetMapping("Info/QuerySaveInfosByFirstPage")
    public Map<String, Object> querySaveInfosByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("SaveList", saveService.querySaveInfosByPage(1));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询录入信息")
    @GetMapping("Info/QueryEnterInfosByPage/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true)
    })
    public Map<String, Object> queryEnterInfosByPage(@PathVariable int pageNum) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("EnterList", enterService.queryEnterInfosByPage(pageNum));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询录入信息{第一页}")
    @GetMapping("Info/QueryEnterInfosByPage")
    public Map<String, Object> queryEnterInfosByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("EnterList", enterService.queryEnterInfosByPage(1));
        ret.put("msg", "200");
        ret.put("description", "查询成功");
        return ret;
    }
}
