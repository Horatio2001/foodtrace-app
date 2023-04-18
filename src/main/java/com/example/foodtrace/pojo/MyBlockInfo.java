package com.example.foodtrace.pojo;

import com.example.foodtrace.util.BlockHelper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;

import java.io.IOException;
import java.util.Date;

public class MyBlockInfo {
    private Long blockNumber;
    private String channelName;
    private Integer transactionCount;
    private String blockHash;
    private String dataHash;
    private String previousHash;
    private String transactions;
    private String type;
    private Date time;

    public Long getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Long blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getDataHash() {
        return dataHash;
    }

    public void setDataHash(String dataHash) {
        this.dataHash = dataHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public MyBlockInfo(BlockInfo blockInfo) throws IOException, InvalidArgumentException {
        this.blockNumber = blockInfo.getBlockNumber();
        this.channelName = blockInfo.getChannelId();
        this.transactionCount = blockInfo.getTransactionCount();
        this.blockHash = BlockHelper.bytesToHex(BlockHelper.calculateBlockHash(blockNumber
                , blockInfo.getPreviousHash()
                , blockInfo.getDataHash()
        ));
        this.dataHash = BlockHelper.bytesToHex(blockInfo.getDataHash());
        this.previousHash = BlockHelper.bytesToHex(blockInfo.getPreviousHash());
        this.transactions = blockInfo.getEnvelopeInfo(0).getTransactionID();
        this.type = blockInfo.getType().toString();
        this.time = blockInfo.getEnvelopeInfo(0).getTimestamp();
    }
}
