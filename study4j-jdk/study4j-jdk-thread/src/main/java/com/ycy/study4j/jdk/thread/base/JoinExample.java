package com.ycy.study4j.jdk.thread.base;

/**
 * Created by yincongyang on 17/9/15.
 */
public class JoinExample implements Runnable {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("主线程开始！");
    JoinExample r1 = new JoinExample();

    Thread t1 = new Thread(r1);
    t1.start();

    t1.join();//等待子线程t1结束后，主线程才会接着往下面执行
    System.out.println("主线程结束！");
  }

  @Override
  public void run() {
    System.out.println("子线程开始！");
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("子线程结束！");

  }
}
