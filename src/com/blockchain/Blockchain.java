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

    public boolean isValid() {
        if (this.chain.size() == 1) {
            return true;
        }
        for (int i = 1; i < this.chain.size(); i++) {
            Block current = this.chain.get(i);
            Block previous = this.chain.get(i - 1);
            if (!current.hash.equals(current.calculateHash()) || !previous.hash.equals(current.previousHash)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.create(2);
        blockchain.addBlock("Jorma", "Erkki", "10");
        blockchain.addBlock("Keijo", "Seppo", "200");
        System.out.println(blockchain.isValid());
        System.out.println(blockchain.chain.get(1));
        blockchain.chain.get(1).setData("Tampering the data");
        System.out.println(blockchain.isValid());
        for (Block block : blockchain.chain) {
            System.out.println(block.toString());
        }
    }

}