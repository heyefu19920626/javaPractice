package com.heyfu.practice.io;

import java.util.LinkedList;

/**
 * Description:
 * 线程池
 *
 * @author heyefu
 * Create in: 2018-04-12
 * Time: 13:26
 **/
public class ThreadPool {

    /**
     * 线程池大小
     */
    private int threadPoolSize = 10;
    /**
     * 任务列表容器
     */
    private LinkedList<Runnable> tasks = new LinkedList<>();

    public ThreadPool() {
        for (int i = 0; i < threadPoolSize; i++) {
            new TaskConsumeThred().start();
        }
    }

    public void add(Runnable task) {
        synchronized (tasks){
            System.out.println("向线程池添加任务");
            tasks.add(task);
            System.out.println("待办任务个数:" + tasks.size());
            tasks.notifyAll();
        }
    }

    class TaskConsumeThred extends Thread {
        private Runnable task;
        @Override
        public void run() {
            System.out.println("启动:" + this.getName());
            while (true) {
                synchronized (tasks){
                    while (tasks.isEmpty()) {
                        try {
                            System.out.println(this.getName() + "正在等待!");
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeFirst();
                    tasks.notifyAll();
                }
                System.out.println(this.getName() + "获取到任务,开始执行!");
                task.run();
            }
        }
    }


}
