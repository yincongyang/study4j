package com.ycy.study4j.jdk.collection.basetype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基本数据类型的默认初始化
 * @author yincongyang
 *
 */
public class InitOfTypes {
	private static final Logger logger = LoggerFactory.getLogger(InitOfTypes.class);

	public static char char_value;
	public static byte byte_value;
	public static short short_value;
	public static int int_value;
	public static long long_value;
	public static float float_value;
	public static double double_value;
	public static boolean boolean_value;

	public static void main(String[] args) {
		logger.info("char作为类成员变量默认初始化为：{}", InitOfTypes.char_value);
		logger.info("byte作为类成员变量默认初始化为：{}", InitOfTypes.byte_value);
		logger.info("short作为类成员变量默认初始化为：{}", InitOfTypes.short_value);
		logger.info("int作为类成员变量默认初始化为：{}", InitOfTypes.int_value);
		logger.info("long作为类成员变量默认初始化为：{}", InitOfTypes.long_value);
		logger.info("float作为类成员变量默认初始化为：{}", InitOfTypes.float_value);
		logger.info("double作为类成员变量默认初始化为：{}", InitOfTypes.double_value);
		logger.info("boolean作为类成员变量默认初始化为：{}", InitOfTypes.boolean_value);
	}
}
