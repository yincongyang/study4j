package com.ycy.study4j.jdk.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by yincongyang on 17/9/15.
 */
public class AtomicReferenceExample {

  public static void main(String[] args) {

    String p1 = "1";
    String p2 = "2";
    // 新建AtomicReference对象，初始化它的值为p1对象
    AtomicReference ar = new AtomicReference(p1);

    // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
    ar.compareAndSet(p1, p2);

    String p3 = (String) ar.get();
    System.out.println("p3 is " + p3);
    System.out.println("p3.equals(p1)=" + p3.equals(p1));
  }
}
