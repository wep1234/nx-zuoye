package com.nx.concurrent.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wep
 * @Since: 2021/7/10 14:10
 */
@Slf4j(topic = "e")
public class TestLock {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            log.debug("----------------1");
            try{
                TimeUnit.SECONDS.sleep(3);//阻塞=========让cpu放弃这线程的调度
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            log.debug("----------------3");
            lock.unlock();//唤醒t2
        },"t1");

        Thread t2 = new Thread(() -> {
            lock.lock();//t1先持有锁 t2阻塞
            log.debug("----------------2");
            lock.unlock();
        },"t2");

        t1.start();//告诉cpu t1可以调用

        try {
            TimeUnit.MILLISECONDS.sleep(10);//让t1先调度 基本是t1先调度 当前t1线程，主线程，主线程睡眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}
