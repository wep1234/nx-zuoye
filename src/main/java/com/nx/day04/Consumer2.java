package com.nx.day04;

import com.nx.day07.SynchronizeDemo;

public class Consumer2 implements Runnable{
    private SynchronizeDemo productBuffer;

    public Consumer2(SynchronizeDemo productBuffer){
        this.productBuffer = productBuffer;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(110);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(productBuffer.take().toString());
        }
    }
}
