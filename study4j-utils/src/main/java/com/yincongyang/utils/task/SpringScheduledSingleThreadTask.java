package com.yincongyang.utils.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring自带定时任务@scheduled单线程实现
 * @author yincongyang
 *
 */
@Component
public class SpringScheduledSingleThreadTask {
	private static final Logger logger = LoggerFactory.getLogger(SpringScheduledSingleThreadTask.class);

	@Scheduled(cron = "0/1 * * * * ?")
	public void executeFileDownLoadTask() {
		Thread current = Thread.currentThread();
		logger.info("定时任务1:" + current.getId() + ",name:" + current.getName());
	}

	@Scheduled(cron = "0/2 * * * * ?")
	public void executeUploadTask() {
		Thread current = Thread.currentThread();
		logger.info("定时任务2:" + current.getId() + ",name:" + current.getName());
	}

	@Scheduled(cron = "0/3 * * * * ?")
	public void executeUploadBackTask() {
		Thread current = Thread.currentThread();
		logger.info("定时任务3:" + current.getId() + ",name:" + current.getName());
	}
}
