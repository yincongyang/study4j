package com.ycy.study4j.jdk.thread.base;

/**
 * Created by yincongyang on 17/9/14.
 */
public class RunnableExample implements Runnable {

  private int ticket = 30;//共有变量

  public static void main(String[] args) {
    RunnableExample mt = new RunnableExample();//所有基于该runnable创建的线程均共享同一个变量

    // 启动3个线程t1,t2,t3(它们共用一个Runnable对象)，这3个线程一共卖10张票！
    Thread t1 = new Thread(mt, "RunnableExample0");
    Thread t2 = new Thread(mt, "RunnableExample1");
    Thread t3 = new Thread(mt, "RunnableExample2");
    t1.start();
    t2.start();
    t3.start();
  }

  public void run() {
    synchronized (this) {
      for (int i = 0; i < 10; i++) {
        if (this.ticket > 0) {
          System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket--);
        }
      }
    }
  }
}
