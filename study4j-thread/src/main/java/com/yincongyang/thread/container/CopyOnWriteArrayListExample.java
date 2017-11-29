package com.yincongyang.thread.container;

import java.util.*;
import java.util.concurrent.*;
/*
 *  CopyOnWriteArrayList是“线程安全”的动态数组，而ArrayList是非线程安全的。
 *
 *  下面是“多个线程同时操作并且遍历list”的示例
 *   (01) 当list是CopyOnWriteArrayList对象时，程序能正常运行。
 *   (02) 当list是ArrayList对象时，程序会产生ConcurrentModificationException异常。
 *
 *  Created by yincongyang on 17/9/18.
 */
public class CopyOnWriteArrayListExample {

    // list是ArrayList对象时，程序会出错ConcurrentModificationException。
//    private static List<String> list = new ArrayList<String>();
    private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {

        // 同时启动两个线程对list进行操作！
        new MyThread("ta").start();
        new MyThread("tb").start();
    }

    private static void printAll() {
        String value;
        Iterator iter = list.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }
        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                // “线程名” + "-" + "序号"
                String val = Thread.currentThread().getName()+"-"+i;
                list.add(val);
                // 通过“Iterator”遍历List。
                printAll();
            }
        }
    }
}