package com.blockchain;

import java.util.ArrayList;

public class Blockchain {

    public Block genesisBlock;
    public int difficulty;
    public ArrayList<Block> chain;

    public Blockchain(Block genesisBlock, int difficulty) {
        this.chain = new ArrayList<>();
        this.genesisBlock = genesisBlock;
        this.difficulty = difficulty;
    }

    public static Blockchain create(int difficulty) {
        Block genesisBlock = new Block(null, null);
        Blockchain newChain = new Blockchain(genesisBlock, difficulty);
        newChain.chain.add(genesisBlock);
        return newChain;
    }

    public void addBlock(String from, String to, String amount) {
        String blockData = from + ":" + to + ":" + amount;
        Block lastBlock = this.chain.get(this.chain.size() - 1);
        Block newBlock = new Block(blockData, lastBlock.hash);
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
            if (!current.hash.equals(current.calculateHash()) || !previous.hash.equals(current.previousHash)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.create(2);
        blockchain.addBlock("Keijo", "Seppo", "200");
        blockchain.addBlock("Jorma", "Kalevi", "10");
        System.out.println(blockchain.isValid());
        blockchain.chain.get(1).data = "Tampering with data";
        System.out.println(blockchain.isValid());
        for (Block block : blockchain.chain) {
            System.out.println(block.toString());
        }
    }

}