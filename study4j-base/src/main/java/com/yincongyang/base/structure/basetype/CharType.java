package com.yincongyang.base.structure.basetype;

import org.junit.Test;

/**
 * char Character 
 * 
 * char在Java中是16位（2个字节）的，因为Java用的是Unicode。不过8位的ASCII码包含在Unicode中，是从0~127的。
 */
public class CharType {
	@Test
	public void test() {
		char c = '中';//65:A 97:a 20013:中
		byte[] b = CharType.charToByteArray(c);
		byte b1 = CharType.charToByte(c);
		int b2 = CharType.charToInt(c);

		System.out.println("字符为：" + c);
		System.out.println("转化为byte数组：(" + b[0] + "," + b[1] + ")");
		System.out.println("char的长度为" + b.length + "个字节");
		System.out.println("强转为byte：" + b1);
		System.out.println("强转为int：" + b2);
		System.out.println("对应的ASCII码：" + b2);
		System.out.println("-------包装类Character-----");
		System.out.println(Character.isLetter(c));
		System.out.println(Character.isDigit(c));
		System.out.println(Character.isUpperCase(c));
	}

	/**
	 * char转为byte数组
	 * 一个char = 2个byte
	 * @param c
	 * @return
	 */
	public static byte[] charToByteArray(char c) {
		byte[] b = new byte[2];
		b[0] = (byte) ((c & 0xFF00) >> 8);
		b[1] = (byte) (c & 0xFF);
		return b;
	}

	/**
	 * char转为byte 
	 * 若char对应的ASCII码 > 127 强转会丢失精度
	 * @param c
	 * @return
	 */
	public static byte charToByte(char c) {
		return (byte) c;
	}

	/**
	 * char转为int 
	 * 可计算出该字符对应的ASCII码
	 * @param c
	 * @return
	 */
	public static int charToInt(char c) {
		return (int) c;
	}
}
