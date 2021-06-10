package com.ycy.study4j.jdk.thread.atomic;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * Created by yincongyang on 17/9/15.
 */
public class AtomicLongArrayExample {

  public static void main(String[] args) {

    // 新建AtomicLongArray对象
    long[] arrLong = new long[]{10, 20, 30, 40, 50};
    AtomicLongArray ala = new AtomicLongArray(arrLong);

    ala.set(0, 100);

    System.out.printf("%30s : %s\n", "AtomicLongArray(Long[])", ala.toString());
    System.out
        .printf("%30s : %10s : %s\n", "getAndDecrement(0)", ala.getAndDecrement(0), ala.toString());
    System.out
        .printf("%30s : %10s : %s\n", "decrementAndGet(1)", ala.decrementAndGet(1), ala.toString());
    System.out
        .printf("%30s : %10s : %s\n", "getAndIncrement(2)", ala.getAndIncrement(2), ala.toString());
    System.out
        .printf("%30s : %10s : %s\n", "incrementAndGet(3)", ala.incrementAndGet(3), ala.toString());
    System.out
        .printf("%30s : %10s : %s\n", "addAndGet(100)", ala.addAndGet(0, 100), ala.toString());
    System.out
        .printf("%30s : %10s : %s\n", "getAndAdd(100)", ala.getAndAdd(1, 100), ala.toString());
    System.out.printf("%30s : %10s : %s\n", "compareAndSet()", ala.compareAndSet(2, 31, 1000),
        ala.toString());
    System.out.printf("%30s : %10s : %s\n", "get(2)", ala.get(2), ala.toString());
  }
}

