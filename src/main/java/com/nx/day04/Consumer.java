package com.nx.day04;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private final BlockingQueue<Product> queue;

    public Consumer(BlockingQueue<Product> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        try {
            while (true){
                Thread.sleep(120);
                queue.take().buy();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
