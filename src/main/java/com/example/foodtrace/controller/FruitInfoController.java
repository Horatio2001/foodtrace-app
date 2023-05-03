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
import org.checkerframework.checker.units.qual.C;
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
//        ret.put("code", 200);
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
            ret.put("code", "-200");
            ret.put("description", "无法删除已经存证的收集信息");
            return ret;
        }
        try {
            ret.put("data", chainCodeService.DeleteFruitInfo(fruitInfoID));
            fruitInfoService.deleteFruitInfo(fruitInfoID);
            collectService.deleteCollectInfo(fruitInfoID);
            enterService.deleteEnterInfo(fruitInfoID);
            shareService.deleteShareInfo(fruitInfoID);
            saveService.deleteSaveInfo(fruitInfoID);
            ret.put("code", 200);
            ret.put("description", "删除商品成功");

        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("data", "你无法删除已经存证的信息！");
            ret.put("code", -200);
        }
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
        String[] args = new String[]{CollectID == null ? "\"\"" : "\"" + CollectID + "\"", Type == null ? "\"\"" : "\"" + Type + "\""
                , Name == null ? "\"\"" : "\"" + Name + "\"", GermplasmName == null ? "\"\"" : "\"" + GermplasmName + "\""
                , GermplasmNameEn == null ? "\"\"" : "\"" + GermplasmNameEn + "\"", SectionName == null ? "\"\"" : "\"" + SectionName + "\""
                , GenericName == null ? "\"\"" : "\"" + GenericName + "\"", ScientificName == null ? "\"\"" : "\"" + ScientificName + "\""
                , ResourceType == null ? "\"\"" : "\"" + ResourceType + "\"", CollectMethod == null ? "\"\"" : "\"" + CollectMethod + "\""
                , GermplasmSource == null ? "\"\"" : "\"" + GermplasmSource + "\"", SourceCountry == null ? "\"\"" : "\"" + SourceCountry + "\""
                , SourceProvince == null ? "\"\"" : "\"" + SourceProvince + "\"", Source == null ? "\"\"" : "\"" + Source + "\""
                , SourceOrg == null ? "\"\"" : "\"" + SourceOrg + "\"", OriginCountry == null ? "\"\"" : "\"" + OriginCountry + "\""
                , OriginPlace == null ? "\"\"" : "\"" + OriginPlace + "\""
                , CollectPlaceLongitude == null ? "\"\"" : "\"" + CollectPlaceLongitude + "\""
                , CollectPlaceLatitude == null ? "\"\"" : "\"" + CollectPlaceLatitude + "\""
                , CollectPlaceAltitude == null ? "\"\"" : "\"" + CollectPlaceAltitude + "\""
                , CollectPlaceSoilType == null ? "\"\"" : "\"" + CollectPlaceSoilType + "\""
                , CollectPlaceEcologyType == null ? "\"\"" : "\"" + CollectPlaceEcologyType + "\""
                , CollectMaterialType == null ? "\"\"" : "\"" + CollectMaterialType + "\""
                , CollectPeople == null ? "\"\"" : "\"" + CollectPeople + "\"", CollectUnit == null ? "\"\"" : "\"" + CollectUnit + "\""
                , CollectTime == null ? "\"\"" : "\"" + CollectTime + "\"", SpeciesName == null ? "\"\"" : "\"" + SpeciesName + "\""
                , Image == null ? "\"\"" : "\"" + Image + "\"", CollectRemark == null ? "\"\"" : "\"" + CollectRemark + "\""
        };
        try {
            fruitInfoService.createFruitInfo(CollectID, Type, Name);
            collectService.addCollectInfo(collectInfo);
            ret.put("data", chainCodeService.CreateFruitInfo(CollectID, args));
            ret.put("code", 200);
            ret.put("description", "成功添加收集信息");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }

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
            ret.put("code", "-200");
            ret.put("description", "无法反驳已经存证的收集信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法反驳已经反驳的收集信息");
            return ret;
        }
        if (previousStatus.getStatus() != 0) {
            ret.put("code", "-202");
            ret.put("description", "当前状态并非收集，无法反驳！");
            return ret;
        }
        try {
            ret.put("data", chainCodeService.RejectCreate(fruitInfoID));
            fruitInfoService.refuseCollect(fruitInfoID);
            ret.put("code", 200);
            ret.put("description", "反驳收集成功");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
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
            ret.put("code", "-200");
            ret.put("description", "无法修改已经存证的收集信息");
            return ret;
        }
        CollectInfo previousCollectInfo = collectService.queryCollectInfo(CollectID);
        Integer Type = previousCollectInfo.getType();
        String Name = previousCollectInfo.getName();

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
        String[] args = new String[]{CollectID == null ? "\"\"" : "\"" + CollectID + "\"", Type == null ? "\"\"" : "\"" + Type + "\""
                , Name == null ? "\"\"" : "\"" + Name + "\"", GermplasmName == null ? "\"\"" : "\"" + GermplasmName + "\""
                , GermplasmNameEn == null ? "\"\"" : "\"" + GermplasmNameEn + "\"", SectionName == null ? "\"\"" : "\"" + SectionName + "\""
                , GenericName == null ? "\"\"" : "\"" + GenericName + "\"", ScientificName == null ? "\"\"" : "\"" + ScientificName + "\""
                , ResourceType == null ? "\"\"" : "\"" + ResourceType + "\"", CollectMethod == null ? "\"\"" : "\"" + CollectMethod + "\""
                , GermplasmSource == null ? "\"\"" : "\"" + GermplasmSource + "\"", SourceCountry == null ? "\"\"" : "\"" + SourceCountry + "\""
                , SourceProvince == null ? "\"\"" : "\"" + SourceProvince + "\"", Source == null ? "\"\"" : "\"" + Source + "\""
                , SourceOrg == null ? "\"\"" : "\"" + SourceOrg + "\"", OriginCountry == null ? "\"\"" : "\"" + OriginCountry + "\""
                , OriginPlace == null ? "\"\"" : "\"" + OriginPlace + "\""
                , CollectPlaceLongitude == null ? "\"\"" : "\"" + CollectPlaceLongitude + "\""
                , CollectPlaceLatitude == null ? "\"\"" : "\"" + CollectPlaceLatitude + "\""
                , CollectPlaceAltitude == null ? "\"\"" : "\"" + CollectPlaceAltitude + "\""
                , CollectPlaceSoilType == null ? "\"\"" : "\"" + CollectPlaceSoilType + "\""
                , CollectPlaceEcologyType == null ? "\"\"" : "\"" + CollectPlaceEcologyType + "\""
                , CollectMaterialType == null ? "\"\"" : "\"" + CollectMaterialType + "\""
                , CollectPeople == null ? "\"\"" : "\"" + CollectPeople + "\"", CollectUnit == null ? "\"\"" : "\"" + CollectUnit + "\""
                , CollectTime == null ? "\"\"" : "\"" + CollectTime + "\"", SpeciesName == null ? "\"\"" : "\"" + SpeciesName + "\""
                , Image == null ? "\"\"" : "\"" + Image + "\"", CollectRemark == null ? "\"\"" : "\"" + CollectRemark + "\""
        };
        try {
            ret.put("data", chainCodeService.ModifyCreateFruitInfo(CollectID, args));
            collectService.modifyCollectInfo(collectInfo);
            fruitInfoService.setStatus(CollectID, 0);
            ret.put("code", 200);
            ret.put("description", "成功修改收集信息");

        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "添加保存信息")
    @PostMapping("Info/AddSaveInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SaveID", value = "SaveID", required = true),
            @ApiImplicitParam(name = "MainPreference", value = "MainPreference", required = true),
            @ApiImplicitParam(name = "MainUse", value = "MainUse"),
            @ApiImplicitParam(name = "PreservationFacility", value = "PreservationFacility"),
            @ApiImplicitParam(name = "GermplasmType", value = "GermplasmType"),
            @ApiImplicitParam(name = "SaveQuantity", value = "SaveQuantity"),
            @ApiImplicitParam(name = "MeasuringUnit", value = "MeasuringUnit"),
            @ApiImplicitParam(name = "SaveUnit", value = "SaveUnit"),
            @ApiImplicitParam(name = "SaveVault", value = "SaveVault"),
            @ApiImplicitParam(name = "SavePlace", value = "SavePlace"),
            @ApiImplicitParam(name = "WarehousingYear", value = "WarehousingYear"),
            @ApiImplicitParam(name = "SaveProperty", value = "SaveProperty"),
            @ApiImplicitParam(name = "ResourceDescription", value = "ResourceDescription"),
            @ApiImplicitParam(name = "ResourceMark", value = "ResourceMark"),
            @ApiImplicitParam(name = "GermplasmImage", value = "GermplasmImage")
    })
    public Map<String, Object> addSaveInfoInSql(@RequestParam("SaveID") String SaveID,
                                                @RequestParam("MainPreference") Integer MainPreference,
                                                @RequestParam(value = "MainUse", required = false) String MainUse,
                                                @RequestParam(value = "PreservationFacility", required = false) String PreservationFacility,
                                                @RequestParam(value = "GermplasmType", required = false) String GermplasmType,
                                                @RequestParam(value = "SaveQuantity", required = false) Integer SaveQuantity,
                                                @RequestParam(value = "MeasuringUnit", required = false) Integer MeasuringUnit,
                                                @RequestParam(value = "SaveUnit", required = false) String SaveUnit,
                                                @RequestParam(value = "SaveVault", required = false) String SaveVault,
                                                @RequestParam(value = "SavePlace", required = false) String SavePlace,
                                                @RequestParam(value = "WarehousingYear", required = false) String WarehousingYear,
                                                @RequestParam(value = "SaveProperty", required = false) String SaveProperty,
                                                @RequestParam(value = "ResourceDescription", required = false) String ResourceDescription,
                                                @RequestParam(value = "ResourceMark", required = false) String ResourceRemark,
                                                @RequestParam(value = "GermplasmImage", required = false) String GermplasmImage) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(SaveID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("code", "-200");
            ret.put("description", "无法添加已经存证的保存信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法添加正在反驳的保存信息");
            return ret;
        }
        if (previousStatus.getStatus() != 0) {
            ret.put("code", "-202");
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
        String[] args = new String[]{MainPreference == null ? "\"\"" : "\"" + MainPreference + "\""
                , MainUse == null ? "\"\"" : "\"" + MainUse + "\""
                , PreservationFacility == null ? "\"\"" : "\"" + PreservationFacility + "\""
                , GermplasmType == null ? "\"\"" : "\"" + GermplasmType + "\""
                , SaveQuantity == null ? "\"\"" : "\"" + SaveQuantity + "\""
                , MeasuringUnit == null ? "\"\"" : "\"" + MeasuringUnit + "\""
                , SaveUnit == null ? "\"\"" : "\"" + SaveUnit + "\"", SaveVault == null ? "\"\"" : "\"" + SaveVault + "\"",
                SavePlace == null ? "\"\"" : "\"" + SavePlace + "\""
                , WarehousingYear == null ? "\"\"" : "\"" + WarehousingYear + "\""
                , SaveProperty == null ? "\"\"" : "\"" + SaveProperty + "\""
                , ResourceDescription == null ? "\"\"" : "\"" + ResourceDescription + "\""
                , ResourceRemark == null ? "\"\"" : "\"" + ResourceRemark + "\""
                , GermplasmImage == null ? "\"\"" : "\"" + GermplasmImage + "\""
        };

        try {
            ret.put("data", chainCodeService.SaveFruitInfo(SaveID, args));
            saveService.addSaveInfo(saveInfo);
            fruitInfoService.setStatus(SaveID, 1);
            ret.put("code", 200);
            ret.put("description", "成功添加保存信息");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
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
            ret.put("code", "-200");
            ret.put("description", "无法反驳已经存证的保存信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法反驳已经反驳的保存信息");
            return ret;
        }
        if (previousStatus.getStatus() != 1) {
            ret.put("code", "-202");
            ret.put("description", "当前状态并非保存，无法反驳！");
            return ret;
        }

        try {
            ret.put("data", chainCodeService.RejectSave(fruitInfoID));
            fruitInfoService.refuseSave(fruitInfoID);
            ret.put("code", 200);
            ret.put("description", "反驳保存成功");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        ret.put("code", 200);
        return ret;
    }

    @ApiOperation(value = "修改保存信息")
    @PostMapping("Info/ModifySaveInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "SaveID", value = "SaveID", required = true),
            @ApiImplicitParam(name = "MainPreference", value = "MainPreference", required = true),
            @ApiImplicitParam(name = "MainUse", value = "MainUse"),
            @ApiImplicitParam(name = "PreservationFacility", value = "PreservationFacility"),
            @ApiImplicitParam(name = "GermplasmType", value = "GermplasmType"),
            @ApiImplicitParam(name = "SaveQuantity", value = "SaveQuantity"),
            @ApiImplicitParam(name = "MeasuringUnit", value = "MeasuringUnit"),
            @ApiImplicitParam(name = "SaveUnit", value = "SaveUnit"),
            @ApiImplicitParam(name = "SaveVault", value = "SaveVault"),
            @ApiImplicitParam(name = "SavePlace", value = "SavePlace"),
            @ApiImplicitParam(name = "WarehousingYear", value = "WarehousingYear"),
            @ApiImplicitParam(name = "SaveProperty", value = "SaveProperty"),
            @ApiImplicitParam(name = "ResourceDescription", value = "ResourceDescription"),
            @ApiImplicitParam(name = "ResourceMark", value = "ResourceMark"),
            @ApiImplicitParam(name = "GermplasmImage", value = "GermplasmImage"),
    })
    public Map<String, Object> modifySaveInfoInSql(@RequestParam("SaveID") String SaveID,
                                                   @RequestParam("MainPreference") Integer MainPreference,
                                                   @RequestParam(value = "MainUse", required = false) String MainUse,
                                                   @RequestParam(value = "PreservationFacility", required = false) String PreservationFacility,
                                                   @RequestParam(value = "GermplasmType", required = false) String GermplasmType,
                                                   @RequestParam(value = "SaveQuantity", required = false) Integer SaveQuantity,
                                                   @RequestParam(value = "MeasuringUnit", required = false) Integer MeasuringUnit,
                                                   @RequestParam(value = "SaveUnit", required = false) String SaveUnit,
                                                   @RequestParam(value = "SaveVault", required = false) String SaveVault,
                                                   @RequestParam(value = "SavePlace", required = false) String SavePlace,
                                                   @RequestParam(value = "WarehousingYear", required = false) String WarehousingYear,
                                                   @RequestParam(value = "SaveProperty", required = false) String SaveProperty,
                                                   @RequestParam(value = "ResourceDescription", required = false) String ResourceDescription,
                                                   @RequestParam(value = "ResourceMark", required = false) String ResourceRemark,
                                                   @RequestParam(value = "GermplasmImage", required = false) String GermplasmImage) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(SaveID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("code", "-200");
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
        String[] args = new String[]{MainPreference == null ? "\"\"" : "\"" + MainPreference + "\""
                , MainUse == null ? "\"\"" : "\"" + MainUse + "\""
                , PreservationFacility == null ? "\"\"" : "\"" + PreservationFacility + "\""
                , GermplasmType == null ? "\"\"" : "\"" + GermplasmType + "\""
                , SaveQuantity == null ? "\"\"" : "\"" + SaveQuantity + "\""
                , MeasuringUnit == null ? "\"\"" : "\"" + MeasuringUnit + "\""
                , SaveUnit == null ? "\"\"" : "\"" + SaveUnit + "\"", SaveVault == null ? "\"\"" : "\"" + SaveVault + "\"",
                SavePlace == null ? "\"\"" : "\"" + SavePlace + "\""
                , WarehousingYear == null ? "\"\"" : "\"" + WarehousingYear + "\""
                , SaveProperty == null ? "\"\"" : "\"" + SaveProperty + "\""
                , ResourceDescription == null ? "\"\"" : "\"" + ResourceDescription + "\""
                , ResourceRemark == null ? "\"\"" : "\"" + ResourceRemark + "\""
                , GermplasmImage == null ? "\"\"" : "\"" + GermplasmImage + "\""
        };

        try {
            ret.put("data", chainCodeService.ModifySaveFruitInfo(SaveID, args));
            saveService.modifySaveInfo(saveInfo);
            fruitInfoService.setStatus(SaveID, 1);
            ret.put("code", 200);
            ret.put("description", "成功修改保存信息");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "添加录入信息")
    @PostMapping("Info/AddEnterInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "EnterID", value = "EnterID", required = true),
            @ApiImplicitParam(name = "Certifier", value = "Certifier", required = true),
            @ApiImplicitParam(name = "CertifyOrg", value = "CertifyOrg", required = true),
            @ApiImplicitParam(name = "CertifyPlace", value = "CertifyPlace"),
            @ApiImplicitParam(name = "CertifyYear", value = "CertifyYear"),
            @ApiImplicitParam(name = "OperationRange", value = "OperationRange"),
            @ApiImplicitParam(name = "EnterRemark", value = "EnterRemark")
    })
    public Map<String, Object> addEnterInfoInSql(@RequestParam("EnterID") String EnterID,
                                                 @RequestParam("Certifier") String Certifier,
                                                 @RequestParam("CertifyOrg") String CertifyOrg,
                                                 @RequestParam(value = "CertifyPlace", required = false) String CertifyPlace,
                                                 @RequestParam(value = "CertifyYear", required = false) String CertifyYear,
                                                 @RequestParam(value = "OperationRange", required = false) String OperationRange,
                                                 @RequestParam(value = "EnterRemark", required = false) String EnterRemark) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(EnterID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("code", "-200");
            ret.put("description", "无法添加已经存证的录入信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法添加正在反驳的录入信息");
            return ret;
        }
        if (previousStatus.getStatus() != 1) {
            ret.put("code", "-202");
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

        String[] args = new String[]{Certifier == null ? "\"\"" : "\"" + Certifier + "\""
                , CertifyOrg == null ? "\"\"" : "\"" + CertifyOrg + "\""
                , CertifyPlace == null ? "\"\"" : "\"" + CertifyPlace + "\""
                , CertifyYear == null ? "\"\"" : "\"" + CertifyYear + "\""
                , OperationRange == null ? "\"\"" : "\"" + OperationRange + "\""
                , EnterRemark == null ? "\"\"" : "\"" + EnterRemark + "\""
        };
        try {
            ret.put("data", chainCodeService.EnterFruitInfo(EnterID, args));
            enterService.addEnterInfo(enterInfo);
            fruitInfoService.setStatus(EnterID, 2);
            ret.put("code", 200);
            ret.put("description", "成功添加录入信息");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "修改录入信息")
    @PostMapping("Info/ModifyEnterInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "EnterID", value = "EnterID", required = true),
            @ApiImplicitParam(name = "Certifier", value = "Certifier", required = true),
            @ApiImplicitParam(name = "CertifyOrg", value = "CertifyOrg", required = true),
            @ApiImplicitParam(name = "CertifyPlace", value = "CertifyPlace"),
            @ApiImplicitParam(name = "CertifyYear", value = "CertifyYear"),
            @ApiImplicitParam(name = "OperationRange", value = "OperationRange"),
            @ApiImplicitParam(name = "EnterRemark", value = "EnterRemark")
    })
    public Map<String, Object> modifyEnterInfoInSql(@RequestParam("EnterID") String EnterID,
                                                    @RequestParam("Certifier") String Certifier,
                                                    @RequestParam("CertifyOrg") String CertifyOrg,
                                                    @RequestParam(value = "CertifyPlace", required = false) String CertifyPlace,
                                                    @RequestParam(value = "CertifyYear", required = false) String CertifyYear,
                                                    @RequestParam(value = "OperationRange", required = false) String OperationRange,
                                                    @RequestParam(value = "EnterRemark", required = false) String EnterRemark) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(EnterID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("code", "-200");
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
        String[] args = new String[]{Certifier == null ? "\"\"" : "\"" + Certifier + "\""
                , CertifyOrg == null ? "\"\"" : "\"" + CertifyOrg + "\""
                , CertifyPlace == null ? "\"\"" : "\"" + CertifyPlace + "\""
                , CertifyYear == null ? "\"\"" : "\"" + CertifyYear + "\""
                , OperationRange == null ? "\"\"" : "\"" + OperationRange + "\""
                , EnterRemark == null ? "\"\"" : "\"" + EnterRemark + "\""
        };

        try {
            ret.put("data", chainCodeService.ModifyEnterFruitInfo(EnterID, args));
            enterService.modifyEnterInfo(enterInfo);
            fruitInfoService.setStatus(EnterID, 2);
            ret.put("code", 200);
            ret.put("description", "成功修改录入信息");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }

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
            ret.put("code", "-200");
            ret.put("description", "无法反驳已经存证的录入信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法反驳已经反驳的录入信息");
            return ret;
        }
        if (previousStatus.getStatus() != 2) {
            ret.put("code", "-202");
            ret.put("description", "当前状态并非录入，无法反驳！");
            return ret;
        }
        try {
            ret.put("data", chainCodeService.RejectEnter(fruitInfoID));
            fruitInfoService.refuseEnter(fruitInfoID);
            ret.put("code", 200);
            ret.put("description", "反驳录入成功");

        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "添加分享信息")
    @PostMapping("Info/AddShareInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ShareID", value = "ShareID", required = true),
            @ApiImplicitParam(name = "ShareObj", value = "ShareObj"),
            @ApiImplicitParam(name = "ContactInfo", value = "ContactInfo", required = true),
            @ApiImplicitParam(name = "ShareMode", value = "ShareMode"),
            @ApiImplicitParam(name = "ShareUse", value = "ShareUse", required = true),
            @ApiImplicitParam(name = "ShareNum", value = "ShareNum", required = true),
            @ApiImplicitParam(name = "ShareBeginTime", value = "ShareBeginTime", required = true),
            @ApiImplicitParam(name = "ShareEndTime", value = "ShareEndTime", required = true)

    })
    public Map<String, Object> addShareInfoInSql(@RequestParam("ShareID") String ShareID,
                                                 @RequestParam(value="ShareObj", required = false) String ShareObj,
                                                 @RequestParam("ContactInfo") String ContactInfo,
                                                 @RequestParam(value="ShareMode", required = false) Integer ShareMode,
                                                 @RequestParam("ShareUse") Integer ShareUse,
                                                 @RequestParam("ShareNum") Integer ShareNum,
                                                 @RequestParam("ShareBeginTime") String ShareBeginTime,
                                                 @RequestParam("ShareEndTime") String ShareEndTime) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(ShareID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("code", "-200");
            ret.put("description", "无法添加已经存证的分享信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法添加正在反驳的分享信息");
            return ret;
        }
        if (previousStatus.getStatus() != 2) {
            ret.put("code", "-202");
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
        String[] args = new String[]{ShareObj == null ? "\"\"" : "\"" + ShareObj + "\""
                , ContactInfo == null ? "\"\"" : "\"" + ContactInfo + "\""
                , ShareMode == null ? "\"\"" : "\"" + ShareMode + "\""
                , ShareUse == null ? "\"\"" : "\"" + ShareUse + "\""
                , ShareNum == null ? "\"\"" : "\"" + ShareNum + "\""
                , ShareBeginTime == null ? "\"\"" : "\"" + ShareBeginTime + "\""
                , ShareEndTime == null ? "\"\"" : "\"" + ShareEndTime + "\""
        };
        try {
            ret.put("data", chainCodeService.ShareFruitInfo(ShareID, args));
            shareService.addShareInfo(shareInfo);
            fruitInfoService.setStatus(ShareID, 3);
            ret.put("code", 200);
            ret.put("description", "成功添加分享信息");

        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "修改分享信息")
    @PostMapping("Info/ModifyShareInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ShareID", value = "ShareID", required = true),
            @ApiImplicitParam(name = "ShareObj", value = "ShareObj"),
            @ApiImplicitParam(name = "ContactInfo", value = "ContactInfo", required = true),
            @ApiImplicitParam(name = "ShareMode", value = "ShareMode"),
            @ApiImplicitParam(name = "ShareUse", value = "ShareUse", required = true),
            @ApiImplicitParam(name = "ShareNum", value = "ShareNum", required = true),
            @ApiImplicitParam(name = "ShareBeginTime", value = "ShareBeginTime", required = true),
            @ApiImplicitParam(name = "ShareEndTime", value = "ShareEndTime", required = true)

    })
    public Map<String, Object> modifyShareInfoInSql(@RequestParam("ShareID") String ShareID,
                                                    @RequestParam(value="ShareObj", required = false) String ShareObj,
                                                    @RequestParam("ContactInfo") String ContactInfo,
                                                    @RequestParam(value="ShareMode", required = false) Integer ShareMode,
                                                    @RequestParam("ShareUse") Integer ShareUse,
                                                    @RequestParam("ShareNum") Integer ShareNum,
                                                    @RequestParam("ShareBeginTime") String ShareBeginTime,
                                                    @RequestParam("ShareEndTime") String ShareEndTime) {
        Map<String, Object> ret = new HashMap<>();

        FruitInfo previousStatus = fruitInfoService.getStatus(ShareID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("code", "-200");
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

        String[] args = new String[]{ShareObj == null ? "\"\"" : "\"" + ShareObj + "\""
                , ContactInfo == null ? "\"\"" : "\"" + ContactInfo + "\""
                , ShareMode == null ? "\"\"" : "\"" + ShareMode + "\""
                , ShareUse == null ? "\"\"" : "\"" + ShareUse + "\""
                , ShareNum == null ? "\"\"" : "\"" + ShareNum + "\""
                , ShareBeginTime == null ? "\"\"" : "\"" + ShareBeginTime + "\""
                , ShareEndTime == null ? "\"\"" : "\"" + ShareEndTime + "\""
        };
        try {
            ret.put("data", chainCodeService.ModifyShareFruitInfo(ShareID, args));
            shareService.modifyShareInfo(shareInfo);
            fruitInfoService.setStatus(ShareID, 3);
            ret.put("code", 200);
            ret.put("description", "成功修改分享信息");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
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
            ret.put("code", "-200");
            ret.put("description", "无法反驳已经存证的分享信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法反驳已经反驳的分享信息");
            return ret;
        }
        if (previousStatus.getStatus() != 3) {
            ret.put("code", "-202");
            ret.put("description", "当前状态并非分享，无法反驳！");
            return ret;
        }
        try {
            ret.put("data", chainCodeService.RejectShare(fruitInfoID));
            fruitInfoService.refuseShare(fruitInfoID);
            ret.put("code", 200);
            ret.put("description", "反驳分享成功");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "信息存证")
    @PostMapping("Info/LoadInfoInSql")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)

    })
    public Map<String, Object> loadInfoInSql(@RequestParam("fruitInfoID") String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo previousStatus = fruitInfoService.getStatus(fruitInfoID);
        if (previousStatus.getIsLoaded() == 1) {
            ret.put("code", "-200");
            ret.put("description", "无法存证已经存证的信息");
            return ret;
        }
        if (previousStatus.getIsContradict() == 1) {
            ret.put("code", "-201");
            ret.put("description", "无法存证正在反驳的信息");
            return ret;
        }
        if (previousStatus.getStatus() != 3) {
            ret.put("code", "-202");
            ret.put("description", "当前状态并非分享，无法存证！");
            return ret;
        }

        try {
            String[] loadConfig = chainCodeService.LoadFruitInfo(fruitInfoID);
            ret.put("data", loadConfig[0]);
            fruitInfoService.loadInfo(fruitInfoID, loadConfig[1]);
            ret.put("code", 200);
            ret.put("description", "存证成功");
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "生成数权证书")
    @PostMapping("Info/generateCertificate/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> generateCertificate(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        FruitInfo fruitInfo = fruitInfoService.getStatus(fruitInfoID);
        if (fruitInfo == null) {
            ret.put("code", "404");
            ret.put("description", "对象不存在");
            return ret;
        }
        if (fruitInfo.getIsLoaded() == 0) {
            ret.put("code", "-200");
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
        fruitInfoService.certify(fruitInfoID);

//        MyBlockInfo myBlockInfo = chainCodeService.ReadFruitInfo(fruitInfoID)
        certificateService.generateCertificate(certificate);
        ret.put("code", 200);
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
            ret.put("code", "-200");
            ret.put("description", "证书不存在");
            return ret;
        }
        ret.put("data", certificate);
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据哈希查询证书")
    @GetMapping("Info/queryCertificateByHash/{hash}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hash", value = "交易哈希", required = true)
    })
    public Map<String, Object> queryCertificateByHash(@PathVariable String hash) {
        Map<String, Object> ret = new HashMap<>();
        Certificate certificate = certificateService.queryCertificateByHash(hash);
        if (certificate == null) {
            ret.put("code", 200);
            ret.put("data", 200);
            ret.put("description", "证书不存在");
            return ret;
        }
        ret.put("data", certificate);
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据id查询信息")
    @GetMapping("Info/QueryInfoByID/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> queryInfoByID(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        CollectInfo collectInfo = collectService.queryCollectInfo(fruitInfoID);
        if (collectInfo == null) {
            ret.put("code", "-200");
            ret.put("description", "该信息不存在");
            return ret;
        }
        FruitInfo fruitInfo = fruitInfoService.getStatus(fruitInfoID);
        fruitInfo.setCollectInfo(collectInfo);
        fruitInfo.setSaveInfo(saveService.querySaveInfo(fruitInfoID));
        fruitInfo.setEnterInfo(enterService.queryEnterInfo(fruitInfoID));
        fruitInfo.setShareInfo(shareService.queryShareInfo(fruitInfoID));
        ret.put("data", fruitInfo);
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据id查询采集信息")
    @GetMapping("Info/QueryCollectInfoByID/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> queryCollectInfoByID(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        CollectInfo collectInfo = collectService.queryCollectInfo(fruitInfoID);
        if (collectInfo == null) {
            ret.put("code", "-200");
            ret.put("description", "该信息不存在");
            return ret;
        }
        ret.put("data", collectInfo);
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据id查询保存信息")
    @GetMapping("Info/QuerySaveInfoByID/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> querySaveInfoByID(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        CollectInfo collectInfo = collectService.queryCollectInfo(fruitInfoID);
        if (collectInfo == null) {
            ret.put("code", "-200");
            ret.put("description", "该信息不存在");
            return ret;
        }
        ret.put("data", saveService.querySaveInfo(fruitInfoID));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据id查询录入信息")
    @GetMapping("Info/QueryEnterInfoByID/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> queryEnterInfoByID(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        CollectInfo collectInfo = collectService.queryCollectInfo(fruitInfoID);
        if (collectInfo == null) {
            ret.put("code", "-200");
            ret.put("description", "该信息不存在");
            return ret;
        }
        ret.put("data", enterService.queryEnterInfo(fruitInfoID));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据id查询分享信息")
    @GetMapping("Info/QueryShareInfoByID/{fruitInfoID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "ID", required = true)
    })
    public Map<String, Object> queryShareInfoByID(@PathVariable String fruitInfoID) {
        Map<String, Object> ret = new HashMap<>();
        CollectInfo collectInfo = collectService.queryCollectInfo(fruitInfoID);
        if (collectInfo == null) {
            ret.put("code", "-200");
            ret.put("description", "该信息不存在");
            return ret;
        }
        ret.put("data", shareService.queryShareInfo(fruitInfoID));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询信息")
    @GetMapping("Info/QueryInfoByPage/{pageNum}/{pageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true),
            @ApiImplicitParam(name = "pageIdx", value = "每页多少个", required = true)

    })
    public Map<String, Object> queryInfoByPage(@PathVariable int pageNum, @PathVariable int pageIdx) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.queryInfosByPage(pageNum, pageIdx));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询信息(第一页)")
    @GetMapping("Info/QueryInfoByFirstPage")
    public Map<String, Object> queryInfoByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.queryInfosByPage(1, 10));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询已存证信息")
    @GetMapping("Info/QueryDocumentedInfoByPage/{pageNum}/{pageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true),
            @ApiImplicitParam(name = "pageIdx", value = "每页多少个", required = true)
    })
    public Map<String, Object> queryDocumentedInfoByPage(@PathVariable int pageNum, @PathVariable int pageIdx) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.queryDocumentedInfosByPage(pageNum, pageIdx));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询已存证信息{第一页}")
    @GetMapping("Info/QueryDocumentedInfoByFirstPage")
    public Map<String, Object> queryDocumentedInfoByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.queryDocumentedInfosByPage(1,10));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询收集信息")
    @GetMapping("Info/QueryCollectInfosByPage/{pageNum}/{pageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true),
            @ApiImplicitParam(name = "pageIdx", value = "每页多少个", required = true)
    })
    public Map<String, Object> queryCollectInfosByPage(@PathVariable int pageNum, @PathVariable int pageIdx) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.queryCollectInfosByPage(pageNum, pageIdx));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询收集信息{第一页}")
    @GetMapping("Info/QueryCollectInfosByFirstPage")
    public Map<String, Object> queryCollectInfosByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.queryCollectInfosByPage(1, 10));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询保存信息")
    @GetMapping("Info/QuerySaveInfosByPage/{pageNum}/{pageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true),
            @ApiImplicitParam(name = "pageIdx", value = "每页多少个", required = true)

    })
    public Map<String, Object> querySaveInfosByPage(@PathVariable int pageNum, @PathVariable int pageIdx) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", saveService.querySaveInfosByPage(pageNum, pageIdx));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询保存信息{第一页}")
    @GetMapping("Info/QuerySaveInfosByFirstPage")
    public Map<String, Object> querySaveInfosByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", saveService.querySaveInfosByPage(1, 10));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询录入信息")
    @GetMapping("Info/QueryEnterInfosByPage/{pageNum}/{pageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true),
            @ApiImplicitParam(name = "pageIdx", value = "每页多少个", required = true)

    })
    public Map<String, Object> queryEnterInfosByPage(@PathVariable int pageNum, @PathVariable int pageIdx) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", enterService.queryEnterInfosByPage(pageNum, pageIdx));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询录入信息{第一页}")
    @GetMapping("Info/QueryEnterInfosByPage")
    public Map<String, Object> queryEnterInfosByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", enterService.queryEnterInfosByPage(1, 10));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询分享信息")
    @GetMapping("Info/QueryShareInfosByPage/{pageNum}/{pageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true),
            @ApiImplicitParam(name = "pageIdx", value = "每页多少个", required = true)

    })
    public Map<String, Object> queryShareInfosByPage(@PathVariable int pageNum, @PathVariable int pageIdx) {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", shareService.queryShareInfosByPage(pageNum, pageIdx));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "根据page查询分享信息{第一页}")
    @GetMapping("Info/QueryShareInfosByFirstPage")
    public Map<String, Object> queryShareInfosByFirstPage() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", shareService.queryShareInfosByPage(1, 10));
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "查寻水果信息数量")
    @GetMapping("Info/fruitCount")
    public Map<String, Object> fruitCount() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", fruitInfoService.fruitCount());
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "查寻采集信息数量")
    @GetMapping("Info/collectCount")
    public Map<String, Object> collectCount() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.collectCount());
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "查寻保存信息数量")
    @GetMapping("Info/saveCount")
    public Map<String, Object> saveCount() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", saveService.saveCount());
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "查寻录入信息数量")
    @GetMapping("Info/enterCount")
    public Map<String, Object> enterCount() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", enterService.enterCount());
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "查寻共享信息数量")
    @GetMapping("Info/shareCount")
    public Map<String, Object> shareCount() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", shareService.shareCount());
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }

    @ApiOperation(value = "查寻存证信息数量")
    @GetMapping("Info/loadCount")
    public Map<String, Object> loadCount() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("data", collectService.queryDocumentedInfosTotal());
        ret.put("code", 200);
        ret.put("description", "查询成功");
        return ret;
    }
}
