package com.heyfu.test;

import com.heyfu.practice.io.Hero;
import org.junit.Test;

/**
 * Description:
 * 并发Concurrency
 *
 * @author heyefu
 * Create in: 2018-04-11
 * Time: 20:39
 **/
public class Concurrency {

    @Test
    public void testSynchronized() {
        final Hero gareen = new Hero("gareen", 1000f);
        int threadNum = 500;
        System.out.printf("gareen的初始生命值为:%.8f%n", gareen.getHp());

        Thread[] addThreads = new Thread[threadNum];
        Thread[] reduceThreads = new Thread[threadNum];
//        增加生命的线程
        for (int i = 0; i < threadNum; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    gareen.recover();
                }
            });
            t.start();
            addThreads[i] = t;

            Thread t_down = new Thread(new Runnable() {
                @Override
                public void run() {
                    gareen.hurt();
                }
            });
            t_down.start();
            reduceThreads[i] = t_down;
        }
//        减少生命的线程
//        for (int i = 0; i < threadNum; i++) {
//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    gareen.hurt();
//                }
//            });
//            t.start();
//            reduceThreads[i] = t;
//        }

//        确保所有线程走完
//        for (Thread t : addThreads) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        for (Thread t : reduceThreads){
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        System.out.printf("%d个增加线程和%d个减少线程结束后gareen的生命值为%.8f%n", threadNum, threadNum, gareen.getHp());


    }


}
