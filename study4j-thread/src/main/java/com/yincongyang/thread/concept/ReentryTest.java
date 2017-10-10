package com.yincongyang.thread.concept;

/**
 * 重入：Synchronized关键字加锁的方法，默认允许同一线程重新获取锁。
 * @author yincongyang
 *
 */
public class ReentryTest {
	public static void main(String[] args) {
		ReentryTest test = new ReentryTest();
		test.outer();
	}

	/**
	 * synchronized加锁是可重入的，即可重复获取自身的锁
	 * outer()和 inner()都声明为 synchronized，这在 Java 中这相当于 synchronized(this)块
	 * 所以在outer()中调用inner()是没问题的
	 */
	public synchronized void outer() {
		this.inner();
	}

	public synchronized void inner() {
		//do something
	}

}

class ReentryTest1 {
	private int count = 0;//该计数器与锁相关联，以实现锁的不可重入性

	public static void main(String[] args) {
		ReentryTest1 test1 = new ReentryTest1();
		try {
			test1.outer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 下列程序通过count和synchronized相关联，实现锁的不可重入性，会造成线程死锁
	 * @throws InterruptedException 
	 */
	public synchronized void outer() throws InterruptedException {
		System.out.println("锁计数器" + (++count));
		this.inner();
		System.out.println("锁计数器" + (--count));
	}

	public synchronized void inner() throws InterruptedException {
		if (count > 0) {//使用程序计数器实现不可重入性
			wait();
		}
		System.out.println("锁计数器" + (++count));
		//		this.outer();
		System.out.println("锁计数器" + (--count));
	}

}
