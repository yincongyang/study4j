package com.ycy.study4j.jdk.io.bytes;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.junit.Test;

/**
 * BufferedInputStream 是缓冲输入流。它继承于FilterInputStream。 BufferedInputStream
 * 的作用是为另一个输入流添加一些功能，例如，提供“缓冲功能”以及支持“mark()标记”和“reset()重置方法”。 BufferedInputStream
 * 本质上是通过一个内部缓冲区数组实现的。例如，在新建某输入流对应的BufferedInputStream后，当我们通过read()读取输入流的数据时，BufferedInputStream会将该输入流的数据分批的填入到缓冲区中。每当缓冲区中的数据被读完之后，输入流会再次填充数据缓冲区；如此反复，直到我们读完输入流数据位置。
 * <p>
 * 因此常用来和FileInputStream搭配使用。
 * <p>
 * ByteArrayInputStream本身具有缓存的功能，且数据一般也来自内存，故一般不需要搭配BufferedInputStream使用。
 *
 * @author yincongyang
 */
public class BufferedInputStreamTest {

  private static final int LEN = 5;

  public static void main(String[] args) {
    testBufferedInputStream();
  }

  /**
   * BufferedInputStream的API测试函数
   */
  @SuppressWarnings("resource")
  private static void testBufferedInputStream() {

    // 创建BufferedInputStream字节流，内容是ArrayLetters数组
    try {
      File file = new File("bufferedinputstream.txt");
      InputStream in = new BufferedInputStream(new FileInputStream(file), 512);

      // 从字节流中读取5个字节。“abcde”，a对应0x61，b对应0x62，依次类推...
      for (int i = 0; i < LEN; i++) {
        // 若能继续读取下一个字节，则读取下一个字节
        if (in.available() >= 0) {
          // 读取“字节流的下一个字节”
          int tmp = in.read();
          System.out.printf("%d : 0x%s\n", i, Integer.toHexString(tmp));
        }
      }

      // 若“该字节流”不支持标记功能，则直接退出
      if (!in.markSupported()) {
        System.out.println("make not supported!");
        return;
      }

      // 标记“当前索引位置”，即标记第6个位置的元素--“f”
      // 1024对应marklimit
      in.mark(1024);

      // 跳过22个字节。
      in.skip(22);

      // 读取5个字节
      byte[] buf = new byte[LEN];
      in.read(buf, 0, LEN);
      // 将buf转换为String字符串。
      String str1 = new String(buf);
      System.out.printf("str1=%s\n", str1);

      // 重置“输入流的索引”为mark()所标记的位置，即重置到“f”处。
      in.reset();
      // 从“重置后的字节流”中读取5个字节到buf中。即读取“fghij”
      in.read(buf, 0, LEN);
      // 将buf转换为String字符串。
      String str2 = new String(buf);
      System.out.printf("str2=%s\n", str2);

      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 适合读取所有类型的文件 读取文件内容：按字节读取
   *
   * @throws IOException
   */
  @Test
  public void readFile() throws IOException {
    String filePath = "key/test.txt";

    URL url = BufferedInputStreamTest.class.getClassLoader().getResource(filePath);   //证书路径
    BufferedInputStream in = new BufferedInputStream(new FileInputStream(url.getFile()));

    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    while (in.available() > 0) {
      buffer.write(in.read());
    }

    System.out.println(buffer.toString());
  }
}
