package com.nx.day07;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException{
        final long start = System.currentTimeMillis();
        final Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        //创建线程池
        ExecutorService executorService =
                new ThreadPoolExecutor(10,20,10,TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(10),new MyselfRejectHandler());
        for (int i=0;i<100;i++){
            executorService.execute(new MyTaskSelf(i));
        }
        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.DAYS);
        System.out.println(list.size()+"----"+(System.currentTimeMillis()-start));
    }
}


class MyTaskSelf implements Runnable{

    private int i;

    public MyTaskSelf(int i) {
        this.i=i;
    }

    @Override
    public void run() {
        System.out.printf("当前线程：：%s----%d%n", Thread.currentThread().getName(), i);
        try {
            Thread.sleep(1000L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class MyselfRejectHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //线程池中线程数目
        int poolSize = executor.getPoolSize();
        //线程池中最大线程数
        int maximumPoolSize = executor.getMaximumPoolSize();
        if(poolSize >= Math.ceil(maximumPoolSize/2)){
            //占用总线程一半移除最old的两个
            if (!executor.isShutdown()) {
                System.out.println("移除两个");
                executor.getQueue().poll();
                executor.getQueue().poll();
            }
        }
        if(poolSize >= Math.ceil(maximumPoolSize*0.8)){
            //占用总线程80%时丢弃
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    executor.toString());
        }
    }
}