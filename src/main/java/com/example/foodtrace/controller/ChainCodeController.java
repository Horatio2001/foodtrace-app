package com.example.foodtrace.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.foodtrace.service.ChainCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@CrossOrigin
@RestController
@Api("链码调用相关")
public class ChainCodeController {
    @Autowired
    private ChainCodeService chainCodeService;

    @ApiOperation(value = "创建水果信息")
    @PostMapping("/createFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "processID", value = "过程ID", required = true),
            @ApiImplicitParam(name = "collectID", value = "采集ID", required = true)
    })
    public JSONObject CreateFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                      @RequestParam("processID") String processID,
                                      @RequestParam("collectID") String collectID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.CreateFruitInfo(fruitInfoID, processID, collectID));
            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "设定采集信息")
    @PostMapping("/setCollectInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "采集信息字符串数组", required = true)
    })
    public JSONObject SetCollectInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                     @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.SetCollectInfo(fruitInfoID, args));
            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "设定物种信息")
    @PostMapping("/setSpeciesInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "物种信息数组", required = true)
    })
    public JSONObject SetSpeciesInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                     @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.SetSpeciesInfo(fruitInfoID, args));
            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "设定来源信息")
    @PostMapping("/setSourceInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "来源信息数组", required = true)
    })
    public JSONObject SetSourceInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                    @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.SetSourceInfo(fruitInfoID, args));
            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "增加物流信息")
    @PostMapping("/addTransportInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "物流信息数组", required = true)
    })
    public JSONObject AddTransportInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                       @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.AddTransportInfo(fruitInfoID, args));
            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "更新过程ID")
    @PostMapping("/updateProcessID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "processID", value = "过程ID", required = true)
    })
    public JSONObject UpdateProcessID(@RequestParam("fruitInfoID") String fruitInfoID,
                                      @RequestParam("processID") String processID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.UpdateProcessID(fruitInfoID, processID));
            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "更新采集ID")
    @PostMapping("/updateCollectID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "collectID", value = "采集ID", required = true)
    })
    public JSONObject UpdateCollectID(@RequestParam("fruitInfoID") String fruitInfoID,
                                      @RequestParam("collectID") String collectID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.UpdateCollectID(fruitInfoID, collectID));
            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "获得水果信息")
    @GetMapping("/readFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject ReadFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ReadFruitInfo(fruitInfoID));
            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "根据ID范围获得水果信息")
    @GetMapping("/readFruitInfoByRange")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoStartID", value = "水果ID1", required = true),
            @ApiImplicitParam(name = "fruitInfoEndID", value = "水果ID2", required = true)
    })
    public JSONObject ReadFruitInfoByRange(@RequestParam("fruitInfoStartID") String fruitInfoStartID,
                                           @RequestParam("fruitInfoEndID") String fruitInfoEndID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ReadFruitInfoByRange(fruitInfoStartID, fruitInfoEndID));
            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "获得水果信息历史修改记录")
    @GetMapping("/readFruitInfoHistory")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject ReadFruitInfoHistory(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ReadFruitInfoHistory(fruitInfoID));
            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "删除水果信息")
    @DeleteMapping("/DeleteFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject DeleteFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.DeleteFruitInfo(fruitInfoID));
            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

}


