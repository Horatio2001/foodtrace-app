package com.example.foodtrace.service;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BlockServiceTest {
    @Autowired
    private BlockService blockService;
    @Test
    void readNetworkInfoByPage() throws InvalidArgumentException, ProposalException {
    }
}