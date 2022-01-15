package com.nx.day04;

import java.util.Queue;

public class ProductBuffer {

    private int maxSize;
    private Queue<Product> queue;

    public ProductBuffer(Queue<Product> queue,int maxSize){
        this.maxSize = maxSize;
        this.queue = queue;
    }

    public synchronized void create(){
        while (queue.size() == maxSize){
            try {
               wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(new Product());
        System.out.println(Thread.currentThread().getName()+"当前size"+queue.size());
        notifyAll();
    }

    public synchronized void consumer(){
        while (queue.isEmpty()){
            try {
                System.out.println("队列空了，等待生产");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Product product = queue.remove();
        System.out.println(Thread.currentThread().getName()+"取出："+queue.size());
        notifyAll();
    }
}
