package com.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    public static void main(String[] args) {
        List<Block> blockchain = new ArrayList();
        Block newBlock = new Block("I'm a new block!", null);
        blockchain.add(newBlock);
    }
}
