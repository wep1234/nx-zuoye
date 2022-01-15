package com.nx.concurrent.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: wep
 * @Since: 2021/7/10 15:12
 * park 和 unpark的基本使用
 *  可以即时唤醒（线程交互）
 */
@Slf4j(topic = "e")
public class TestPark {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            log.debug("---------------1");
            LockSupport.park();;//阻塞自己
            log.debug("---------------unparked--3");
        },"t1");


        Thread t2 = new Thread(()->{
            log.debug("---------------2");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(t1);
        },"t2");

        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
