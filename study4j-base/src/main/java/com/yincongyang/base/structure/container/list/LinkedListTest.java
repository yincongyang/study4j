package com.yincongyang.base.structure.container.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yincongyang.base.structure.container.map.HashMapTest;

/**
 * LinkedList特点
 * 
 * - 数据结构：双向链表（用head节点连接首尾）
 * - 不同步
 * - fail-fast机制
 * - 尽量不要用随机访问去插入或者访问元素：即LinkedList.get(),LinkedList.set()
 * @author yincongyang
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class LinkedListTest {
	private static final Logger logger = LoggerFactory.getLogger(LinkedListTest.class);

	public static void main(String[] args) {
		// 测试LinkedList的API
		testLinkedListAPIs();

		// 将LinkedList当作 LIFO(后进先出)的堆栈
		useLinkedListAsLIFO();

		// 将LinkedList当作 FIFO(先进先出)的队列
		useLinkedListAsFIFO();

		/**
		 * 推荐使用迭代器或者 foreach对象来迭代
		 * 
		 * 不推荐使用随机访问方式迭代：性能消耗太大
		 */
		// 通过Iterator遍历LinkedList
		iteratorLinkedListThruIterator(getLinkedList());
		// 通过快速随机访问遍历LinkedList
		iteratorLinkedListThruForeach(getLinkedList());
		// 通过for循环的变种来访问遍历LinkedList
		iteratorThroughFor2(getLinkedList());
		// 通过PollFirst()遍历LinkedList
		iteratorThroughPollFirst(getLinkedList());
		// 通过PollLast()遍历LinkedList
		iteratorThroughPollLast(getLinkedList());
		// 通过removeFirst()遍历LinkedList
		iteratorThroughRemoveFirst(getLinkedList());
		// 通过removeLast()遍历LinkedList
		iteratorThroughRemoveLast(getLinkedList());
	}

	/* 测试LinkedList中部分API */
	private static void testLinkedListAPIs() {
		String val = null;
		// 新建一个LinkedList
		LinkedList llist = new LinkedList();
		//---- 添加操作 ----
		logger.info("测试 \"addFirst(), removeFirst(), getFirst()\"");
		llist.add("1");
		llist.add("2");
		llist.add("3");
		logger.info("addFirst()依次添加1,2,3:{}", llist);
		llist.add(1, "4");// 将“4”添加到第一个位置
		logger.info("addFirst(index,value)将“4”添加到index[1]位置:{}", llist);
		llist.addFirst("10");
		logger.info("addFirst()将“10”添加到首位:{}", llist);
		logger.info("removeFirst()删除第一个元素:{},删除后:{}", llist.removeFirst(), llist);
		logger.info("getFirst()获取第一个元素:{}", llist.getFirst());

		logger.info("\n测试 \"offerFirst(), pollFirst(), peekFirst()\"");
		llist.offerFirst("10");
		logger.info("offerFirst()将“10”添加到第一个位置:{}", llist);
		logger.info("pollFirst()删除第一个元素:{},删除后:{}", llist.pollFirst(), llist);
		logger.info("peekFirst()获取第一个元素:{}", llist.peekFirst());

		logger.info("\n测试 \"addLast(), removeLast(), getLast()\"");
		llist.addLast("20");
		logger.info("addLast()将“20”添加到最后一个位置:{}", llist);
		logger.info("removeLast()将最后一个元素删除:{},删除后:{}", llist.removeLast(), llist);
		logger.info("getLast()获取最后一个元素:{}", llist.getLast());

		logger.info("\n测试 \"offerLast(), pollLast(), peekLast()\"");
		llist.offerLast("20");
		logger.info("offerLast()将“20”添加到最后一个位置:{}", llist);
		logger.info("pollLast()将最后一个元素删除:{},删除后:{}", llist.pollLast(), llist);
		logger.info("peekLast()获取最后一个元素:" + llist.peekLast());

		llist.set(2, "300");// 将第3个元素设置300。不建议在LinkedList中使用此操作，因为效率低！
		llist.get(2);// 获取第3个元素。不建议在LinkedList中使用此操作，因为效率低！

		// ---- toArray(T[] a) ----
		// 将LinkedList转行为数组
		String[] arr = (String[]) llist.toArray(new String[0]);

	}

	/**
	 * 将LinkedList当作 LIFO(后进先出)的堆栈
	 */
	private static void useLinkedListAsLIFO() {
		logger.info("\n测试 LinkedListAs Stack(LIFO)");
		// 新建一个LinkedList
		LinkedList stack = new LinkedList();

		// 将1,2,3,4添加到堆栈中
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");

		logger.info("stack.push()向栈中加入元素：" + stack);
		logger.info("stack.pop()删除“栈顶元素”:" + stack.pop());
		logger.info("stack.peek()取出“栈顶元素”:" + stack.peek());
		logger.info("stack:" + stack);
	}

	/**
	 * 将LinkedList当作 FIFO(先进先出)的队列
	 */
	private static void useLinkedListAsFIFO() {
		logger.info("\n测试 LinkedList as queue(FIFO)");
		// 新建一个LinkedList
		LinkedList queue = new LinkedList();

		// 将10,20,30,40添加到队列。每次都是插入到末尾
		queue.add("10");
		queue.add("20");
		queue.add("30");
		queue.add("40");
		logger.info("queue.add()向队列中加入元素:" + queue);
		logger.info("queue.remove()删除(队列的第一个元素):" + queue.remove());
		logger.info("queue.element()读取(队列的第一个元素):" + queue.element());
		logger.info("queue:" + queue);
	}

	private static LinkedList getLinkedList() {
		LinkedList llist = new LinkedList();
		for (int i = 0; i < 100000; i++)
			llist.addLast(i);

		return llist;
	}

	/**
	 * 通过快迭代器遍历LinkedList
	 */
	private static void iteratorLinkedListThruIterator(LinkedList<Integer> list) {
		if (list == null)
			return;

		// 记录开始时间
		long start = System.currentTimeMillis();

		for (Iterator iter = list.iterator(); iter.hasNext();) {
			iter.next();
			//list.remove();//ConcurrentModificationException
		}

		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("iteratorLinkedListThruIterator：" + interval + " ms");
	}

	/**
	 * 通过快速随机访问遍历LinkedList
	 */
	private static void iteratorLinkedListThruForeach(LinkedList<Integer> list) {
		if (list == null)
			return;

		// 记录开始时间
		long start = System.currentTimeMillis();

		int size = list.size();
		for (int i = 0; i < size; i++) {
			list.get(i);
		}
		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("iteratorLinkedListThruForeach：" + interval + " ms");
	}

	/**
	 * 通过另外一种for循环来遍历LinkedList
	 */
	private static void iteratorThroughFor2(LinkedList<Integer> list) {
		if (list == null)
			return;

		// 记录开始时间
		long start = System.currentTimeMillis();

		for (Integer integ : list) {
			//list.remove();//ConcurrentModificationException
		}

		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("iteratorThroughFor2：" + interval + " ms");
	}

	/**
	 * 通过pollFirst()来遍历LinkedList
	 */
	private static void iteratorThroughPollFirst(LinkedList<Integer> list) {
		if (list == null)
			return;

		// 记录开始时间
		long start = System.currentTimeMillis();
		while (list.pollFirst() != null)
			;

		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("iteratorThroughPollFirst：" + interval + " ms");
	}

	/**
	 * 通过pollLast()来遍历LinkedList
	 */
	private static void iteratorThroughPollLast(LinkedList<Integer> list) {
		if (list == null)
			return;

		// 记录开始时间
		long start = System.currentTimeMillis();
		while (list.pollLast() != null)
			;

		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("iteratorThroughPollLast：" + interval + " ms");
	}

	/**
	 * 通过removeFirst()来遍历LinkedList
	 */
	private static void iteratorThroughRemoveFirst(LinkedList<Integer> list) {
		if (list == null)
			return;

		// 记录开始时间
		long start = System.currentTimeMillis();
		try {
			while (list.removeFirst() != null)
				;
		} catch (NoSuchElementException e) {
		}

		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("iteratorThroughRemoveFirst：" + interval + " ms");
	}

	/**
	 * 通过removeLast()来遍历LinkedList
	 */
	private static void iteratorThroughRemoveLast(LinkedList<Integer> list) {
		if (list == null)
			return;

		// 记录开始时间
		long start = System.currentTimeMillis();
		try {
			while (list.removeLast() != null)
				;
		} catch (NoSuchElementException e) {
		}

		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("iteratorThroughRemoveLast：" + interval + " ms");
	}

}
