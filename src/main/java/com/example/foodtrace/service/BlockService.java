package com.example.foodtrace.service;

import com.example.foodtrace.pojo.MyBlockInfo;
import com.example.foodtrace.pojo.MyNetworkInfo;
import com.example.foodtrace.pojo.MyTxInfo;
import com.example.foodtrace.util.BlockHelper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.BlockchainInfo;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.TransactionInfo;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.Math.min;

@Service
public class BlockService {
    @Autowired
    private Channel mychannel;

    public Long ReadBlockHeight() throws InvalidArgumentException, ProposalException {
        BlockchainInfo blockchainInfo = mychannel.queryBlockchainInfo();
        return blockchainInfo.getHeight();
    }

    public MyBlockInfo ReadBlockByNum(Long BlockNum) throws InvalidArgumentException, ProposalException, IOException {
        BlockInfo blockInfo = mychannel.queryBlockByNumber(BlockNum);
        return new MyBlockInfo(blockInfo);
    }

    public MyBlockInfo ReadBlockByHash(String blockHash) throws InvalidArgumentException, ProposalException, IOException {
        BlockInfo blockInfo = mychannel.queryBlockByHash(BlockHelper.hexStringToByteArray(blockHash));
        return new MyBlockInfo(blockInfo);
    }

    public ArrayList<MyBlockInfo> ReadBlockByPage(long PageNum) throws InvalidArgumentException, ProposalException, IOException {
        long currentHeight = mychannel.queryBlockchainInfo().getHeight();
        long index0 = (PageNum - 1) * 5;
        long index1 = min(PageNum * 5, currentHeight);
        ArrayList<MyBlockInfo> blockInfos = new ArrayList<>();
        for (long i = index0, j = currentHeight - 1; i < index1; i++, j--) {
            BlockInfo blockInfo = mychannel.queryBlockByNumber(j);
            blockInfos.add(new MyBlockInfo(blockInfo));
        }
        return blockInfos;
    }

    public ArrayList<MyBlockInfo> ReadNewestBlock() throws InvalidArgumentException, ProposalException, IOException {
        long currentHeight = mychannel.queryBlockchainInfo().getHeight();
        ArrayList<MyBlockInfo> blockInfos = new ArrayList<>();
        for (long i = currentHeight - 1, j = 0; j < 3; j++, i--) {
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

    public List<String> ReadAllPeerName() {
        List<String> myNetworkInfos = new ArrayList<>();
        Collection<Peer> peers = mychannel.getPeers();
        for (Peer peer : peers) {
            myNetworkInfos.add(peer.getUrl().substring(8));
        }
        Collection<Orderer> orderers = mychannel.getOrderers();
        for (Orderer orderer : orderers) {
            myNetworkInfos.add(orderer.getUrl().substring(8));
        }
        return myNetworkInfos;
    }

    public MyTxInfo ReadTxInfoById(String TxId) throws InvalidArgumentException, ProposalException, IOException {
        BlockInfo blockInfo = mychannel.queryBlockByTransactionID(TxId);
        TransactionInfo transactionInfo = mychannel.queryTransactionByID(TxId);
//        System.out.println(transactionInfo.getEnvelope().getPayload().toStringUtf8().substring(104,114));
//        System.out.println();
//        System.out.println(transactionInfo.getEnvelope().getPayload().toStringUtf8());
//        transactionInfo.getEnvelope().getPayload()
        return new MyTxInfo(blockInfo, transactionInfo);
    }

    public List<MyTxInfo> ReadTxInfoByPage(long PageNum) throws InvalidArgumentException, ProposalException, IOException {
        long currentHeight = mychannel.queryBlockchainInfo().getHeight();
        long index0 = (PageNum - 1) * 5;
        long index1 = min(PageNum * 5, currentHeight);
        ArrayList<MyTxInfo> myTxInfos = new ArrayList<>();
        for (long i = index0, j = currentHeight - 1; i < index1; i++, j--) {
            BlockInfo blockInfo = mychannel.queryBlockByNumber(j);
            TransactionInfo transactionInfo = mychannel.queryTransactionByID(blockInfo.getEnvelopeInfo(0).getTransactionID());
            myTxInfos.add(new MyTxInfo(blockInfo, transactionInfo));
        }
        return myTxInfos;
    }

    public Collection<String> ReadAllBootChainCodeName() {
        return mychannel.getDiscoveredChaincodeNames();
    }


}
