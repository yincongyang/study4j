package com.ycy.study4j.jdk.io.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 序列化，就是为了保存对象的状态；而与之对应的反序列化，则可以把保存的对象状态再读出来。 简言之：序列化/反序列化，是Java提供一种专门用于的保存/恢复对象状态的机制。
 * <p>
 * 一般在以下几种情况下，我们可能会用到序列化： a）当你想把的内存中的对象状态保存到一个文件中或者数据库中时候； b）当你想用套接字在网络上传送对象的时候； c）当你想通过RMI传输对象的时候。
 * <p>
 * 我们可以自定义类，让它支持序列化(即实现Serializable接口)，从而能支持对象的保存/恢复
 *
 * @author yincongyang
 */
public class SerializableTest1 {

  private static final String TMP_FILE = ".serialtest1.txt";

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
      // 创建Box对象，Box实现了Serializable序列化接口
      Box box = new Box("desk", 80, 48);
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
      Box box = (Box) in.readObject();
      // 打印“Box对象”
      System.out.println("testRead  box: " + box);
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
 * Box类“支持序列化”。因为Box实现了Serializable接口。
 * <p>
 * 实际上，一个类只需要实现Serializable即可实现序列化，而不需要实现任何函数。
 */
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