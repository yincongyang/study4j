package com.ycy.study4j.jdk.io.bytes;

/**
 * FilterInputStream 和 FilterOutputStream(流的装饰器)的作用是用来“封装其它的流，并为它们提供额外的功能”。
 * 他俩本身没有提供什么额外的功能，常用的是其子类。
 * <p>
 * FilterInputStream常用的子类有BufferedInputStream和DataInputStream。 (01) BufferedInputStream的作用就是为“输入流提供缓冲功能，以及mark()和reset()功能”。
 * (02) DataInputStream 是用来装饰其它输入流，它“允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型”。
 * 应用程序可以使用DataOutputStream(数据输出流)写入由DataInputStream(数据输入流)读取的数据。
 * <p>
 * FilterOutputStream常用的子类有BufferedOutputStream, DataOutputStream和PrintStream。 (01)
 * BufferedOutputStream的作用就是为“输出流提供缓冲功能”。 (02) DataOutputStream 是用来装饰其它输出流，将DataOutputStream和DataInputStream输入流配合使用，
 * “允许应用程序以与机器无关方式从底层输入流中读写基本 Java 数据类型”。 (03) PrintStream 是用来装饰其它输出流。它能为其他输出流添加了功能，使它们能够方便地打印各种数据值表示形式。
 *
 * @author yincongyang
 */
public class FilterStreamTest {
  //FilterInputStream 和 FilterOutputStream 本身没有提供什么额外的功能。
}
