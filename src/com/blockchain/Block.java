package com.blockchain;

import java.util.Date;

public class Block {

    public String data, hash, previousHash;
    public Date timestamp;
    public int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date();
        this.hash = calculateHash();
        this.nonce = 0;
    }

    //For testing purpose only
    public void setData(String data) {
        this.data = data;
    }

    public String mine(int difficulty) {
        String prefix = new String(new char[difficulty]).replace('\0', '0');
        while(!this.hash.substring(0, difficulty).equals(prefix)) {
            this.nonce++;
            this.hash = calculateHash();
        }
        return hash;
    }

    public String calculateHash() {
        HashCreator creator = new HashCreator();
        String dataToHash = previousHash
                + timestamp.toString()
                + nonce
                + data;
        return creator.createHash(dataToHash);
    }
}
