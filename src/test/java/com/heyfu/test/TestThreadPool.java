package com.heyfu.test;

import com.heyfu.practice.io.ThreadPool;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * 测试线程池
 *
 * @author heyefu
 * Create in: 2018-04-12
 * Time: 13:36
 **/
public class TestThreadPool {


    public static void main(String[] args) {
//        ThreadPool pool = new ThreadPool();
//        int time = 10;
//        for (int i = 0; i < 100; i++) {
//            Runnable task = new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("任务正在执行!");
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("任务执行完成!");
//                }
//            };
//            pool.add(task);
//            try {
//                Thread.sleep(time);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


//        使用java自带的线程池
//        初始化线程个数, 最大线程个数(初始化的不够用自动增加), 多出来的线程经过keepAliveTime * TimeUnit 还未使用就自动回收
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15, 60, TimeUnit.SECONDS, new LinkedTransferQueue<>());
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试ThreadPoolExecutor!");
            }
        });


    }
}
