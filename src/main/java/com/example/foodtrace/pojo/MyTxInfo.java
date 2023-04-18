package com.example.foodtrace.pojo;

import com.example.foodtrace.util.BlockHelper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.hyperledger.fabric.gateway.Transaction;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.TransactionInfo;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

import java.util.Date;

public class MyTxInfo {
    private String creator;
    private String channelName;
    private String txID;
    private String type;
    private String chainCode;
    private Date timeStamp;
    private String payloadProposalHash;
    private String validationCode;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTxID() {
        return txID;
    }

    public void setTxID(String txID) {
        this.txID = txID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPayloadProposalHash() {
        return payloadProposalHash;
    }

    public void setPayloadProposalHash(String payloadProposalHash) {
        this.payloadProposalHash = payloadProposalHash;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public MyTxInfo(BlockInfo blockInfo, TransactionInfo transactionInfo) throws InvalidProtocolBufferException, InvalidArgumentException, ProposalException {
        this.creator = blockInfo.getEnvelopeInfo(0).getCreator().getMspid();
        this.channelName = blockInfo.getChannelId();
        this.txID = blockInfo.getEnvelopeInfo(0).getTransactionID();
        this.type = "ENDORSER_TRANSACTION";
        this.chainCode = transactionInfo.getEnvelope().getPayload().toStringUtf8().charAt(104) != 'f' ? "_lifestyle" : "foodtrace";
//        this.chainCode = transactionInfo.getEnvelope().getPayload().toStringUtf8().substring(104,114);
        this.validationCode = transactionInfo.getValidationCode().name();
        this.payloadProposalHash = BlockHelper.bytesToHex(BlockHelper.toSHA256ByteArray(transactionInfo.getEnvelope().getPayload().toByteArray()));
        this.timeStamp = blockInfo.getEnvelopeInfo(0).getTimestamp();
    }
}
