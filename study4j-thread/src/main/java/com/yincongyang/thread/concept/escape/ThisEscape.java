package com.yincongyang.thread.concept.escape;

import com.yincongyang.thread.annotation.UnThreadSafe;

/**
 * 对象逸出
 * - 下面举例了this逸出的场景
 * - 其它引起this逸出的常用场景是：在构造函数里面启动一个线程。
 * @author yincongyang
 *
 */
@UnThreadSafe
public class ThisEscape {
	//
	public ThisEscape(EventSource source) {
		source.registerListener(new EventListener() {
			public void onEvent(Event e) {
				ThisEscape.this.dosomething(e);
			}

		});
		// initAction 初始化到这里的时候，有可能Listener事件已经被触发，
		//此时会在ThisEscapeTest未初始化完成的情况下，就调用了其方法。就造成了this逸出
	}

	public void dosomething(Event e) {
		//this.action()
	}

	interface EventSource {
		void registerListener(EventListener e);
	}

	interface EventListener {
		void onEvent(Event e);
	}

	interface Event {
	}
}
