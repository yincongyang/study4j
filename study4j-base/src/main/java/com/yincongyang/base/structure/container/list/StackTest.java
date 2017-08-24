package com.yincongyang.base.structure.container.list;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Stack;
import java.util.Vector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yincongyang.base.structure.container.map.HashMapTest;

/**
 * Statck特点
 * 
 * - 数据结构：继承自Vector，故也是数组
 * - 同步，线程安全
 * - fail-fast机制
 * - LIFO  position[1,n] index[0,n-1]
 * @author yincongyang
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class StackTest {
	private static final Logger logger = LoggerFactory.getLogger(StackTest.class);

	public static void main(String[] args) {
		Stack stack = new Stack();
		// 将1,2,3,4,5添加到栈中
		for (int i = 1; i < 6; i++) {
			stack.push(String.valueOf(i));
		}

		// 遍历并打印出该栈
		iteratorThroughRandomAccess(stack);//good

		// 查找“2”在栈中的位置，并输出
		int pos = stack.search("1");
		System.out.println("the postion of 5 is:" + pos);

		// pup栈顶元素之后，遍历栈
		stack.pop();
		iteratorThroughRandomAccess(stack);

		// peek栈顶元素之后，遍历栈
		String val = (String) stack.peek();
		System.out.println("peek:" + val);
		iteratorThroughRandomAccess(stack);

		// 通过Iterator去遍历Stack
		iteratorThroughIterator(stack);//recommend
	}

	/**
	 * 通过快速访问遍历Stack
	 */
	public static void iteratorThroughRandomAccess(List list) {
		String val = null;
		for (int i = 0; i < list.size(); i++) {
			val = (String) list.get(i);
			System.out.print(val + " ");
		}
		System.out.println();
	}

	/**
	 * 通过迭代器遍历Stack
	 */
	public static void iteratorThroughIterator(List list) {

		String val = null;
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			val = (String) iter.next();
			System.out.print(val + " ");
		}
		System.out.println();
	}

}
