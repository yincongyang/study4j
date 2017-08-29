package com.yincongyang.base.io.bytes;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

@SuppressWarnings("all")
/**  
 * 管道输入流和管道输出流的交互程序
 * 
 * -在java中，PipedOutputStream和PipedInputStream分别是管道输出流和管道输入流。
 * -它们的作用是让多线程可以通过管道进行线程间的通讯。在使用管道通信时，必须将PipedOutputStream和PipedInputStream配套使用。
 * -使用管道通信时，大致的流程是：我们在线程A中向PipedOutputStream中写入数据，这些数据会自动的发送到与PipedOutputStream对应的PipedInputStream中，
 * -进而存储在PipedInputStream的缓冲中；此时，线程B通过读取PipedInputStream中的数据。就可以实现，线程A和线程B的通信。
 * -需要调用connect方法将PipedOutputStream和PipedInputStream关联起来
 */
public class PipedStreamTest {

	public static void main(String[] args) {
		PipedOutputStreamSender t1 = new PipedOutputStreamSender();

		PipedInputStreamReceiver t2 = new PipedInputStreamReceiver();

		PipedOutputStream out = t1.getOutputStream();

		PipedInputStream in = t2.getInputStream();

		try {
			//管道连接。下面2句话的本质是一样。
			//out.connect(in);   
			in.connect(out);

			/**  
			 * Thread类的START方法：  
			 * 使该线程开始执行；Java 虚拟机调用该线程的 run 方法。   
			 * 结果是两个线程并发地运行；当前线程（从调用返回给 start 方法）和另一个线程（执行其 run 方法）。   
			 * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。   
			 */
			t1.start();
			t2.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
