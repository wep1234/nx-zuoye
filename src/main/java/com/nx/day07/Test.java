package com.nx.day07;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock(true);
        try {
            lock.lock();
        }finally {
            lock.unlock();
        }
    }

}
