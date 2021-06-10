package com.ycy.study4j.jdk.io.bytes;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @param args SequenceInputStream合并流，将与之相连接的流集组合成一个输入流并从第一个输入流开始读取，直到到达文件末尾，
 *             接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。 合并流的作用是将多个源合并合一个源。
 *             可接收枚举类(Enumeration)所封闭的多个字节流对象。
 */
public class SequenceInputStreamTest {

  public static void main(String[] args) {
    doSequence();
  }

  private static void doSequence() {
    //创建一个合并流的对象
    SequenceInputStream sis = null;
    //创建输出流。
    BufferedOutputStream bos = null;
    try {
      //构建流集合。
      Vector<InputStream> vector = new Vector<InputStream>();
      vector.addElement(new FileInputStream("D:\\text1.txt"));
      vector.addElement(new FileInputStream("D:\\text2.txt"));
      vector.addElement(new FileInputStream("D:\\text3.txt"));

      //Vector的枚举类
      Enumeration<InputStream> e = vector.elements();

      sis = new SequenceInputStream(e);

      bos = new BufferedOutputStream(new FileOutputStream("D:\\text4.txt"));
      //读写数据
      byte[] buf = new byte[1024];
      int len = 0;
      while ((len = sis.read(buf)) != -1) {
        bos.write(buf, 0, len);
        bos.flush();
      }
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    } finally {
      //关闭输入输出流
      try {
				if (sis != null) {
					sis.close();
				}
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
				if (bos != null) {
					bos.close();
				}
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
