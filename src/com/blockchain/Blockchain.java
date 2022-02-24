package com.blockchain;

import java.util.ArrayList;

public class Blockchain {

    public Block genesisBlock;
    public int nonce;
    public ArrayList<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
    }

    public Blockchain(Block genesisBlock, int nonce) {
        this.chain = new ArrayList<>();
        this.genesisBlock = genesisBlock;
        this.nonce = nonce;
    }

    public static Blockchain create(int nonce) {
        Block genesisBlock = new Block(null, null);
        Blockchain newChain = new Blockchain(genesisBlock, nonce);
        newChain.chain.add(genesisBlock);
        return newChain;
    }

    public void addBlock(String from, String to, String amount) {
        String blockData = from + ":" + to + ":" + amount;
        Block lastBlock = this.chain.get(this.chain.size() - 1);
        Block newBlock = new Block(blockData, lastBlock.hash);
        newBlock.mine(this.nonce);
        this.chain.add(newBlock);
    }

    public static void main(String[] args) {
        Blockchain Blockchain = new Blockchain();
        Block newBlock = new Block("I'm a new block!", null);
        Blockchain.chain.add(newBlock);
        System.out.println(newBlock.data);
        System.out.println(newBlock.hash);
        System.out.println(newBlock.timestamp);
    }
}
