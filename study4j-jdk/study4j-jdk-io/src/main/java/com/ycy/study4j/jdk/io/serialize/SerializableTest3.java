package com.ycy.study4j.jdk.io.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 以下两种类成员变量是不会被序列化的 (01) 序列化对static和transient变量，是不会自动进行状态保存的。 transient的作用就是，用transient声明的变量，不会被自动序列化。
 * (02) 对于Socket, Thread类，不支持序列化。若实现序列化的接口中，有Thread成员；在对该类进行序列化操作时，编译会出错！
 * 这主要是基于资源分配方面的原因。如果Socket，Thread类可以被序列化，但是被反序列化之后也无法对他们进行重新的资源分配；再者，也是没有必要这样实现。
 * 若需要序列化Thread类，则需要将其声明成static||transient
 *
 * @author yincongyang
 */
public class SerializableTest3 {

  private static final String TMP_FILE = ".serialtest4.txt";

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
      Box1 box = new Box1("desk", 80, 48);
      // 将box对象写入到对象输出流out中，即相当于将对象保存到文件TMP_FILE中
      out.writeObject(box);
      // 打印“Box对象”
      System.out.println("testWrite box: " + box);
      // 修改box的值
      box = new Box1("room", 100, 50);

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
      Box1 box = (Box1) in.readObject();
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
class Box1 implements Serializable {

  private static int width;//不会被序列化，但是static变量是每个对象共用的，所以反序列化时，其值等于当时的实际值。
  private transient int height;//反序列化时，其值为默认值0；不会保存序列化时的状态值
  private String name;

  public Box1(String name, int width, int height) {
    this.name = name;
    this.width = width;
    this.height = height;
  }

  @Override
  public String toString() {
    return "[" + name + ": (" + width + ", " + height + ") ]";
  }
}
