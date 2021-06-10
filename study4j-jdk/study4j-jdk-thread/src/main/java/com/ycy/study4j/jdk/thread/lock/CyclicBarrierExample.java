package com.ycy.study4j.jdk.thread.lock;

import java.util.concurrent.CyclicBarrier;

/**
 * 新建5个线程，这5个线程达到一定的条件时，它们才继续往后运行。 Created by yincongyang on 17/9/18.
 */

public class CyclicBarrierExample {

  private static CyclicBarrier cb;

  public static void main(String[] args) {

    int SIZE = 5;
    cb = new CyclicBarrier(SIZE);//初始化启动此屏障的线程(参与者)数目

    // 新建5个任务
      for (int i = 0; i < SIZE; i++) {
          new InnerThread().start();
      }
  }

  static class InnerThread extends Thread {

    public void run() {
      try {
        System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");
        System.out.println("当前在屏障处等待的参与者数目" + cb.getNumberWaiting());//当等待屏障启动的数目 = SIZE的时候，所有线程往下执行

        // 将cb的参与者数量加1
        cb.await();

        // cb的参与者数量等于5时，才继续往后执行
        System.out.println(Thread.currentThread().getName() + " continued.");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
