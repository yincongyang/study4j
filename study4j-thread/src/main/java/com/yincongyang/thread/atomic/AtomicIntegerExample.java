package com.yincongyang.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类操作示例
 * Created by yincongyang on 17/9/15.
 */
public class AtomicIntegerExample {
    public static void main(String[] args){

        AtomicInteger mAtoInt = new AtomicInteger();

        mAtoInt.set(5);

        System.out.printf("%20s : %s\n", "get()", mAtoInt.get());
        System.out.printf("%20s : %s\n", "intValue()", mAtoInt.intValue());
        System.out.printf("%20s : %s\n", "longValue()", mAtoInt.longValue());
        System.out.printf("%20s : %s\n", "doubleValue()", mAtoInt.doubleValue());
        System.out.printf("%20s : %s\n", "floatValue()", mAtoInt.floatValue());

        System.out.printf("%20s : %s\n", "getAndDecrement()", mAtoInt.getAndDecrement());
        System.out.printf("%20s : %s\n", "decrementAndGet()", mAtoInt.decrementAndGet());
        System.out.printf("%20s : %s\n", "getAndIncrement()", mAtoInt.getAndIncrement());
        System.out.printf("%20s : %s\n", "incrementAndGet()", mAtoInt.incrementAndGet());

        System.out.printf("%20s : %s\n", "addAndGet(1)", mAtoInt.addAndGet(1));
        System.out.printf("%20s : %s\n", "getAndAdd(1)", mAtoInt.getAndAdd(1));

        System.out.printf("\n%20s : %s\n", "get()", mAtoInt.get());

        System.out.printf("%20s : %s\n", "compareAndSet()", mAtoInt.compareAndSet(7, 4));
        System.out.printf("%20s : %s\n", "get()", mAtoInt.get());
    }
}
