package com.example.foodtrace.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.BlockchainInfo;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

@Service
public class ChainCodeService {
    @Autowired
    private Contract foodtraceContract;

    public String CreateFruitInfo(String fruitInfoID, String processID, String collectID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("CreateFruitInfo")
                .submit(fruitInfoID, processID, collectID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String SetCollectInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("SetCollectInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String SetSpeciesInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("SetSpeciesInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String SetSourceInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("SetSourceInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String AddTransportInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("AddTransportInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String UpdateProcessID(String fruitInfoID, String processID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("UpdateProcessID")
                .submit(fruitInfoID, processID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String UpdateCollectID(String fruitInfoID, String collectID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("UpdateCollectID")
                .submit(fruitInfoID, collectID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public JSONObject ReadFruitInfoByRange(String fruitInfoIDStart, String fruitInfoIDEnd) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ReadFruitInfoByRange")
                .submit(fruitInfoIDStart, fruitInfoIDEnd);
        return JSON.parseObject(new String(ccResult, StandardCharsets.UTF_8));
    }

    public JSONObject ReadFruitInfo(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ReadFruitInfo")
                .submit(fruitInfoID);
        String info = new String(ccResult, StandardCharsets.UTF_8);
        return JSON.parseObject(info);
    }

    public JSONObject ReadFruitInfoHistory(String fruitInfoID) throws ContractException,InterruptedException,TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ReadHistory")
                .submit(fruitInfoID);
        return JSON.parseObject(new String(ccResult, StandardCharsets.UTF_8));
    }

    public String DeleteFruitInfo(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("DeleteFruitInfo")
                .submit(fruitInfoID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

}
