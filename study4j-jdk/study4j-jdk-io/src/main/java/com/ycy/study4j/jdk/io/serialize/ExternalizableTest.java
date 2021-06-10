package com.ycy.study4j.jdk.io.serialize;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * 如果一个类要完全负责自己的序列化，则实现Externalizable接口，而不是Serializable接口。 Externalizable接口定义包括两个方法writeExternal()与readExternal()。
 * 需要注意的是：声明类实现Externalizable接口会有重大的安全风险。writeExternal()与readExternal()方法声明为public，恶意类可以用这些方法读取和写入对象数据。如果对象包含敏感信息，则要格外小心。
 * <p>
 * (01) 实现Externalizable接口的类，不会像实现Serializable接口那样，会自动将数据保存。 (02) 实现Externalizable接口的类，必须实现writeExternal()和readExternal()接口！否则，程序无法正常编译！
 * (03) 实现Externalizable接口的类，必须定义不带参数的构造函数！否则，程序无法正常编译！ (04) writeExternal() 和 readExternal()
 * 的方法都是public的，不是非常安全！
 * <p>
 * 不推荐使用
 *
 * @author yincongyang
 */
public class ExternalizableTest {

  private static final String TMP_FILE = ".externalizabletest1.txt";

  public static void main(String[] args) {
    // 将“对象”通过序列化保存
    testWrite();
    // 将序列化的“对象”读出来
    testRead();
  }

  /**
   * 将Box对象通过序列化，保存到文件中
   */
  private static void testWrite() {
    try {
      // 获取文件TMP_FILE对应的对象输出流。
      // ObjectOutputStream中，只能写入“基本数据”或“支持序列化的对象”
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
      // 创建Box对象
      Box3 box = new Box3("desk", 80, 48);
      // 将box对象写入到对象输出流out中，即相当于将对象保存到文件TMP_FILE中
      out.writeObject(box);
      // 打印“Box对象”
      System.out.println("testWrite box: " + box);

      out.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * 从文件中读取出“序列化的Box对象”
   */
  private static void testRead() {
    try {
      // 获取文件TMP_FILE对应的对象输入流。
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(TMP_FILE));
      // 从对象输入流中，读取先前保存的box对象。
      Box3 box = (Box3) in.readObject();
      // 打印“Box对象”
      System.out.println("testRead  box: " + box);
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
 * Box类实现Externalizable接口
 */
class Box3 implements Externalizable {

  private int width;
  private int height;
  private String name;

  public Box3() {
  }

  public Box3(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  @Override
  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeObject(name);
    out.writeInt(width);
    out.writeInt(height);
  }

  @Override
  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    name = (String) in.readObject();
    width = in.readInt();
    height = in.readInt();
  }

  @Override
  public String toString() {
    return "[" + name + ": (" + width + ", " + height + ") ]";
  }
}
