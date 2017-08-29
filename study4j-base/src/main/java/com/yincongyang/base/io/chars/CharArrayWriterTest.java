package com.yincongyang.base.io.chars;

import java.io.CharArrayWriter;
import java.io.IOException;

/**
 * CharArrayReader 用于写入数据符，它继承于Writer。操作的数据是以字符为单位！
 * 
 * 其主要有append方法和write方法
 * @author yincongyang
 *
 */
public class CharArrayWriterTest {
	// 对应英文字母“abcdefghijklmnopqrstuvwxyz”
	private static final char[] ArrayLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) {

		tesCharArrayWriter();
	}

	/**
	 * CharArrayWriter的API测试函数
	 */
	private static void tesCharArrayWriter() {
		try {
			// 创建CharArrayWriter字符流
			CharArrayWriter caw = new CharArrayWriter();

			// 写入“A”个字符
			caw.write('A');
			// 写入字符串“BC”个字符
			caw.write("BC");
			//System.out.printf("caw=%s\n", caw);
			// 将ArrayLetters数组中从“3”开始的后5个字符(defgh)写入到caw中。
			caw.write(ArrayLetters, 3, 5);
			//System.out.printf("caw=%s\n", caw);

			// (01) 写入字符0
			// (02) 然后接着写入“123456789”
			// (03) 再接着写入ArrayLetters中第8-12个字符(ijkl)
			caw.append('0').append("123456789").append(String.valueOf(ArrayLetters), 8, 12);

			System.out.printf("caw=%s\n", caw);

			// 计算长度
			int size = caw.size();
			System.out.printf("size=%s\n", size);

			// 转换成byte[]数组
			char[] buf = caw.toCharArray();
			System.out.printf("buf=%s\n", String.valueOf(buf));

			// 将caw写入到另一个输出流中
			CharArrayWriter caw2 = new CharArrayWriter();
			caw.writeTo(caw2);
			System.out.printf("caw2=%s\n", caw2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
