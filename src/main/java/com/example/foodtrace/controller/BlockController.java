package com.example.foodtrace.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.foodtrace.service.BlockService;
import com.google.protobuf.InvalidProtocolBufferException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@Api("区块信息相关")
public class BlockController {
    @Autowired
    private BlockService blockService;

    @ApiOperation(value = "获得当前区块高度")
    @GetMapping("Blockchain/GetBlockHeight")
    public JSONObject GetBlockHeight() {
        JSONObject ret = new JSONObject();
        try {
            ret.put("data", blockService.ReadBlockHeight());
            ret.put("code", 200);
        } catch (InvalidArgumentException | ProposalException e) {
            e.printStackTrace();
            ret.put("code", -200);
        }
        return ret;
    }

    @ApiOperation(value = "根据块号获取区块")
    @GetMapping("Blockchain/GetBlockInfoByNum/{BlockNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BlockNum", value = "块号", required = true)
    })
    public Map<String, Object> GetBlockInfoByNum(@PathVariable Long BlockNum) throws InvalidArgumentException, ProposalException, InvalidProtocolBufferException {
        Map<String, Object> map = new HashMap<>();
        map.put("BlockInfo", blockService.ReadBlockByNum(BlockNum));
        return map;
    }

    @ApiOperation(value = "分页获取区块信息")
    @GetMapping("Blockchain/GetBlockInfoByPage/{PageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页码", required = true)
    })
    public Map<String, Object> GetBlockInfoByPage(@PathVariable long PageNum) throws InvalidArgumentException, InvalidProtocolBufferException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("BlockInfoList", blockService.ReadBlockByPage(PageNum));
        return map;
    }

    @ApiOperation(value = "分页获取区块信息第一页")
    @GetMapping("Blockchain/GetBlockInfoByFirstPage")
    public Map<String, Object> GetBlockInfoByFirstPage() throws InvalidArgumentException, InvalidProtocolBufferException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("BlockInfoList", blockService.ReadBlockByPage(1));
        return map;
    }

    @ApiOperation(value = "分页获取节点信息")
    @GetMapping("Blockchain/GetNetInfoByPage/{PageNum}")
    public Map<String, Object> GetNetInfoByPage(@PathVariable int PageNum) throws InvalidArgumentException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("NetInfoList", blockService.ReadNetworkInfoByPage(PageNum));
        return map;
    }

    @ApiOperation(value = "分页获取节点信息第一页")
    @GetMapping("Blockchain/GetNetInfoByFirstPage")
    public Map<String, Object> GetNetInfoByFirstPage() throws InvalidArgumentException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("NetInfoList", blockService.ReadNetworkInfoByPage(1));
        return map;
    }

    @ApiOperation(value = "根据交易号获取交易信息")
    @GetMapping("Blockchain/GetTxInfo/{TxId}")
    public Map<String, Object> GetTxInfoById(@PathVariable String TxId) throws InvalidArgumentException, InvalidProtocolBufferException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("TxInfo", blockService.ReadTxInfoById(TxId));
        return map;
    }

    @ApiOperation(value = "分页获取交易信息")
    @GetMapping("Blockchain/GetTxInfoByPage/{PageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页码", required = true)
    })
    public Map<String, Object> GetTxInfoByPage(@PathVariable long PageNum) throws InvalidArgumentException, InvalidProtocolBufferException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("BlockInfoList", blockService.ReadTxInfoByPage(PageNum));
        return map;
    }

    @ApiOperation(value = "分页获取交易信息第一页")
    @GetMapping("Blockchain/GetTxInfoByFirstPage")
    public Map<String, Object> GetTxInfoByFirstPage() throws InvalidArgumentException, InvalidProtocolBufferException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("BlockInfoList", blockService.ReadTxInfoByPage(1));
        return map;
    }

    @ApiOperation(value = "获得所以部署的链码名称")
    @GetMapping("Blockchain/GetAllChainCodeName")
    public Map<String, Object> GetAllChainCodeName() {
        Map<String, Object> map = new HashMap<>();
        map.put("ChainCodeList", blockService.ReadAllBootChainCodeName());
        return map;
    }
}
