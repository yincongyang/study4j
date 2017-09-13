package com.yincongyang.base.structure.basetype;

import java.util.Arrays;

import org.junit.Test;

public class ByteType {

	@Test
	public void plusTest() {
		byte a = 127;
		byte b = 12;
		b += a;
		//b = a + b;//保错，byte，char，short运算时会在自动提升至int
		System.out.println(b);
	}

	@Test
	public void test() {
		//前提：在java中，默认都是int型  
		//负数在计算机中用补码表示，转换规则为  
		//十进求二进制负数情况  
		//先求出与该负数相对应正整数二进制代码，然后所有位取反（不管符号位），末尾加1，不够位数时左边补1。  
		//二进制求十进制负数情况  
		//如果首位是1，表明是负整数。将所有位取反（不管符号位），末尾加1，所得数字就是该负数的绝对值。  
		System.out.println((byte) -129); // 结果显示127  
		// 129的二进制 为 1000 0001，因为是-129，根据补码运算规则，得到-129的补码表示 0111 1111，因此计算结果显示为127  
		byte t = -120; //120的二进制为 0111 1000，因为为(-120)，根据补码运算得到1000 1000，这是在计算机内部的表示  
		System.out.println(Integer.toBinaryString(t)); // t转为整形，因为1000 1000为负数，所以需要在前面补充1， 显示结果为：1111111111111111111111110001000  

		int t2 = (byte) 136 & 0xff; //运算步骤，136在java中默认是int类型，为  00000000 00000000 00000000 10001000，强制转为byte后，为1000 1000，  
									//与  0xff 运算后(0xff为int类型)，又变成int类型 00000000 00000000 00000000 10001000  
		System.out.println((byte) t2); // 强制转换后为 1000 1000，因为是负数，根据补码的表示规则为 0111 1000，为120，因为原始为负数，所以结果为-120  
		System.out.println(t2 + " " + Integer.toBinaryString(t2)); // 显示结果为 136 10001000，说明如果是证书，int前面的0不显示   
	}

	private static char[] hex_chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	//byte数组填充0  
	public static void fillWithNUL(byte[] b) {
		for (int i = 0; i < b.length; ++i) {
			b[i] = 0x00; //16进制表示  
		}
	}

	//byte数组逆序  
	public static byte[] reverse(byte[] b) {
		byte[] b1 = new byte[b.length];
		int index = 0;
		for (int i = b.length - 1; i >= 0; --i) {
			b1[index] = b[i];
			++index;
		}
		return b1;
	}

	//两个数组是否相等  
	public static boolean byteArrayEquals(byte[] array1, int array1off, byte[] array2, int array2off, int len) {
		for (int i = 0; i < len; ++i)
			if (array1[array1off + i] != array2[array2off + i])
				return false;
		return true;
	}

	//byte转成int  
	public static int onebyte2int(byte[] b, int off) {
		return (0xFF & b[off]); //0xFF java数字默认是int类型的  
	}

	//int型转为byte  
	public static void int2onebyte(int n, byte[] b, int off) throws Exception {
		if (!(n >= 0 && n <= 255))
			throw new Exception(n + " exceed unsigned char range [0,255]!");
		b[off] = 0x00;
		b[off] |= n;
	}

	//两个byte转成int  
	public static int twobyte2int(byte[] b, int off) {
		int n = 0;
		n |= (0xFF & b[off]) << 8;
		n |= (0xFF & b[off + 1]);
		return n;
	}

	//int转为byte  
	public static void int2twobyte(int n, byte[] b, int off) throws Exception {
		if (!(n >= 0 && n <= 65535))
			throw new Exception(n + " exceed unsigned short int range [0,65535]!");
		b[off] = 0x00;
		b[off + 1] = 0x00;
		b[off] |= (n >> 8);
		b[off + 1] |= n;
	}

	//三组byte转为整形  
	public static int threebyte2int(byte[] b, int off) {
		int n = 0;
		n |= (0xFF & b[off]) << 16;
		n |= (0xFF & b[off + 1]) << 8;
		n |= (0xFF & b[off + 2]);
		return n;
	}

	//int转为三组byte  
	public static void int2threebyte(int n, byte[] b, int off) throws Exception {
		if (!(n >= 0 && n <= 0xFFFFFF))
			throw new Exception(n + " exceed unsigned short int range [0,16777215]!");
		b[off] = 0x00;
		b[off + 1] = 0x00;
		b[off + 2] = 0x00;
		b[off] |= (n >> 16);
		b[off + 1] |= (n >> 8);
		b[off + 2] |= n;
	}

