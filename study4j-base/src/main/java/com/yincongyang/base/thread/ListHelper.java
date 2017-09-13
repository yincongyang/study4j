package com.yincongyang.base.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 客户端加锁机制
 * @author yincongyang
 *
 */
public class ListHelper<T> {
	private List<T> list = Collections.synchronizedList(new ArrayList<T>());

	/**
	 * bad 非线程安全，因为此时synchronized持有的锁和需要同步的对象list持有的锁不一样
	 * @param t
	 * @return
	 */
	public synchronized boolean unsafeAddIfAbsent(T t) {
		if (!list.contains(t)) {
			list.add(t);
			return true;
		}
		return false;
	}

	/**
	 * good 线程安全
	 * @param t
	 * @return
	 */
	public boolean safeAddIfAbsent(T t) {
		//客户端加锁
		synchronized (list) {
			if (!list.contains(t)) {//判断和添加是两个动作，需要保证同步
				list.add(t);
				return true;
			}
		}
		return false;
	}
}
