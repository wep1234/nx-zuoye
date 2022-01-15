package com.nx.concurrent.sync;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author: wep
 * @Since: 2021/7/26 19:49
 */
@Slf4j(topic = "e")
public class TestSync {

    public static void main(String[] args) throws InterruptedException {
        //不可偏向

        //跳过偏向延迟时间，默认4s
        TimeUnit.SECONDS.sleep(5);

        L l = new L();
//        log.debug(Integer.toHexString(l.hashCode()));
        log.debug(ClassLayout.parseInstance(l).toPrintable(l));

//        synchronized (l){
//            log.debug(ClassLayout.parseInstance(l).toPrintable(l));
//        }

//        new Thread(()->{
//            //为什么这个地方叫做锁对象==改变对象
//            //这里所谓的锁住l对象，那么究竟锁住的是什么
//            //锁的并不是对象的属性，因为一个对象不仅仅只有属性还有对象头和对齐填充
//            synchronized (l){
//                log.debug("locked0");
//                log.debug("locked1");
//            }
//        }).start();
    }
}
