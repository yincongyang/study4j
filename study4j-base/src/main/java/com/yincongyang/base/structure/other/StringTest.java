package com.yincongyang.base.structure.other;

import org.junit.Test;

import java.math.BigDecimal;

/**
 *
 * Created by yincongyang on 17/11/30.
 */
public class StringTest {

    /**
     * BigDecimal .00结尾丢失精度问题测试
     */
    @Test
    public void testTransformBigDecimal(){

        //使用string初始化化BigDecimal时，toString时不会丢失精度
        BigDecimal bigDecimal = new BigDecimal("1000.00");

        StringBuilder stringBuilder = new StringBuilder().append(bigDecimal);

        System.out.println(stringBuilder);
    }

}
