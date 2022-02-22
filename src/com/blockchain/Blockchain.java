package com.blockchain;

import java.util.ArrayList;

public class Blockchain {

    public static void main(String[] args) {
        ArrayList<Block> blockchain = new ArrayList<>();
        Block newBlock = new Block("I'm a new block!", null);
        blockchain.add(newBlock);
        System.out.println(newBlock.data);
        System.out.println(newBlock.hash);
        System.out.println(newBlock.timestamp);
    }
}
