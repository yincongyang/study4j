package com.yincongyang.base.thread;

/**
 * 伪共享
 * 
 * 是由于多个CPU自己的1级缓存或者2级缓存 同时访问同一个缓存行的数据导致的 缓存行失效问题
 * 
 * @author yincongyang
 *
 */
public class FalseShareTest implements Runnable {
	public static int NUM_THREADS = 4;
	public final static long ITERATIONS = 500L * 1000L * 1000L;
	private final int arrayIndex;
	private static VolatileLong[] longs;
	public static long SUM_TIME = 0l;

	public FalseShareTest(final int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	public static void main(final String[] args) throws Exception {
		Thread.sleep(10000);
		for (int j = 0; j < 10; j++) {
			System.out.println(j);
			if (args.length == 1) {
				NUM_THREADS = Integer.parseInt(args[0]);
			}
			longs = new VolatileLong[NUM_THREADS];
			for (int i = 0; i < longs.length; i++) {
				longs[i] = new VolatileLong();
			}
			final long start = System.nanoTime();
			runTest();
			final long end = System.nanoTime();
			SUM_TIME += end - start;
		}
		System.out.println("平均耗时：" + SUM_TIME / 10);
	}

	private static void runTest() throws InterruptedException {
		Thread[] threads = new Thread[NUM_THREADS];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new FalseShareTest(i));
		}
		for (Thread t : threads) {
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
	}

	public void run() {
		long i = ITERATIONS + 1;
		while (0 != --i) {
			longs[arrayIndex].value = i;
		}
	}

	public final static class VolatileLong {
		public volatile long value = 0L;
		//public long p1, p2, p3, p4, p5, p6; //屏蔽此行
	}
}
