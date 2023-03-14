package com.example.foodtrace.config;

import org.hyperledger.fabric.gateway.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class FabricConfig {
    @Bean
    public Contract makeContract() throws IOException {
        Path walletDirectory= new ClassPathResource("wallets/org0.foodtrace.com").getFile().toPath();
        Wallet wallet = Wallets.newFileSystemWallet(walletDirectory);
        InputStream configInputStream=new ClassPathResource("mychannel_connection_for_javasdk.yaml").getInputStream();
        Gateway.Builder builder = Gateway.createBuilder()
                .identity(wallet, "Admin")
                .networkConfig(configInputStream)
                .discovery(false);
        Gateway gateway=builder.connect();
        Network network=gateway.getNetwork("mychannel");
        return network.getContract("message");
    }
}

