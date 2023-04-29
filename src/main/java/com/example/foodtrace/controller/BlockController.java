package com.example.foodtrace.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.foodtrace.pojo.MyBlockInfo;
import com.example.foodtrace.pojo.MyNetworkInfo;
import com.example.foodtrace.pojo.MyTxInfo;
import com.example.foodtrace.service.BlockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public Map<String, Object> GetBlockInfoByNum(@PathVariable Long BlockNum) throws InvalidArgumentException, ProposalException, IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadBlockByNum(BlockNum));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "根据哈希获取区块")
    @GetMapping("Blockchain/GetBlockInfoByHash/{BlockHash}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "BlockHash", value = "哈希", required = true)
    })
    public Map<String, Object> GetBlockInfoByHash(@PathVariable String BlockHash) throws InvalidArgumentException, ProposalException, IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadBlockByHash(BlockHash));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获取最新三个区块信息")
    @GetMapping("Blockchain/GetNewestBlockInfo")
    public Map<String, Object> GetNewestBlockInfo() throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadNewestBlock());
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "分页获取区块信息")
    @GetMapping("Blockchain/GetBlockInfoByPage/{PageNum}/{PageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "PageIdx", value = "每页多少个", required = true)
    })
    public Map<String, Object> GetBlockInfoByPage(@PathVariable long PageNum, @PathVariable int PageIdx) throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadBlockByPage(PageNum, PageIdx));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获取全部区块信息")
    @GetMapping("Blockchain/GetAllBlockInfo")
    public Map<String, Object> GetAllBlockInfo() throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        List<MyBlockInfo> list = blockService.ReadAllBlock();
        map.put("data", list);
        map.put("total", list.size());
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "分页获取区块信息第一页")
    @GetMapping("Blockchain/GetBlockInfoByFirstPage")
    public Map<String, Object> GetBlockInfoByFirstPage() throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadBlockByPage(1, 5));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获取全部节点名称")
    @GetMapping("Blockchain/GetAllPeerName")
    public Map<String, Object> GetAllPeerName() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadAllPeerName());
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "分页获取节点信息")
    @GetMapping("Blockchain/GetNetInfoByPage/{PageNum}/{PageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "PageIdx", value = "每页多少个", required = true)
    })
    public Map<String, Object> GetNetInfoByPage(@PathVariable int PageNum, @PathVariable int PageIdx) throws InvalidArgumentException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadNetworkInfoByPage(PageNum, PageIdx));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获取全部节点信息")
    @GetMapping("Blockchain/GetAllNetInfos")
    public Map<String, Object> GetAllNetInfos() throws InvalidArgumentException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        List<MyNetworkInfo> list = blockService.ReadAllNetworkInfos();
        map.put("data", list);
        map.put("total", list.size());
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "分页获取节点信息第一页")
    @GetMapping("Blockchain/GetNetInfoByFirstPage")
    public Map<String, Object> GetNetInfoByFirstPage() throws InvalidArgumentException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadNetworkInfoByPage(1,5));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "根据交易号获取交易信息")
    @GetMapping("Blockchain/GetTxInfo/{TxId}")
    public Map<String, Object> GetTxInfoById(@PathVariable String TxId) throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadTxInfoById(TxId));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "分页获取交易信息")
    @GetMapping("Blockchain/GetTxInfoByPage/{PageNum}/{PageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "PageIdx", value = "每页多少个", required = true)
    })
    public Map<String, Object> GetTxInfoByPage(@PathVariable long PageNum, @PathVariable int PageIdx) throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadTxInfoByPage(PageNum, PageIdx));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获取全部交易信息")
    @GetMapping("Blockchain/GetAllTxInfoByPage")
    public Map<String, Object> GetTxInfoByPage() throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        List<MyTxInfo> list = blockService.ReadAllTxInfos();
        map.put("data", list);
        map.put("total", list.size());
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "分页获取交易信息第一页")
    @GetMapping("Blockchain/GetTxInfoByFirstPage")
    public Map<String, Object> GetTxInfoByFirstPage() throws InvalidArgumentException, IOException, ProposalException {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadTxInfoByPage(1,5));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "分页获取链码信息")
    @GetMapping("Blockchain/GetChaincodesInfoByPage/{PageNum}/{PageIdx}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "PageNum", value = "页码", required = true),
            @ApiImplicitParam(name = "PageIdx", value = "每页多少个", required = true)
    })
    public Map<String, Object> GetChaincodesInfoByPage(@PathVariable int PageNum, @PathVariable int PageIdx) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.queryChaincodesByPage(PageNum, PageIdx));
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获得所有部署的链码名称")
    @GetMapping("Blockchain/GetAllChainCodeName")
    public Map<String, Object> GetAllChainCodeName() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.ReadAllBootChainCodeName());
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获得节点数量")
    @GetMapping("Blockchain/GetAllPeers")
    public Map<String, Object> GetAllPeers() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.getPeerNums());
        map.put("code", 200);
        return map;
    }

    @ApiOperation(value = "获得组织交易数")
    @GetMapping("Blockchain/GetTxByOrg")
    public Map<String, Object> GetTxByOrg() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", blockService.readTransactionByOrg());
        map.put("code", 200);
        return map;
    }


}
