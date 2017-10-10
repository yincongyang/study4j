package com.yincongyang.thread.base;

/**
 * Created by yincongyang on 17/9/15.
 * <p>
 * Object.wait()
 * Object.notify()
 * Object.notifyAll()用法
 */
public class WaitNotifyExample implements Runnable {
    private final String[] lock = {"true"};

    public void notifyLock() {
        synchronized (lock) {//调用Object.notify()方法时，必须持有该对象的锁。
            System.out.println(Thread.currentThread().getName() + " notifyLock");
            System.out.println("Object.notify() 只能唤醒当前对象上单个等待的线程");
            lock.notify();//唤醒lock上的线程
        }
    }

    public void notifyAllLock() {
        synchronized (lock) {//调用Object.notifyAll()方法时，必须持有该对象的锁。
            System.out.println(Thread.currentThread().getName() + " notifyAllLock");
            System.out.println("Object.notifyAllLock() 可以唤醒当前对象上所有等待的线程");
            lock.notifyAll();
        }
    }

    @Override
    public void run() {
        synchronized (lock) {//调用Object.wait()方法时，必须持有该对象的锁。
            //调用Object.wait()时，最好用while而不是if，以便于在notify()后再校验一下条件
            while (lock[0].equalsIgnoreCase("true")) {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting");
                    lock.wait();//lock一直等待
                    lock[0] = "false";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(Thread.currentThread().getName() + " continue");
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyExample runnable1 = new WaitNotifyExample();

        Thread t1 = new Thread(runnable1, "runnable1");
        Thread t2 = new Thread(runnable1, "runnable2");
        Thread t3 = new Thread(runnable1, "runnable3");

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(3000L);
        runnable1.notifyLock();

        Thread.sleep(3000L);
        runnable1.notifyAllLock();
    }
}
