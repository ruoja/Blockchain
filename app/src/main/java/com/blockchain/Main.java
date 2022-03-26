package com.blockchain;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.create(2);
        blockchain.addBlock("Keijo", "Seppo", "200");
        blockchain.addBlock("Jorma", "Kalevi", "10");
        System.out.println(blockchain.isValid());
        ArrayList<Block> chain = blockchain.getChain();
        for (Block block : chain) {
            System.out.println(block.toString());
        }
    }
}
