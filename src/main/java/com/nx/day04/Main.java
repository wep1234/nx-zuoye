package com.nx.day04;

import com.nx.day07.SynchronizeDemo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {
//        BlockingQueue<Product> queue = new ArrayBlockingQueue<Product>(10);
//        Producer p1 = new Producer(queue);
//        Producer p2 = new Producer(queue);
//        Producer p3 = new Producer(queue);
//        Consumer c1 = new Consumer(queue);
//        Consumer c2 = new Consumer(queue);
//
//        Thread producer1 = new Thread(p1);
//        producer1.setName("生产者1");
//        Thread producer2 = new Thread(p2);
//        producer2.setName("生产者2");
//        Thread producer3 = new Thread(p3);
//        producer3.setName("生产者3");
//        Thread consumer1 = new Thread(c1);
//        Thread consumer2 = new Thread(c2);
//        consumer1.setName("消费者1");
//        consumer2.setName("消费者2");

//        producer1.start();
//        producer2.start();
//        producer3.start();
//        consumer1.start();
//        consumer2.start();


//        Queue<Product> queue1 = new LinkedList<>();
//        ProductBuffer productBuffer = new ProductBuffer(queue1,20);
//        Producer1 p11 = new Producer1(productBuffer);
//        Producer1 p12 = new Producer1(productBuffer);
//        Producer1 p13 = new Producer1(productBuffer);
//        Consumer1 c11 = new Consumer1(productBuffer);
//        Consumer1 c12 = new Consumer1(productBuffer);
//
//        Thread producer11 = new Thread(p11);
//        Thread producer12 = new Thread(p12);
//        Thread producer13 = new Thread(p13);
//        Thread consumer11 = new Thread(c11);
//        Thread consumer12 = new Thread(c12);
//        producer11.start();
//        producer12.start();
//        producer13.start();
//        consumer11.start();
//        consumer12.start();
        Queue<Object> queue1 = new LinkedList<>();
        SynchronizeDemo productBuffer = new SynchronizeDemo(queue1,20);
        Producer2 p11 = new Producer2(productBuffer);
        Producer2 p12 = new Producer2(productBuffer);
        Producer2 p13 = new Producer2(productBuffer);
        Consumer2 c11 = new Consumer2(productBuffer);
        Consumer2 c12 = new Consumer2(productBuffer);

        Thread producer11 = new Thread(p11);
        Thread producer12 = new Thread(p12);
        Thread producer13 = new Thread(p13);
        Thread consumer11 = new Thread(c11);
        Thread consumer12 = new Thread(c12);
        producer11.start();
        producer12.start();
        //producer13.start();
        consumer11.start();
        consumer12.start();
    }
}
