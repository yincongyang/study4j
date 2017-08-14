package com.yincongyang.base.structure.basetype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基本数据类型常量常见描述
 * @author yincongyang
 *
 */
public class InstanceOfTypes {
	private static final Logger logger = LoggerFactory.getLogger(InstanceOfTypes.class);

	public static final String A;// 常量A
	public static final String B;//常量B
	static {
		A = "ab";
		B = "cd";
	}

	public static final String C = "ab"; // 常量C
	public static final String D = "cd"; //常量D

	public static void main(String[] args) {
		/**
		 * Byte,Short,Integer,Long,Character,Boolean运用了常量池技术
		 * 这5种包装类默认创建了数值[-128，127]的相应类型的缓存数据，但是超出此范围仍然会去创建新的对象。
		 */
		Integer i1 = 40;//等价于Integer.valueOf(40)
		Integer i2 = Integer.valueOf(40);//使用常量池中的对象
		Integer i3 = 129;
		Integer i4 = Integer.valueOf(129);//超出[-128，127]此范围仍然会去创建新的对象
		Integer i5 = new Integer(40);//这种情况下会创建新的对象
		Integer i6 = new Integer(40);
		Integer i7 = new Integer(0);

		Byte b1 = 12;
		Byte b2 = Byte.valueOf((byte) 12);

		Character c1 = 127;
		Character c2 = Character.valueOf((char) 127);
		Character c3 = 129;
		Character c4 = Character.valueOf((char) 129);

		logger.info("Byte,Short,Integer,Long,Character,Boolean运用了常量池技术");
		logger.info("这5种包装类默认创建了数值[-128，127]的相应类型的缓存数据，但是超出此范围仍然会去创建新的对象");
		logger.info("Byte[-128，127]以内的比较结果：{}", b1 == b2);
		logger.info("Integer[-128，127]以内的比较结果：{}", i1 == i2);
		logger.info("Integer[-128，127]以外的比较结果：{}", i3 == i4);
		logger.info("Integer自动拆箱：{}", i1 == i6 + i7);
		logger.info("Integer自动拆箱：{}", 40 == i6 + i7);
		logger.info("Integer通过new关键字的比较结果：{}", i1 == i5);
		logger.info("Character[-128，127]以内的比较结果：{}", c1 == c2);
		logger.info("Character[-128，127]以外的比较结果：{}", c3 == c4);

		/**
		 * 两种浮点数类型的包装类Float,Double并没有实现常量池技术
		 */
		Double d1 = 1.2;
		Double d2 = 1.2;
		logger.info(" ");
		logger.info("两种浮点数类型的包装类Float,Double并没有实现常量池技术");
		logger.info("Double的比较结果：{}", d1 == d2);//输出false

		/**
		 * String类和常量池
		 * 只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入字符串池中
		 * 对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的新对象都不会被加入字符串池中
		 */
		String str1 = "abcd";//在常量池中拿对象
		String str2 = new String("abcd");//直接在堆内存空间创建一个新的对象。只要使用new方法，便需要创建新的对象
		logger.info(" ");
		logger.info("String使用new关键字的区别：{}", str1 == str2);

		String str3 = "str";//常量
		String str4 = "ing";//常量
		String str5 = "str" + "ing";//常量
		String str6 = str3 + str4;//非常量
		String str7 = "string";//常量
		logger.info("使用+号声明字符串的区别：{},{}", str5 == str6, str5 == str7);//false

		/**
		 * A和B虽然被定义为常量，但是它们都没有马上被赋值。在运算出s的值之前，他们何时被赋值，以及被赋予什么样的值，都是个变数。
		 * 因此A和B在被赋值之前，性质类似于一个变量。那么str8就不能在编译期被确定，而只能在运行时被创建了
		 */
		String str8 = InstanceOfTypes.A + InstanceOfTypes.B;//非常量
		logger.info("编译期不能确定的String不是常量：{}", str1 == str8);//false

		/**
		 * A和B都是常量，值是固定的，因此s的值也是固定的，它在类被编译时就已经确定了。
		 * 也就是说：String str9=A+B; 等同于：String s="ab"+"cd";
		 */
		String str9 = InstanceOfTypes.C + InstanceOfTypes.D;//常量
		logger.info("编译期能确定的String是常量：{}", str1 == str9);//false

		/**
		 * java.lang.String.intern()的用法
		 * java运行时也可动态的添加常量进入常量池
		 */
		String s1 = new String("计算机");
		String s2 = s1.intern();
		String s3 = "计算机";
		logger.info(" ");
		logger.info("java.lang.String.intern()运行时常量");
		logger.info("s1 == s2? {}", (s1 == s2));
		logger.info("s3 == s2? {}", (s3 == s2));

	}
}
