package com.heyfu.test;

import com.heyfu.practice.io.MonteCarlo;
import org.junit.Test;

/**
 * Description:
 *
 * @author heyefu
 * Create in: 2018-04-14
 * Time: 14:21
 **/
public class TestArithmetic {



    @Test
    public void testMonteCarlo(){
        System.out.printf("%.10f", MonteCarlo.getPI(10000));

    }

}
