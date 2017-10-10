package com.yincongyang.thread.concept.escape;

import com.yincongyang.thread.annotation.ThreadSafe;

/**
 * 解决this逸出类
 * @author yincongyang
 *
 */
@ThreadSafe
public class NoThisEscape {
	private final EventListener listener;

	private NoThisEscape() {
		listener = new EventListener() {
			public void onEvent(Event e) {
				doSomething(e);
			}
		};
	}

	public static NoThisEscape newInstance(EventSource source) {
		NoThisEscape safe = new NoThisEscape();
		source.registerListener(safe.listener);
		return safe;
	}

	void doSomething(Event e) {
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