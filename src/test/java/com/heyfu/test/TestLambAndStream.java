package com.heyfu.test;

import org.junit.Test;

import java.util.Arrays;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-10
 * Time: 14:06
 **/
public class TestLambAndStream {


    @Test
    public void testStream() {

        int[] s = {1, 2, 3, 4, 5, 6};
//        数组必须用Arrays.stream()或者Stream.of()转化为管道源
        Arrays.stream(s).filter(i -> i < 3).forEach(i -> System.out.println(i));


    }


}
