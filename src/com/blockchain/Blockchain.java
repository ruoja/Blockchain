package com.blockchain;

import java.util.ArrayList;

public class Blockchain {

    private int difficulty;
    private final ArrayList<Block> chain;

    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.difficulty = difficulty;
    }

    public static Blockchain create(int difficulty) {
        Block genesisBlock = new Block(null, null);
        Blockchain newChain = new Blockchain(difficulty);
        newChain.chain.add(genesisBlock);
        return newChain;
    }

    public ArrayList<Block> getChain() {
        return chain;
    }

    public void addBlock(String from, String to, String amount) {
        String blockData = from + ":" + to + ":" + amount;
        Block lastBlock = this.chain.get(this.chain.size() - 1);
        Block newBlock = new Block(blockData, lastBlock.getHash());
        newBlock.mine(this.difficulty);
        this.chain.add(newBlock);
    }

    public boolean isValid() {
        if (this.chain.size() == 1) {
            return true;
        }
        for (int i = 1; i < this.chain.size(); i++) {
            Block current = this.chain.get(i);
            Block previous = this.chain.get(i - 1);
            if (!current.getHash().equals(current.calculateHash()) || !previous.getHash().equals(current.getPreviousHash())) {
                return false;
            }
        }
        return true;
    }
}