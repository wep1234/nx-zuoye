package com.nx.concurrent.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wep
 * @Since: 2021/7/10 15:21
 */
@Slf4j(topic = "e")
public class TestReentrantLock {

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock(true);
        Thread t1 = new Thread(()->{
            //上锁（获取锁） 改变某个变量的值
           lock.lock();;
                log.debug("xxxxxxxxxxxxxxx");
           lock.unlock();
        },"t1");

        t1.start();

    }
}
