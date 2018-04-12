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

    /**
     * Description:
     * 演示同步
     *
     * @author heyefu 21:46 2018/4/11
     **/
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
                    synchronized (gareen) {
                        gareen.recover();
                    }
                }
            });
            t.start();
            addThreads[i] = t;

            Thread t_down = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (gareen) {
                        gareen.hurt();
                    }
                }
            });
            t_down.start();
            reduceThreads[i] = t_down;
        }
//        确保所有线程走完
        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%d个增加线程和%d个减少线程结束后gareen的生命值为%.8f%n", threadNum, threadNum, gareen.getHp());

    }


    /**
     * Description:
     * 演示死锁
     *
     * @author heyefu 21:46 2018/4/11
     **/
    @Test
    public void testDeadLock() {

        Hero fox = new Hero("九尾", 1000f);
        Hero annie = new Hero("安妮", 1000f);

        Thread t_fox = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (fox) {
                    System.out.println("t_fox已经占有九尾");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t_fox试图占有安妮");
                    System.out.println("t_fox等待中.....");
                    synchronized (annie) {
                        System.out.println("t_fox已经占有安妮");
                    }
                }
            }
        });
        t_fox.start();
        Thread t_annie = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (annie) {
                    System.out.println("t_annie已经占有安妮");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t_annie试图占有九尾");
                    System.out.println("t_annie等待中.....");
                    synchronized (fox) {
                        System.out.println("t_annie已经占有九尾");
                    }
                }
            }
        });
        t_annie.start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description:
     * 线程交互wait, notify, notifyAll
     *
     * @author heyefu 22:02 2018/4/11
     **/
    @Test
    public void testInteraction() {

        Hero gareen = new Hero("盖伦", 10);

        Thread t_add = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    gareen.recover();
                    System.out.printf("t_add正在为gareen加血,减少后血量为%f%n", gareen.getHp());
                }
            }
        };
        t_add.start();

        Thread t_reduce = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    if (gareen.getHp() == 1){
//                        continue;
//                    }
                    gareen.hurt();
                    System.out.printf("t_add正在为gareen减血,减少后血量为%f%n", gareen.getHp());
                }
            }
        };
        t_reduce.start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
