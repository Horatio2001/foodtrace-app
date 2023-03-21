package com.example.foodtrace.pojo;

import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.springframework.beans.factory.annotation.Autowired;

public class MyNetworkInfo {
    private String peerName;
    private String requestUrl;
    private PeerType peerType;
    private String MSPId;
    private Long high;

    public String getPeerName() {
        return peerName;
    }

    public void setPeerName(String peerName) {
        this.peerName = peerName;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMSPId() {
        return MSPId;
    }

    public void setMSPId(String MSPId) {
        this.MSPId = MSPId;
    }

    public Long getHigh() {
        return high;
    }

    public void setHigh(Long high) {
        this.high = high;
    }

    public PeerType getPeerType() {
        return peerType;
    }

    public void setPeerType(PeerType peerType) {
        this.peerType = peerType;
    }

    public enum PeerType {
        PEER, ORDERER;
    }

    public MyNetworkInfo(Peer peer, Channel channel) throws InvalidArgumentException, ProposalException {
        this.peerName = peer.getName();
        this.requestUrl = peer.getUrl();
        this.peerType = PeerType.PEER;
        this.MSPId = peer.getProperties().getProperty("org.hyperledger.fabric.sdk.peer.organization_mspid");
        this.high = channel.queryBlockchainInfo(peer).getHeight();

    }

    public MyNetworkInfo(Orderer orderer) {
        this.peerName = orderer.getName();
        this.requestUrl = orderer.getUrl();
        this.peerType = PeerType.ORDERER;
        this.MSPId = orderer.getProperties().getProperty("org.hyperledger.fabric.sdk.orderer.organization_mspid");

    }
}
