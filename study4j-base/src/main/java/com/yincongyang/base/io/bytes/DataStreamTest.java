package com.yincongyang.base.io.bytes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DataInputStream 是数据输入流。它继承于FilterInputStream。
 * DataOutputStream 是数据输出流。它继承于FilterOutputStream。
 * 
 * 他俩是用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型”。
 * 应用程序可以使用DataOutputStream(数据输出流)写入由DataInputStream(数据输入流)读取的数据。
 * @author yincongyang
 *
 */
@SuppressWarnings("unused")
public class DataStreamTest {

	public static void main(String[] args) {
		// 测试DataOutputStream，将数据写入到输出流中。
		testDataOutputStream();
		// 测试DataInputStream，从上面的输出流结果中读取数据。
		testDataInputStream();
	}

	/**
	 * DataOutputStream的API测试函数
	 */
	private static void testDataOutputStream() {

		try {
			File file = new File("file.txt");
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

			out.writeBoolean(true);
			out.writeByte(2);
			out.writeChar('中');
			out.writeShort(677);
			out.writeInt(23456);
			out.writeLong(23456L);

			out.writeUTF("abcdefghijklmnopqrstuvwxyz严12");

			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DataInputStream的API测试函数
	 */
	private static void testDataInputStream() {

		try {
			File file = new File("file.txt");
			DataInputStream in = new DataInputStream(new FileInputStream(file));

			System.out.printf("readBoolean():%s\n", in.readBoolean());
			System.out.printf("readByte():%s\n", in.readByte());
			System.out.printf("readChar():%s\n", in.readChar());
			System.out.printf("readShort():%s\n", in.readShort());
			System.out.printf("readInt():%s\n", in.readInt());
			System.out.printf("readLong():%s\n", in.readLong());
			System.out.printf("readUTF():%s\n", in.readUTF());

			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 打印byte对应的16进制的字符串
	private static String byteToHexString(byte val) {
		return Integer.toHexString(val & 0xff);
	}

	// 打印char对应的16进制的字符串
	private static String charToHexString(char val) {
		return Integer.toHexString(val);
	}

	// 打印short对应的16进制的字符串
	private static String shortToHexString(short val) {
		return Integer.toHexString(val & 0xffff);
	}
}
