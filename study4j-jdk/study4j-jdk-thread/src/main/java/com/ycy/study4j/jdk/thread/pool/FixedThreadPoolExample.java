package com.ycy.study4j.jdk.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by yincongyang on 17/10/9.
 */
public class FixedThreadPoolExample {

  public static void main(String[] args) {
//        final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
//        final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        final ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newScheduledThreadPool(5);

    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 20, 30l, TimeUnit.SECONDS,
        new ArrayBlockingQueue<Runnable>(10));

    for (int i = 0; i < 15; i++) {
      threadPool.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println("当前线程名称：" + Thread.currentThread().getName()
              + "  主线程大小：" + threadPool.getCorePoolSize()
              + "  最大线程池大小：" + threadPool.getMaximumPoolSize()
              + "  线程池大小：" + threadPool.getPoolSize()
              + "  活跃线程数：" + threadPool.getActiveCount()
              + "  最大线程数：" + threadPool.getLargestPoolSize()
              + "  线程池需要执行的任务数量：" + threadPool.getTaskCount()
              + "  排队等待的线程数量：" + threadPool.getQueue().size()
              + "  完成的任务总数：" + threadPool.getCompletedTaskCount());
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }

    threadPool.shutdown();
  }
}
