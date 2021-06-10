package com.ycy.study4j.jdk.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 的简单例子 注：guava的RateLimiter也是类似的原理
 * <p>
 * Created by yincongyang on 17/9/18.
 */


public class SemaphoreExample {

  private static final int SEM_MAX = 10;

  public static void main(String[] args) {
    Semaphore sem = new Semaphore(SEM_MAX);
    //创建线程池
    ExecutorService threadPool = Executors.newFixedThreadPool(3);
    //在线程池中执行任务
    threadPool.execute(new MyThread(sem, 5));
    threadPool.execute(new MyThread(sem, 4));
    threadPool.execute(new MyThread(sem, 7));
    //关闭池
    threadPool.shutdown();
  }
}

class MyThread extends Thread {

  private final Semaphore sem;    // 信号量
  private final int count;        // 申请信号量的大小

  MyThread(Semaphore sem, int count) {
    this.sem = sem;
    this.count = count;
  }

  public void run() {
    try {
      // 从信号量中获取count个许可
      sem.acquire(count);

      Thread.sleep(2000);
      System.out.println(Thread.currentThread().getName() + " acquire count=" + count);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      // 释放给定数目的许可，将其返回到信号量。
      sem.release(count);
      System.out.println(Thread.currentThread().getName() + " release " + count + "");
    }
  }
}