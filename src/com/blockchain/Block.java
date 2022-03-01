package com.blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block {

    private final String data;
    private String hash;
    private final String previousHash;
    private final Date timestamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.nonce = 0;
        this.timestamp = new Date();
        this.hash = calculateHash();
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void mine(int difficulty) {
        String prefix = new String(new char[difficulty]).replace('\0', '0');
        while(!this.hash.substring(0, difficulty).equals(prefix)) {
            this.nonce++;
            this.hash = calculateHash();
        }
    }

    public String calculateHash() {
        String dataToHash = previousHash
                + timestamp
                + data
                + nonce;
        Logger logger = (Logger.getLogger(this.getClass().getName()));
        MessageDigest md;
        byte[] bytes = null;
        try {
            md = MessageDigest.getInstance("SHA3-256");
            bytes = md.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
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

    @Override
    public String toString() {
        return "Data: " + data
                + " Timestamp: " + timestamp
                + " Hash: " + hash
                + " Previous hash: " + previousHash;
    }
}
