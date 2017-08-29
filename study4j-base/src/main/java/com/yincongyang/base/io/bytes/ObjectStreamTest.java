package com.yincongyang.base.io.bytes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * - ObjectInputStream 和 ObjectOutputStream 的作用是，对基本数据和对象进行序列化操作支持。
 * 
 * - 创建“文件输出流”对应的ObjectOutputStream对象，该ObjectOutputStream对象能提供对“基本数据或对象”的持久存储；
 * - 当我们需要读取这些存储的“基本数据或对象”时，可以创建“文件输入流”对应的ObjectInputStream，进而读取出这些“基本数据或对象”。
 * - 注意： 只有支持 java.io.Serializable 或 java.io.Externalizable 接口的对象才能被ObjectInputStream/ObjectOutputStream所操作！
 * @author yincongyang
 *
 */
@SuppressWarnings("all")
public class ObjectStreamTest {
	private static final String TMP_FILE = "test.tmp";

	public static void main(String[] args) {
		testWrite();
		testRead();
	}

	/**
	 * ObjectOutputStream 测试函数
	 */
	private static void testWrite() {
		try {
			//装饰器模式
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
			out.writeBoolean(true);
			out.writeByte((byte) 65);
			out.writeChar('a');
			out.writeInt(20131015);
			out.writeFloat(3.14F);
			out.writeDouble(1.414D);
			// 写入HashMap对象
			HashMap map = new HashMap();
			map.put("one", "red");
			map.put("two", "green");
			map.put("three", "blue");
			out.writeObject(map);
			// 写入自定义的Box对象，Box实现了Serializable接口
			Box box = new Box("desk", 80, 48);
			out.writeObject(box);

			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * ObjectInputStream 测试函数
	 */
	private static void testRead() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(TMP_FILE));
			System.out.printf("boolean:%b\n", in.readBoolean());
			System.out.printf("byte:%d\n", (in.readByte() & 0xff));
			System.out.printf("char:%c\n", in.readChar());
			System.out.printf("int:%d\n", in.readInt());
			System.out.printf("float:%f\n", in.readFloat());
			System.out.printf("double:%f\n", in.readDouble());
			// 读取HashMap对象
			HashMap map = (HashMap) in.readObject();
			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				System.out.printf("%-6s -- %s\n", entry.getKey(), entry.getValue());
			}
			// 读取Box对象，Box实现了Serializable接口
			Box box = (Box) in.readObject();
			System.out.println("box: " + box);

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

@SuppressWarnings("all")
class Box implements Serializable {
	private int width;
	private int height;
	private String name;

	public Box(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "[" + name + ": (" + width + ", " + height + ") ]";
	}
}
