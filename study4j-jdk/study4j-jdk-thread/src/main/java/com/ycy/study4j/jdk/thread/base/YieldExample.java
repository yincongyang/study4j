package com.ycy.study4j.jdk.thread.base;

/**
 * 线程让步 Created by yincongyang on 17/9/15.
 */
public class YieldExample implements Runnable {

  public static void main(String[] args) {
    YieldExample r1 = new YieldExample();

    Thread t1 = new Thread(r1, "t1");
    Thread t2 = new Thread(r1, "t2");
    Thread t3 = new Thread(r1, "t3");

    t1.start();
    t2.start();
    t3.start();
  }

  @Override
  public void run() {
    for (int i = 0; i < 6; i++) {
      System.out.println(Thread.currentThread().getName() + " running " + i);
      if (i % 2 == 0) {
        Thread.yield();//每运行两次让步一次，以便其他线程有机会执行
      }
    }
  }
}
