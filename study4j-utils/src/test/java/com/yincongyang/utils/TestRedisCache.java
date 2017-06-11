package com.yincongyang.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.yincongyang.utils.cache.core.CacheManager;
import com.yincongyang.utils.cache.pojo.TestCachePojo;

//SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringJUnit4ClassRunner.class)
//指定我们SpringBoot工程的Application启动类
@SpringBootTest(classes = Application.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//@WebAppConfiguration
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class})
public class TestRedisCache {
	
	@Autowired
	private CacheManager<TestCachePojo> cacheManager;

	@Test
	public void test() throws Exception {
		TestCachePojo pojo = new TestCachePojo("haha");
		cacheManager.setCache("test", pojo);
		cacheManager.setCache("test1", pojo);
		cacheManager.evictCache("test");
		
		TestCachePojo res = cacheManager.getCache("test1");
		
		System.out.println(res.toString());
	}
}
