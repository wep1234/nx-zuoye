package com.nx.day04;

public class Producer1 implements Runnable{

    private ProductBuffer productBuffer;

    public Producer1(ProductBuffer productBuffer){
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
            productBuffer.create();
        }
    }
}
