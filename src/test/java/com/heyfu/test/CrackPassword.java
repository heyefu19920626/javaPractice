package com.heyfu.test;

import java.util.LinkedList;
import java.util.Random;

/**
 * Description:
 * 守护线程setDaemon
 * 向匿名内部类中传值:final 变量, 类变量, 构造函数接收
 * 队列
 *
 * @author heyefu
 * Create in: 2018-04-11
 * Time: 15:41
 **/
public class CrackPassword {


    public static void main(String[] args) {

        Random random = new Random();
        int password = random.nextInt(1000);
        password = 10;
        System.out.println(password);
        final LinkedList container = new LinkedList();

//        破解线程
        Thread crack = new Thread(new Runnable() {
            int password = 0;
            int finalpassword;

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    container.addLast(password);
                    password++;
                    if (password == finalpassword) {
                        System.out.printf("密码是%d %n", password);
                        break;
                    }
                }
            }

            //            构造函数接收外部非final参数
            public Runnable accept(int password) {
                this.finalpassword = password;
                return this;
            }
        }.accept(password));


//        守护线程
        Thread daemon = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (container.isEmpty()) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("可能的密码是:" + container.getFirst());
                        container.removeFirst();
                    }
                }
            }
        });

        crack.start();
//        设置守护线程
        daemon.setDaemon(true);
        daemon.start();
//        container.stream().forEach(i -> System.out.println("container:" + i));


    }


}
