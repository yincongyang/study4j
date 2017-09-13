package com.yincongyang.base.concept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 运行期类型鉴定（RTTI）
 * 
 * java 类型转换测试
 * - 子类转成父类，完全没有问题，直接转换就行
 * - 父类转成子类，则需要使用强制类型转换，且在运行期会检查内存中对象是否真的是子类型。若不是，则会报ClassCastException。
 * 
 * RTTI的用处
 * - RMI中可用于解析远程对象：反射。
 * 
 * 常用关键字：instanceOf isInstance等
 * @author yincongyang
 *
 */
public class TypeConversionTest {
	private static final Logger logger = LoggerFactory.getLogger(TypeConversionTest.class);

	public static void main(String[] args) {
		String a = new String("c");
		logger.info("创建Object子类String");
		Object b = a;//子类向上转型成功
		logger.info("向上转型成Object成功！");
		String c = (String) b;//强制转换，编译器检查c指向的对象真的是String，则通过
		logger.info("再向下转型强制转换成String成功：{}", c);

		Object d = new Object();
		String e = (String) d;//编译期没有问题，运行时会检查e指向的对象是否真的是String，若不是，则抛ClassCastException异常
		logger.info("将原本是Object的对象强转成其子类String报错：{}", e);
	}
}
