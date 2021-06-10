package com.ycy.study4j.jdk.io.bytes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * PrintStream 是打印输出流，它继承于FilterOutputStream。 PrintStream 是用来装饰其它输出流。它能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
 * 与其他输出流不同，PrintStream 永远不会抛出 IOException；它产生的IOException会被自身的函数所捕获并设置错误标记， 用户可以通过 checkError()
 * 返回错误标记，从而查看PrintStream内部是否产生了IOException。 另外，PrintStream 提供了自动flush 和
 * 字符集设置功能。所谓自动flush，就是往PrintStream写入的数据会立刻调用flush()函数。
 * <p>
 * 推荐使用PrintStream装饰输出流，简单好用//可完全替代FileOutputStream
 *
 * @author yincongyang
 */
@SuppressWarnings("all")
public class PrintStreamTest {

  public static void main(String[] args) {

    // 下面3个函数的作用都是一样：都是将字母“abcde”写入到文件“file.txt”中。
    // 任选一个执行即可！
    //		testPrintStreamConstrutor1();
    //testPrintStreamConstrutor2() ;
    //testPrintStreamConstrutor3() ;

    // 测试write(), print(), println(), printf()等接口。
    testPrintStreamAPIS();
  }

  /**
   * PrintStream(OutputStream out) 的测试函数
   * <p>
   * 函数的作用，就是将字母“abcde”写入到文件“file.txt”中
   */
  private static void testPrintStreamConstrutor1() {
    // 0x61对应ASCII码的字母'a'，0x62对应ASCII码的字母'b', ...
    final byte[] arr = {0x61, 0x62, 0x63, 0x64, 0x65}; // abced
    try {
      // 创建文件“file.txt”的File对象
      File file = new File("file.txt");
      // 创建文件对应FileOutputStream
      PrintStream out = new PrintStream(new FileOutputStream(file));
      // 将“字节数组arr”全部写入到输出流中
      out.write(arr);
      // 关闭输出流
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * PrintStream(File file) 的测试函数
   * <p>
   * 函数的作用，就是将字母“abcde”写入到文件“file.txt”中
   */
  private static void testPrintStreamConstrutor2() {
    final byte[] arr = {0x61, 0x62, 0x63, 0x64, 0x65};
    try {
      File file = new File("file.txt");
      PrintStream out = new PrintStream(file);
      out.write(arr);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * PrintStream(String fileName) 的测试函数
   * <p>
   * 函数的作用，就是将字母“abcde”写入到文件“file.txt”中
   */
  private static void testPrintStreamConstrutor3() {
    final byte[] arr = {0x61, 0x62, 0x63, 0x64, 0x65};
    try {
      PrintStream out = new PrintStream("classpath:file.txt");
      out.write(arr);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试write(), print(), println(), printf()等接口。
   */
  private static void testPrintStreamAPIS() {
    // 0x61对应ASCII码的字母'a'，0x62对应ASCII码的字母'b', ...
    final byte[] arr = {0x61, 0x62, 0x63, 0x64, 0x65}; // abced
    try {
      // 创建文件对应FileOutputStream
      // PrintStream out = new PrintStream("other.txt");
      PrintStream out = new PrintStream(new FileOutputStream("other.txt", false));

      // 将字符串“hello PrintStream”+回车符，写入到输出流中
      out.println("hello PrintStream");

      // 将字符串"65"写入到输出流中。
      // out.print(0x41); 等价于 out.write(String.valueOf(0x41));
      out.print(0x41);
      // 将字符'B'追加到输出流中
      out.append('B');

      // 将"CDE is 5" + 回车  写入到输出流中
      String str = "CDE";
      int num = 5;
      out.printf("%s is %d\n", str, num);

      // 将0x41写入到输出流中
      // 0x41对应ASCII码的字母'A'，也就是写入字符'A'
      out.write(0x41);

      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
