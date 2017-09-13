package com.yincongyang.guava.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 令牌桶原理，可以用于限流
 * 
 * @author yincongyang
 *
 */
public class RateLimitTest {

	private static Logger logger = LoggerFactory.getLogger(RateLimitTest.class);

	private RateLimiter limiter = null;

	public RateLimitTest(double limit) {
		this.limiter = RateLimiter.create(limit);
	}

	public void test(String i) {
		if (limiter.tryAcquire()) {
			logger.info("通过：" + i);
		} else {
			logger.info("超出速率" + i);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		RateLimitTest test = new RateLimitTest(0.2);
		Thread.sleep(10000);
		for (int i = 0; i < 3; i++) {
			test.test(String.valueOf(i));
		}
	}
}
