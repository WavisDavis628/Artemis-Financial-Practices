package com.snhu.sslserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class HashController {

    private static final Logger logger = LoggerFactory.getLogger(HashController.class);

    @GetMapping("/hash")
    public String getHash() {
        String data = "Hello World Check Sum from David Simonelli!";
        try {	
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(data.getBytes());
            return "Data: " + data + "<br>Hash: " + bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error computing hash", e);
            return "Error computing hash: " + e.getMessage();
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
