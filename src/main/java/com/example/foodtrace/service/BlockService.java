package com.example.foodtrace.service;

import com.alibaba.fastjson.JSONArray;
import com.example.foodtrace.pojo.MyBlockInfo;
import com.example.foodtrace.pojo.MyNetworkInfo;
import com.example.foodtrace.util.BlockHelper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.codec.binary.Hex;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.BlockchainInfo;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.min;

@Service
public class BlockService {
    @Autowired
    private Channel mychannel;

    public Long ReadBlockHeight() throws InvalidArgumentException, ProposalException {
        BlockchainInfo blockchainInfo = mychannel.queryBlockchainInfo();
        return blockchainInfo.getHeight();
    }

    public MyBlockInfo ReadBlockByNum(Long BlockNum) throws InvalidArgumentException, ProposalException, InvalidProtocolBufferException {
        BlockInfo blockInfo = mychannel.queryBlockByNumber(BlockNum);
        return new MyBlockInfo(blockInfo);
    }

    public ArrayList<MyBlockInfo> ReadBlockByPage(long PageNum) throws InvalidArgumentException, ProposalException, InvalidProtocolBufferException {
        long currentHeight = mychannel.queryBlockchainInfo().getHeight();
        long index0 = (PageNum - 1) * 5;
        long index1 = min(PageNum * 5, currentHeight);
        ArrayList<MyBlockInfo> blockInfos = new ArrayList<>();
        for (long i = index0; i < index1; i++) {
            BlockInfo blockInfo = mychannel.queryBlockByNumber(i);
            blockInfos.add(new MyBlockInfo(blockInfo));
        }
        return blockInfos;
    }

    public List<MyNetworkInfo> ReadNetworkInfoByPage(int PageNum) throws InvalidArgumentException, ProposalException {
        List<MyNetworkInfo> myNetworkInfos = new ArrayList<>();
        Collection<Peer> peers = mychannel.getPeers();
        for (Peer peer : peers) {
            myNetworkInfos.add(new MyNetworkInfo(peer, mychannel));
        }
        Collection<Orderer> orderers = mychannel.getOrderers();
        for (Orderer orderer : orderers) {
            myNetworkInfos.add(new MyNetworkInfo(orderer));
        }
        int index0 = (PageNum - 1) * 5;
        int index1 = min(PageNum * 5, myNetworkInfos.size());
        return myNetworkInfos.subList(index0, index1);
    }
}
