package com.ycy.study4j.jdk.io.bytes;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * 接收者线程
 *
 * @author yincongyang
 */
public class PipedInputStreamReceiver extends Thread {

  // 管道输入流对象。
  // 它和“管道输出流(PipedOutputStream)”对象绑定，
  // 从而可以接收“管道输出流”的数据，再让用户读取。
  private final PipedInputStream in = new PipedInputStream();

  // 获得“管道输入流”对象
  public PipedInputStream getInputStream() {
    return in;
  }

  @Override
  public void run() {
    //		readMessageOnce();
    readMessageContinued();
  }

  // 从“管道输入流”中读取1次数据
  public void readMessageOnce() {
    // 虽然buf的大小是2048个字节，但最多只会从“管道输入流”中读取1024个字节。
    // 因为，“管道输入流”的缓冲区大小默认只有1024个字节。
    byte[] buf = new byte[2048];
    try {
      int len = in.read(buf);
      System.out.println(new String(buf, 0, len));
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 从“管道输入流”读取>1024个字节时，就停止读取
  public void readMessageContinued() {
    int total = 0;
    while (true) {
      byte[] buf = new byte[1024];
      try {
        int len = in.read(buf);
        if (len < 0) {//len=-1则没有可读字节
          break;
        }
        total += len;
        System.out.println(new String(buf, 0, len));
        // 若读取的字节总数>1024，则退出循环。
        //				if (total > 1024)
        //					break;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    System.out.println("字节长度：" + total);

    try {
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
