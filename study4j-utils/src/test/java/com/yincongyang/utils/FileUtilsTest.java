package com.yincongyang.utils;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yincongyang.utils.io.FileUtils;

public class FileUtilsTest extends BaseTest {
	private final static Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);

	@Ignore
	@Test
	public void test() {
		long starttime = System.currentTimeMillis();
		FileUtils.batchFileRename("D:\\3.III期TMF(修改后)");
		long endtime = System.currentTimeMillis();
		logger.info("修改文件数量为：{}", FileUtils.count);
		logger.info("共耗时：{}毫秒", new Long(endtime - starttime));
	}

	@Test
	public void testFindLog() {
		FileUtils.findLog();
	}

}
