package com.nx.concurrent.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * 自己模拟锁
 * 自旋来实现同步（局限）
 * 1.什么是锁？
 *  目标：同步 多线程之间一前一后的执行
 *
 *  其实就是一个标识;如果这个标识改变成了某个状态我们就理解为获取锁（让方法正常返回），
 *  拿不到锁其实就是陷入阻塞（死循环） 让这个方法不返回
 * @author: wep
 * @Since: 2021/7/10 14:30
 */
public class CustomLock {

    volatile int state = 0;

    private static Unsafe unsafe = null;

    private static long stateOffset;

    //获取unsafe对象
    static{
        Field singleoneInstanceField = null;
        try{
            singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            unsafe = (Unsafe)singleoneInstanceField.get(null);

            stateOffset = unsafe.objectFieldOffset((com.nx.concurrent.util.CustomLock.class.getDeclaredField("state")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clock() throws InterruptedException {
        while (!compareAndSet(0,1)){//cas 原子操作
            //死循环 耗cpu  用sleep的话，时间不好控制
            TimeUnit.SECONDS.sleep(5);
        }
    }

    //解锁
    public void cunlock(){
        state = 0;
    }


    private boolean compareAndSet(int oldVal, int newVal) {
        return unsafe.compareAndSwapInt(this,stateOffset,oldVal,newVal);
    }
}
