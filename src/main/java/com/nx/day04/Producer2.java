package com.nx.day04;

import com.nx.day07.SynchronizeDemo;

import java.util.Random;

public class Producer2 implements Runnable{
    private SynchronizeDemo productBuffer;

    public Producer2(SynchronizeDemo productBuffer){
        this.productBuffer = productBuffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final Random random = new Random();
            productBuffer.put(random.nextInt());
        }
    }
}
