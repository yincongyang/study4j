package com.yincongyang.thread.base;

/**
 * Thread的多线程示例
 * Created by yincongyang on 17/9/14.
 */
public class ThreadExample extends Thread{
    private int ticket=10;//thread内部变量
    //private static int ticket=10;//共享变量
    public void run(){
        synchronized (this){//内部变量不需要共享
            for(int i=0;i<20;i++){
                if(this.ticket>0){
                    System.out.println(this.getName()+" 卖票：ticket"+this.ticket--);
                }
            }
        }
    }

    public static void main(String[] args) {
        // 启动3个线程t1,t2,t3；每个线程各卖10张票！
        ThreadExample t1=new ThreadExample();
        ThreadExample t2=new ThreadExample();
        ThreadExample t3=new ThreadExample();
        t1.start();
        t2.start();
        t3.start();
    }
}
