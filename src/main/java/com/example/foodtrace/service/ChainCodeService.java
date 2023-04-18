package com.example.foodtrace.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

@Service
public class ChainCodeService {
    @Autowired
    private Contract foodtraceContract;

    public String CreateFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("CreateFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String RejectCreate(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("RejectCreate")
                .submit(fruitInfoID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String ModifyCreateFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ModifyCreateFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String SaveFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("SaveFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String RejectSave(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("RejectSave")
                .submit(fruitInfoID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String ModifySaveFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ModifySaveFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String EnterFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("EnterFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String RejectEnter(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("RejectEnter")
                .submit(fruitInfoID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String ModifyEnterFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ModifyEnterFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String ShareFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ShareFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String RejectShare(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("RejectShare")
                .submit(fruitInfoID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String ModifyShareFruitInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ModifyShareFruitInfo")
                .submit(fruitInfoID, Arrays.toString(args));
        return new String(ccResult, StandardCharsets.UTF_8);
    }

    public String LoadFruitInfo(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("LoadFruitInfo")
                .submit(fruitInfoID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }
//
//    public String SetCollectInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
//        byte[] ccResult = foodtraceContract.createTransaction("SetCollectInfo")
//                .submit(fruitInfoID, Arrays.toString(args));
//        return new String(ccResult, StandardCharsets.UTF_8);
//    }
//
//    public String SetSpeciesInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
//        byte[] ccResult = foodtraceContract.createTransaction("SetSpeciesInfo")
//                .submit(fruitInfoID, Arrays.toString(args));
//        return new String(ccResult, StandardCharsets.UTF_8);
//    }
//
//    public String SetSourceInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
//        byte[] ccResult = foodtraceContract.createTransaction("SetSourceInfo")
//                .submit(fruitInfoID, Arrays.toString(args));
//        return new String(ccResult, StandardCharsets.UTF_8);
//    }
//
//    public String AddTransportInfo(String fruitInfoID, String[] args) throws ContractException, InterruptedException, TimeoutException {
//        byte[] ccResult = foodtraceContract.createTransaction("AddTransportInfo")
//                .submit(fruitInfoID, Arrays.toString(args));
//        return new String(ccResult, StandardCharsets.UTF_8);
//    }
//
//    public String UpdateProcessID(String fruitInfoID, String processID) throws ContractException, InterruptedException, TimeoutException {
//        byte[] ccResult = foodtraceContract.createTransaction("UpdateProcessID")
//                .submit(fruitInfoID, processID);
//        return new String(ccResult, StandardCharsets.UTF_8);
//    }
//
//    public String UpdateCollectID(String fruitInfoID, String collectID) throws ContractException, InterruptedException, TimeoutException {
//        byte[] ccResult = foodtraceContract.createTransaction("UpdateCollectID")
//                .submit(fruitInfoID, collectID);
//        return new String(ccResult, StandardCharsets.UTF_8);
//    }

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

    public JSONArray ReadFruitInfoHistory(String fruitInfoID) throws ContractException,InterruptedException,TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("ReadHistory")
                .submit(fruitInfoID);
//        System.out.println(new String(ccResult, StandardCharsets.UTF_8));
        return JSON.parseArray(new String(ccResult, StandardCharsets.UTF_8));
    }

    public String DeleteFruitInfo(String fruitInfoID) throws ContractException, InterruptedException, TimeoutException {
        byte[] ccResult = foodtraceContract.createTransaction("DeleteFruitInfo")
                .submit(fruitInfoID);
        return new String(ccResult, StandardCharsets.UTF_8);
    }

}
