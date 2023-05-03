package com.example.foodtrace.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.foodtrace.service.BlockService;
import com.example.foodtrace.service.ChainCodeService;
import com.example.foodtrace.service.FruitInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@CrossOrigin
@RestController
@Api("链码调用相关")
public class ChainCodeController {
    @Autowired
    private ChainCodeService chainCodeService;
    @Autowired
    private FruitInfoService fruitInfoService;
    @Autowired
    private BlockService blockService;

    @ApiOperation(value = "创建水果信息")
    @PostMapping("/CreateFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "采集信息字符串数组", required = true)
    })
    public JSONObject CreateFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                      @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.CreateFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "拒绝创建信息")
    @PostMapping("/RejectCreate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject RejectCreate(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.RejectCreate(fruitInfoID));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "修改创建水果信息")
    @PostMapping("/ModifyCreateFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "采集信息字符串数组", required = true)
    })
    public JSONObject ModifyCreateFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                            @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ModifyCreateFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "保存水果信息")
    @PostMapping("/SaveFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "保存信息字符串数组", required = true)
    })
    public JSONObject SaveFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                    @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.SaveFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "拒绝保存信息")
    @PostMapping("/RejectSave")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject RejectSave(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.RejectSave(fruitInfoID));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "修改保存信息")
    @PostMapping("/ModifySaveFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "保存信息字符串数组", required = true)
    })
    public JSONObject ModifySaveFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                          @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ModifySaveFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "录入水果信息")
    @PostMapping("/EnterFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "录入信息字符串数组", required = true)
    })
    public JSONObject EnterFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                     @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.EnterFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "拒绝录入信息")
    @PostMapping("/RejectEnter")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject RejectEnter(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.RejectEnter(fruitInfoID));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "修改录入信息")
    @PostMapping("/ModifyEnterFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "录入信息字符串数组", required = true)
    })
    public JSONObject ModifyEnterFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                           @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ModifyEnterFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "共享水果信息")
    @PostMapping("/ShareFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "共享信息字符串数组", required = true)
    })
    public JSONObject ShareFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                     @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ShareFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "拒绝共享信息")
    @PostMapping("/RejectShare")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject RejectShare(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.RejectShare(fruitInfoID));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "修改共享信息")
    @PostMapping("/ModifyShareFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true),
            @ApiImplicitParam(name = "args", value = "共享信息字符串数组", required = true)
    })
    public JSONObject ModifyShareFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID,
                                           @RequestParam("args") String[] args) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ModifyShareFruitInfo(fruitInfoID, args));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "信息存证")
    @PostMapping("/LoadFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject LoadFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID) {

        JSONObject ret = new JSONObject();
        try {
            String[] loadConfig = chainCodeService.LoadFruitInfo(fruitInfoID);
            ret.put("data", loadConfig[0]);
            blockService.updateTxByOrg();

            ret.put("code", 200);
            fruitInfoService.loadInfo(fruitInfoID, loadConfig[1]);
        } catch (ContractException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "获得水果信息")
    @GetMapping("/ReadFruitInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject ReadFruitInfo(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ReadFruitInfo(fruitInfoID));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "根据ID范围获得水果信息")
    @GetMapping("/ReadFruitInfoByRange")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoStartID", value = "水果ID1", required = true),
            @ApiImplicitParam(name = "fruitInfoEndID", value = "水果ID2", required = true)
    })
    public JSONObject ReadFruitInfoByRange(@RequestParam("fruitInfoStartID") String fruitInfoStartID,
                                           @RequestParam("fruitInfoEndID") String fruitInfoEndID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ReadFruitInfoByRange(fruitInfoStartID, fruitInfoEndID));
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "获得水果信息历史修改记录")
    @GetMapping("/ReadFruitInfoHistory")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject ReadFruitInfoHistory(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.ReadFruitInfoHistory(fruitInfoID));
            blockService.updateTxByOrg();

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
            blockService.updateTxByOrg();

            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("data", "你无法删除已经存证的信息！");
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "强制删除")
    @DeleteMapping("/DeleteFruitInfoByAdmin")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fruitInfoID", value = "水果ID", required = true)
    })
    public JSONObject DeleteFruitInfoByAdmin(@RequestParam("fruitInfoID") String fruitInfoID) {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", chainCodeService.DeleteFruitInfoByAdmin(fruitInfoID));
            blockService.updateTxByOrg();
            ret.put("code", 200);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }
}


