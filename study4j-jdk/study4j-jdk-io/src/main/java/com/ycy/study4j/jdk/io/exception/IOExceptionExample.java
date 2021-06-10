package com.ycy.study4j.jdk.io.exception;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 处理IO异常模板：不会漏掉IO过程中任何异常的捕获
 * <p>
 * 但是代码过于繁琐，推荐使用通用工具类库使用IO
 *
 * @author yincongyang
 */
public class IOExceptionExample {

  /**
   * 逻辑清晰版
   *
   * @throws Exception
   */
  public static void example1() throws Exception {
    InputStream input = null;
    IOException processException = null;
    try {
      input = new FileInputStream("text.txt");
      //...process input stream...
    } catch (IOException e) {
      processException = e;
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          if (processException != null) {
            //处理异常信息
            throw new Exception(processException);
          } else {
            //处理异常信息
            throw new Exception(e);
          }
        }
      }
      if (processException != null) {
        //处理异常信息
        throw new Exception(processException);
      }
    }
  }

  /**
   * 简洁版
   *
   * @throws Exception
   */
  public static void example2() throws Exception {
    InputStream input = null;
    try {
      input = new FileInputStream("text.txt");
    } catch (IOException e) {
      //处理异常信息
      e.printStackTrace();
    } finally {
      try {
				if (input != null) {
					input.close();
				}
      } catch (IOException e) {
        //处理异常信息
        e.printStackTrace();
      }
    }
  }
}
