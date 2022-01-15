package com.nx.day04;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者，用于产生数据
 */
public class Producer implements Runnable {
    private final BlockingQueue<Product> queue;

    public Producer(BlockingQueue<Product> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            while (true){
                Thread.sleep(100);
                queue.put(new Product());
                System.out.println(Thread.currentThread().getName()+"当前size:"+queue.size());
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
