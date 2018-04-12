package com.heyfu.test;

import com.heyfu.practice.io.ThreadPool;

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
        ThreadPool pool = new ThreadPool();
        int time = 10;
        for (int i = 0; i < 100; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    System.out.println("任务正在执行!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("任务执行完成!");
                }
            };
            pool.add(task);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
