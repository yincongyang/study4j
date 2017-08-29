package com.yincongyang.base.io.chars;

import java.io.CharArrayReader;
import java.io.IOException;

/**
 * CharArrayReader 是字符数组输入流。
 * 它和ByteArrayInputStream类似，只不过ByteArrayInputStream是字节数组输入流，而CharArray是字符数组输入流。
 * CharArrayReader 是用于读取字符数组，它继承于Reader。操作的数据是以字符为单位
 * @author yincongyang
 *
 */
public class CharArrayReaderTest {
	private static final int LEN = 5;
	// 对应英文字母“abcdefghijklmnopqrstuvwxyz”
	private static final char[] ArrayLetters = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) {
		tesCharArrayReader();
	}

	/**
	 * CharArrayReader的API测试函数
	 */
	private static void tesCharArrayReader() {
		try {
			// 创建CharArrayReader字符流，内容是ArrayLetters数组
			CharArrayReader car = new CharArrayReader(ArrayLetters);

			// 从字符数组流中读取5个字符
			for (int i = 0; i < LEN; i++) {
				// 若能继续读取下一个字符，则读取下一个字符
				if (car.ready() == true) {
					// 读取“字符流的下一个字符”
					char tmp = (char) car.read();
					System.out.printf("%d : %c\n", i, tmp);
				}
			}

			// 若“该字符流”不支持标记功能，则直接退出
			if (!car.markSupported()) {
				System.out.println("make not supported!");
				return;
			}

			// 标记“字符流中下一个被读取的位置”。即--标记“f”，因为因为前面已经读取了5个字符，所以下一个被读取的位置是第6个字符”
			// (01), CharArrayReader类的mark(0)函数中的“参数0”是没有实际意义的。
			// (02), mark()与reset()是配套的，reset()会将“字符流中下一个被读取的位置”重置为“mark()中所保存的位置”
			car.mark(0);

			// 跳过5个字符。跳过5个字符后，字符流中下一个被读取的值应该是“k”。
			car.skip(5);

			// 从字符流中读取5个数据。即读取“klmno”
			char[] buf = new char[LEN];
			car.read(buf, 0, LEN);
			System.out.printf("buf=%s\n", String.valueOf(buf));

			// 重置“字符流”：即，将“字符流中下一个被读取的位置”重置到“mark()所标记的位置”，即f。
			car.reset();
			// 从“重置后的字符流”中读取5个字符到buf中。即读取“fghij”
			car.read(buf, 0, LEN);
			System.out.printf("buf=%s\n", String.valueOf(buf));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
