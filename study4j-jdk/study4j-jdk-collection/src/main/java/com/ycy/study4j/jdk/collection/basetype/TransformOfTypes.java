package com.ycy.study4j.jdk.collection.basetype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * java基础类型的相互类型转换
 * @author yincongyang
 *
 */
public class TransformOfTypes {
	private static final Logger logger = LoggerFactory.getLogger(TransformOfTypes.class);

	public static final byte BYTE_V = 60;
	public static final char CHAR_V = 65;
	public static final short SHORT_V = 3;
	public static final int INT_V = 4;
	public static final long LONG_V = 5;
	public static final float FLOAT_V = 6.4F;
	public static final double DOUBLE_V = 8.7;

	public static void main(String[] args) {
		/**
		 * 转化方法:自动转换和强制转换
		 * 自动转换:这些类型由"小"到"大"分别为 (byte，short，char)--int--long--float—double
		 * 强制转换:将"大"数据转换为"小"数据时，你可以使用强制类型转换。
		 *        即你必须采用下面这种语句格式： int n=(int)3.14159/2;可以想象，这种转换肯定可能会导致溢出或精度的下降。
		 */
		//①下面的语句可以在Java中直接通过：
		byte b = 12;
		int i = b;
		long l = b;
		float f = b;
		double d = b;
		logger.info("自动转换：byte={}，int={}，long={}，float={}，double={}", b, i, l, f, d);
		//②如果低级类型为char型，向高级类型（整型）转换时，会转换为对应ASCII码值，例如
		char c = 'c';
		int m = c;
		logger.info("自动转换：char={},int={}", c, m);
		//③对于byte,short,char三种类型而言，他们是平级的，因此不能相互自动转换，可以使用下述的强制类型转换。
		short n = 99;
		char v = (char) n;
		logger.info("平级强制转换：short={},char={}", n, v);

		/**
		 * 基本数据类型与String之间的互相转换
		 * 基本类型转为String
		 * ①调用包装类的串转换方法:X.toString();
		 * ②自动转换:X+"";
		 * ③使用String的方法:String.volueOf(X);//推荐
		 */
		logger.info(" ");
		logger.info("byte转String:X.toString()={};X+''={};String.valueOf(X)={}",
				Byte.valueOf(BYTE_V).toString(),
				BYTE_V + "",
				String.valueOf(BYTE_V));
		logger.info("char转String:X.toString()={};X+''={};String.valueOf(X)={}",
				Character.valueOf(CHAR_V).toString(),
				CHAR_V + "",
				String.valueOf(CHAR_V));
		logger.info("int转String:X.toString()={};X+''={};String.valueOf(X)={}",
				Integer.valueOf(INT_V).toString(),
				INT_V + "",
				String.valueOf(INT_V));
		logger.info("long转String:X.toString()={};X+''={};String.valueOf(X)={}",
				Long.valueOf(LONG_V).toString(),
				LONG_V + "",
				String.valueOf(LONG_V));
		logger.info("float转String:X.toString()={};X+''={};String.valueOf(X)={}",
				Float.valueOf(FLOAT_V).toString(),
				FLOAT_V + "",
				String.valueOf(FLOAT_V));
		logger.info("double转String:X.toString()={};X+''={};String.valueOf(X)={}",
				Double.valueOf(DOUBLE_V).toString(),
				DOUBLE_V + "",
				String.valueOf(DOUBLE_V));

		/**
		 * String转为基本数据类型
		 * ①先转换成相应的封装器实例,再调用对应的方法转换成其它类型
		 * 例如，字符中"32.1"转换double型的值的格式为:new Float("32.1").doubleValue()。也可以用:Double.valueOf("32.1").doubleValue()
		 * ②静态parseXXX方法//推荐
		 */
		String str = "65";
		logger.info(" ");
		logger.info("String转byte: 包装类.valueOf().xvalue()={},parsexxx={}",
				Byte.valueOf(str).byteValue(),
				Byte.parseByte(str));
		logger.info("String转int: 包装类.valueOf().xvalue()={},parsexxx={}",
				Integer.valueOf(str).intValue(),
				Integer.parseInt(str));
		logger.info("String转short: 包装类.valueOf().xvalue()={},parsexxx={}",
				Short.valueOf(str).shortValue(),
				Short.parseShort(str));
		logger.info("String转long: 包装类.valueOf().xvalue()={},parsexxx={}",
				Long.valueOf(str).longValue(),
				Long.parseLong(str));
		logger.info("String转float: 包装类.valueOf().xvalue()={},parsexxx={}",
				Float.valueOf(str).floatValue(),
				Float.parseFloat(str));
		logger.info("String转double: 包装类.valueOf().xvalue()={},parsexxx={}",
				Double.valueOf(str).doubleValue(),
				Double.parseDouble(str));
		logger.info("String转char数组: X.toCharArray()={}", str.toCharArray());
	}
}
