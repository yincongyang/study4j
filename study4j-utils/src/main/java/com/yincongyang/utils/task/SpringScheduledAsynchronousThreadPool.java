package com.yincongyang.utils.task;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.RateLimiter;

/**
 * spring自带定时任务@scheduled 异步线程池实现
 * @author yincongyang
 *
 */
@Component
public class SpringScheduledAsynchronousThreadPool {
	private static final Logger logger = LoggerFactory.getLogger(SpringScheduledAsynchronousThreadPool.class);

	/**
	 * 线程池
	 */
	private ListeningExecutorService executeThreadPool;

	/**
	 * 线程池
	 */
	private ListeningExecutorService callbackthreadPool;

	/**
	 * 每秒提交任务数
	 */
	private double rateLimit;

	/**
	 * 计数器，用来保证异步线程不同时运行
	 */
	private AtomicInteger syncCount;

	@PostConstruct
	public void init() {
		/*
	  执行线程池大小
	 */
		int threadPoolSize = 10;
		this.rateLimit = 3;
		this.callbackthreadPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(threadPoolSize));
		this.executeThreadPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(threadPoolSize));
		this.syncCount = new AtomicInteger(0);
	}

	@Scheduled(cron = "0/1 * * * * ?")
	public void execute() throws InterruptedException {
		//根据计数器判断上次的任务是否已经结束
		if (this.syncCount.get() > 0) {
			logger.info("上个异步线程池任务未结束，不执行本次任务！");
			return;
		}

		logger.info("异步线程池定时任务开始:{}", Thread.currentThread().getName());
		RateLimiter limiter = RateLimiter.create(this.rateLimit); // 每秒不超过rateLimit个任务被提交

		for (int i = 0; i < 10; i++) {
			limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞

			ListenableFuture<String> listenableFuture = this.executeThreadPool.submit(new Callable<String>() {
				/**
				 * 异步任务执行函数
				 */
				@Override
				public String call() throws Exception {
					//计数器+1
					SpringScheduledAsynchronousThreadPool.this.syncCount.incrementAndGet();
					logger.info("执行函数线程:{}，活跃线程数：{}，计数器:{}",
							Thread.currentThread().getName(),
							Thread.activeCount(),
							SpringScheduledAsynchronousThreadPool.this.syncCount);

					Thread.sleep(10000L);

					//调用代扣接口
					return Thread.currentThread().getName();
				}
			});

			/**
			 * 给异步任务增加回调函数
			 */
			Futures.addCallback(listenableFuture, new FutureCallback<String>() {
				//调用代扣成功返回结果回调函数
				@Override
				public void onSuccess(String res) {
					//计数器-1
					SpringScheduledAsynchronousThreadPool.this.syncCount.decrementAndGet();
					logger.info("成功回调函数:{}，活跃线程数：{}，计数器：{}",
							Thread.currentThread().getName(),
							Thread.activeCount(),
							SpringScheduledAsynchronousThreadPool.this.syncCount.get());
				}

				//调用代扣抛出异常后的回调函数
				@Override
				public void onFailure(Throwable t) {
					//计数器-1
					SpringScheduledAsynchronousThreadPool.this.syncCount.decrementAndGet();
					logger.info("失败回调函数:{}，活跃线程数：{},计数器:{}",
							Thread.currentThread().getName(),
							Thread.activeCount(),
							SpringScheduledAsynchronousThreadPool.this.syncCount.get());
				}

			}, this.callbackthreadPool);
		}

		logger.info("异步线程池定时任务结束:{}", Thread.currentThread().getName());
	}

}
