package com.example.foodtrace.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BlockHelper {
    public static byte[] calculateBlockHash(byte[] blockBytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(blockBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to calculate block hash: " + e.getMessage(), e);
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
