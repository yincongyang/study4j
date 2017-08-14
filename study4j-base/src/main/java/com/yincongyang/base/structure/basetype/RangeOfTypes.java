package com.yincongyang.base.structure.basetype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基本数据类型的取值范围
 * @author yincongyang
 *
 */
public class RangeOfTypes {
	private static final Logger logger = LoggerFactory.getLogger(RangeOfTypes.class);

	public static void main(String[] args) {
		logger.info("char取值范围：{}~{}", Character.MIN_VALUE, Character.MAX_VALUE);
		logger.info("byte取值范围：{}~{}", Byte.MIN_VALUE, Byte.MAX_VALUE);
		logger.info("short取值范围：{}~{}", Short.MIN_VALUE, Short.MAX_VALUE);
		logger.info("int取值范围：{}~{}", Integer.MIN_VALUE, Integer.MAX_VALUE);
		logger.info("long取值范围：{}~{}", Long.MIN_VALUE, Long.MAX_VALUE);
		logger.info("float取值范围：{}~{}", Float.MIN_VALUE, Float.MAX_VALUE);
		logger.info("double取值范围：{}~{}", Double.MIN_VALUE, Double.MAX_VALUE);
	}
}
