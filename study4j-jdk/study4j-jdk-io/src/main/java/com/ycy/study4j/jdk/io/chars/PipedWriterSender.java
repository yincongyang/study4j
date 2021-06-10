package com.ycy.study4j.jdk.io.chars;

import java.io.IOException;
import java.io.PipedWriter;

@SuppressWarnings("all")
public class PipedWriterSender extends Thread {

  // 管道输出流对象。
  // 它和“管道输入流(PipedReader)”对象绑定，
  // 从而可以将数据发送给“管道输入流”的数据，然后用户可以从“管道输入流”读取数据。
  private PipedWriter out = new PipedWriter();

  // 获得“管道输出流”对象
  public PipedWriter getWriter() {
    return out;
  }

  @Override
  public void run() {
    writeShortMessage();
    //writeLongMessage();
  }

  // 向“管道输出流”中写入一则较简短的消息："this is a short message"
  private void writeShortMessage() {
    String strInfo = "this is a short message";
    try {
      out.write(strInfo.toCharArray());
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 向“管道输出流”中写入一则较长的消息
  private void writeLongMessage() {
    StringBuilder sb = new StringBuilder();
    // 通过for循环写入1020个字符
		for (int i = 0; i < 102; i++) {
			sb.append("0123456789");
		}
    // 再写入26个字符。
    sb.append("abcdefghijklmnopqrstuvwxyz");
    // str的总长度是1020+26=1046个字符
    String str = sb.toString();
    try {
      // 将1046个字符写入到“管道输出流”中
      out.write(str);
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
