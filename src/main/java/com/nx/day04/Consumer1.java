package com.nx.day04;

public class Consumer1 implements Runnable {
    private ProductBuffer productBuffer;

    public Consumer1(ProductBuffer productBuffer){
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
            productBuffer.consumer();
        }
    }
}
