package com.heyfu.practice.io;

/**
 * Description:
 * 蒙特卡罗算法
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 14:11
 **/
public class MonteCarlo {


    /**
     * Description:
     * 蒙特卡罗算法求圆周率
     *
     * @param times 计算次数
     * @return double 圆周率
     * @author heyefu 14:35 2018/4/14
     **/
    public static double getPI(int times) {
        double x;
        double y;
        int piTimes = 0;
        times = times < 100 ? 100 : times;
        for (int i = 0; i < times; i++) {
            x = Math.random();
            y = Math.random();
            boolean pi = x * x + y * y > 1;
            if (x * x + y * y > 1) {
                piTimes++;
            }
        }
        return (times - piTimes) / (double) times * 4;
    }


}
