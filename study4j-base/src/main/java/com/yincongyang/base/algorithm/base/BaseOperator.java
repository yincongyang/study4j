package com.yincongyang.base.algorithm.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础操作符
 * @author yincongyang
 *
 */
public class BaseOperator {
	private static final Logger logger = LoggerFactory.getLogger(BaseOperator.class);

	public static void main(String[] args) {
		int v1 = 1 << 4;
		logger.info("1 << 4 = {}", v1);
		logger.info("1 << 30 = {}", 1 << 30);
		logger.info("1 << 20 = {}", 1 << 20);
		logger.info("3000 % 13 = {}", 3000 % 13);
	}
}
