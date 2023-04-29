package com.example.foodtrace.entity;

public class Chaincodes {
    private String ChaincodeName;
    private String ChannelName;
    private String Path;
    private int TransactionCount;
    private String Version;

    public String getChaincodeName() {
        return ChaincodeName;
    }

    public void setChaincodeName(String chaincodeName) {
        ChaincodeName = chaincodeName;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String channelName) {
        ChannelName = channelName;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public int getTransactionCount() {
        return TransactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        TransactionCount = transactionCount;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
