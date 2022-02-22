package com.blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashCreator {

    public String createHash(String input) {
        Logger logger = (Logger.getLogger(this.getClass().getName()));
        MessageDigest md;
        byte[] bytes = null;
        try {
            md = MessageDigest.getInstance("SHA3-256");
            bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        StringBuilder buffer = new StringBuilder();
        if (bytes != null) {
            for (byte b : bytes) {
                buffer.append(String.format("%02x", b));
            }
        }
        return buffer.toString();
    }
}