	//4组byte转成long  
	public static long fourbyte2long(byte[] b, int off) {
		long n = 0L;
		n |= (0xFFL & b[off]) << 24;
		n |= (0xFFL & b[off + 1]) << 16;
		n |= (0xFFL & b[off + 2]) << 8;
		n |= (0xFFL & b[off + 3]);
		return n;
	}

	//long转到4个数组中  
	public static void long2fourbyte(long n, byte[] b, int off) throws Exception {
		if (!(n >= 0 && n <= 4294967295L))
			throw new Exception(n + " exceed unsigned long int range [0,4294967295]!");
		b[off] = 0x00;
		b[off + 1] = 0x00;
		b[off + 2] = 0x00;
		b[off + 3] = 0x00;
		b[off] |= (n >> 24);
		b[off + 1] |= (n >> 16);
		b[off + 2] |= (n >> 8);
		b[off + 3] |= n;
	}

	//四组byte转int  
	public static int byte2int(byte[] b) {
		if (b == null || b.length != 4)
			return -1;
		int mask = 0xff;
		int temp = 0;
		int res = 0;
		for (int i = 0; i < 4; i++) {
			res <<= 8; //左移8位，为0不变  
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	//int转为byte数组  
	public static byte[] int2byte(int n) {
		int mask = 0xff;
		byte[] b = new byte[4];
		b[3] = (byte) (n & mask); //强转   
		b[2] = (byte) (n >> 8 & mask);
		b[1] = (byte) (n >> 16 & mask);
		b[0] = (byte) (n >> 24 & mask);
		return b;
	}

	//byte转short  
	public static short byte2short(byte[] b) {
		if (b == null || b.length != 2)
			return -1;
		int mask = 0xff;
		int temp = 0;
		short res = 0;
		for (int i = 0; i < 2; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}

	//short转为byte  
	public static byte[] short2byte(short n) {
		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) (n >> 8 & 0xff);
		return b;
	}

	//数组拷贝  
	public byte[] subArray(byte[] byte_array, int off, int len) {
		byte[] result = new byte[len];
		System.arraycopy(byte_array, off, result, 0, len); //源数组，起始位置，目的数组，起始位置，长度  
		return result;
	}

	//byte数组转为16进制，利用 hex_chars 数组  
	public static String byte2hex(byte[] byte_array, int off, int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = off; i < off + len; ++i) {
			byte temp = byte_array[i];
			sb.append(hex_chars[temp >>> 4 & 0X0F]); //无符号右移，左侧补0  
			sb.append(hex_chars[temp & 0X0F]);
		}
		return sb.toString();
	}

	public static String byte2hex(byte[] byte_array) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byte_array.length; ++i) {
			byte temp = byte_array[i];
			sb.append(hex_chars[temp >>> 4 & 0X0F]);
			sb.append(hex_chars[temp & 0X0F]);
		}
		return sb.toString();
	}

	public static String byte2hexWithBlank(byte[] byte_array) {
		if (byte_array == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < byte_array.length; ++i) {
			byte temp = byte_array[i];
			sb.append(hex_chars[temp >>> 4 & 0X0F]);
			sb.append(hex_chars[temp & 0X0F]);
			sb.append(" ");
		}
		return sb.toString();
	}

	//16进制字符串转byte数组  
	public static byte[] hex2byte(String hexString) throws Exception {
		byte[] hexStrBytes = hexString.getBytes();

		if (hexStrBytes.length % 2 != 0)
			throw new IllegalArgumentException("[" + hexString + "] lengh is even");

		byte[] bytes = new byte[hexStrBytes.length / 2];
		for (int i = 0; i < hexStrBytes.length; i += 2) {
			String item = new String(hexStrBytes, i, 2);
			int2onebyte(Integer.parseInt(item, 16), bytes, i / 2);
		}
		return bytes;
	}

	public static byte[] hex2byteWithBlank(String hexStringWithBlank) throws Exception {
		hexStringWithBlank = hexStringWithBlank.replaceAll(" ", "");
		return hex2byte(hexStringWithBlank);
	}

	//两个数组是否相等  
	public static boolean equals(byte[] b1, byte[] b2) {
		return Arrays.equals(b1, b2);
	}

	//  
	public static String revertStringFromBytes(byte[] ba) {
		int index = 0;
		for (int i = 0; i < ba.length; ++i) {
			if (ba[i] == '\0') {
				index = i;
				break;
			}
		}
		return new String(ba, 0, index); //转为  
	}
}
