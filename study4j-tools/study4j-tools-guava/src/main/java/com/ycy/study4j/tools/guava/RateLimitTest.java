package com.ycy.study4j.tools.guava;


import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * 令牌桶原理，可以用于限流
 *
 * @author yincongyang
 */
@Slf4j
public class RateLimitTest {

  private RateLimiter limiter;

  public RateLimitTest(double limit) {
    this.limiter = RateLimiter.create(limit);
  }

  public static void main(String[] args) throws InterruptedException {
    RateLimitTest test = new RateLimitTest(2);
    RateLimitTest test1 = new RateLimitTest(0.3);
    Thread.sleep(1000);
    for (int i = 0; i < 10; i++) {
      test.test(String.valueOf(i));
    }
  }

  public void test(String i) {
    if (limiter.tryAcquire()) {
      log.info("通过：" + i);
    } else {
      log.info("超出速率" + i);
    }
  }
}
