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
    private String high;

    private String low;
    private String unsigned;

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

    public PeerType getPeerType() {
        return peerType;
    }

    public void setPeerType(PeerType peerType) {
        this.peerType = peerType;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getUnsigned() {
        return unsigned;
    }

    public void setUnsigned(String unsigned) {
        this.unsigned = unsigned;
    }

    public enum PeerType {
        PEER, ORDERER;
    }

    public MyNetworkInfo(Peer peer, Channel channel) throws InvalidArgumentException, ProposalException {
        this.peerName = peer.getName();
        this.requestUrl = peer.getUrl();
        this.peerType = PeerType.PEER;
        this.MSPId = peer.getProperties().getProperty("org.hyperledger.fabric.sdk.peer.organization_mspid");
        this.low = String.valueOf(channel.queryBlockchainInfo(peer).getHeight());
        this.high = String.valueOf(0);
        this.unsigned = "true";
    }

    public MyNetworkInfo(Orderer orderer) {
        this.peerName = orderer.getName();
        this.requestUrl = orderer.getUrl();
        this.peerType = PeerType.ORDERER;
        this.MSPId = orderer.getProperties().getProperty("org.hyperledger.fabric.sdk.orderer.organization_mspid");
        this.high = "-";
        this.low = "-";
        this.unsigned = "-";
    }
}
