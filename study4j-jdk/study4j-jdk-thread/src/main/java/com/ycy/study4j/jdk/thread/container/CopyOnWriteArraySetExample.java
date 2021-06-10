package com.ycy.study4j.jdk.thread.container;


import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/*
 *   CopyOnWriteArraySet是“线程安全”的集合，而HashSet是非线程安全的。
 *
 *   下面是“多个线程同时操作并且遍历集合set”的示例
 *   (01) 当set是CopyOnWriteArraySet对象时，程序能正常运行。
 *   (02) 当set是HashSet对象时，程序会产生ConcurrentModificationException异常。
 *
 * Created by yincongyang on 17/9/18.
 */
public class CopyOnWriteArraySetExample {

  // TODO: set是HashSet对象时，程序会出错。
  //private static Set<String> set = new HashSet<String>();
  private static final Set<String> set = new CopyOnWriteArraySet<String>();

  public static void main(String[] args) {

    // 同时启动两个线程对set进行操作！
    new MyThread("ta").start();
    new MyThread("tb").start();
  }

  private static void printAll() {
    String value = null;
    Iterator iter = set.iterator();
    while (iter.hasNext()) {
      value = (String) iter.next();
      System.out.print(value + ", ");
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
      while (i++ < 10) {
        // “线程名” + "-" + "序号"
        String val = Thread.currentThread().getName() + "-" + (i % 6);
        set.add(val);
        // 通过“Iterator”遍历set。
        printAll();
      }
    }
  }
}
