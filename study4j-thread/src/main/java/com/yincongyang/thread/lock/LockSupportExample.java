package com.yincongyang.thread.lock;

/**
 *
 * Created by yincongyang on 17/9/18.
 */
import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {

    private static Thread mainThread;

    public static void main(String[] args) throws InterruptedException {

        ThreadA ta = new ThreadA("ta");
        // 获取主线程
        mainThread = Thread.currentThread();

        System.out.println(Thread.currentThread().getName()+" starting");
        ta.start();

//        Thread.sleep(500L);//unpark和park的执行时序可以颠倒

        System.out.println(Thread.currentThread().getName()+" 消费许可（等待许可，阻塞）");
        // 主线程阻塞
        LockSupport.park(mainThread);

        System.out.println(Thread.currentThread().getName()+" continue");
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName()+" 发放许可（唤醒等待许可阻塞的线程）");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
        }
    }
}
