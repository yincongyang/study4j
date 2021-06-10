package com.ycy.study4j.jdk.io.chars;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * StringReader\StringWriter 处理字符串IO流
 *
 * @author yincongyang
 */
public class StringReaderWriterTest {

  public static void main(String[] args) {
    StringReaderWriterTest.testReader();
    StringReaderWriterTest.testWriter();
  }

  /**
   * 在内存中缓存读取到的所有字符串，然后使用通过toString方法一次性全部输出字符串。
   */
  public static void testWriter() {
    StringWriter writer = new StringWriter();

    writer.append("heheheh");
    writer.append("hahahaha");
    writer.append("22222222");

    System.out.println(writer.toString());
  }

  public static void testReader() {

    //读取
    try {
      StringReader reader1 = new StringReader("this is a example");
      int a = 0;
      while ((a = reader1.read()) != -1) {
        System.out.print((char) a);
      }

      // 将字符串转换成字符输入流
      StringReader reader2 = new StringReader("这是一个例子");

      // 读取字符输入流中的数据
      char[] buffer = new char[1024];
      int len = 0;
      while ((len = reader2.read(buffer)) != -1) {
        System.out.println(new String(buffer, 0, len));
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
