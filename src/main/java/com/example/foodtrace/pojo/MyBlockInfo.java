package com.example.foodtrace.pojo;

import com.example.foodtrace.util.BlockHelper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.hyperledger.fabric.sdk.BlockInfo;

public class MyBlockInfo {
    private Long blockNumber;
    private String channelName;
    private Integer transactionCount;
    private String blockHash;
    private String dataHash;
    private String previousHash;
    private String transactions;

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

    public MyBlockInfo(BlockInfo blockInfo) throws InvalidProtocolBufferException {
        this.blockNumber = blockInfo.getBlockNumber();
        this.channelName = blockInfo.getChannelId();
        this.transactionCount = blockInfo.getTransactionCount();
        this.blockHash = BlockHelper.bytesToHex(BlockHelper.calculateBlockHash(blockInfo.getBlock().toByteArray()));
        this.dataHash = BlockHelper.bytesToHex(blockInfo.getDataHash());
        this.previousHash = BlockHelper.bytesToHex(blockInfo.getPreviousHash());
        this.transactions = blockInfo.getEnvelopeInfo(0).getTransactionID();
    }
}
