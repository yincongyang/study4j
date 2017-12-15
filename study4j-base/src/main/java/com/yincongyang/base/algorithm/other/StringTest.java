package com.yincongyang.base.algorithm.other;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by yincongyang on 17/11/30.
 */
public class StringTest {

    @Test
    public void test(){

        BigDecimal bigDecimal = new BigDecimal("1000.00");

        StringBuilder stringBuilder = new StringBuilder().append(bigDecimal);

        System.out.println(stringBuilder);
    }

}
