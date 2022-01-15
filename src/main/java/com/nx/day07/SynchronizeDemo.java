package com.nx.day07;


import java.util.Queue;

/**
 * wait/notify 实现生产者消费者
 */
public class SynchronizeDemo {

    private Queue<Object> queue;
    int maxSize;

    public SynchronizeDemo(Queue<Object> queue,int maxSize){
        this.maxSize = maxSize;
        this.queue = queue;
    }

    public void put(Object e){
        synchronized (this){
            while (queue.size() == maxSize){
                try {
                    System.out.println("队列满了，等待消费");
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            queue.add(e);
            notifyAll();
        }
    }

    public Object take() {
        synchronized (this){
            while (queue.isEmpty()){
                try {
                    System.out.println("队列空了，等待生产");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object o = queue.remove();
            notifyAll();
            return o;
        }
    }
}
