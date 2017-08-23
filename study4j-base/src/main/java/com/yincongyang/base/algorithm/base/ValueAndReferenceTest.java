package com.yincongyang.base.algorithm.base;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java 字面量存储的是引用还是值测试
 * @author yincongyang
 *
 */
public class ValueAndReferenceTest {
	private static final Logger logger = LoggerFactory.getLogger(ValueAndReferenceTest.class);

	/**
	 * = 号作用：
	 * 对于基本数据类型，赋值运算符会直接改变变量的值，原来的值被覆盖掉。
	 * 对于引用类型（各种对象），赋值运算符会改变引用中所保存的地址，原来的地址被覆盖掉。但是原来的对象不会被改变（重要）
	 */
	@Test
	public void arrayTest() {
		int c = 2;//c存储2
		int d = c;//此时d,c都存储2
		c = 5;//c变更为5，此时d仍然是2
		logger.info("基本数据类型存储的是值：{}", d);

		int[] a = { 1, 2 };
		int[] b = a;//此时a，b存储的引用指向同一个数组
		b[1] = 5;//改变了数组的值，故a={5，2}
		logger.info("数组类型存储的是引用：{}", a[1]);
		b = new int[] { 7, 8 };//此时b指向新的数组，故a仍然是{5，2}
		logger.info("数组类型存储的是引用：{}", a[1]);
	}

	/**
	 * java 方法传参：对于基本类型传的是值，对于对象传的是引用。
	 * 
	 * 故若在函数内，改变参数原来指向的对象的值，则会影响方法外对象
	 */
	@Test
	public void paramTest() {
		int a = 1;
		int[] arr = { 1, 2 };
		StringBuilder str = new StringBuilder("test");

		logger.info("初始化值为：int={}，arr={},str={}", a, arr, str);
		ValueAndReferenceTest.method(a, arr, str);
		logger.info("通过=号赋值不会改变原对象的值，故调用者引用存储的值不会变：int={}，arr={},str={}", a, arr, str);
		ValueAndReferenceTest.method1(arr, str);
		logger.info("改变参数指向对象本身的值，则调用者引用存储的对象跟着改变：arr={},str={}", arr, str);
	}

	private static void method(int a, int[] arr, StringBuilder str) {
		a = 4;
		arr = new int[] { 3, 4 };
		str = new StringBuilder("change");
	}

	private static void method1(int[] arr, StringBuilder str) {
		arr[1] = 5;
		str = str.append("change");
	}
}
