package com.ycy.study4j.jdk.collection.container.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ycy.study4j.jdk.collection.container.map.HashMapTest;

/**
 * ArrayList特点
 * 
 * - 可自动扩展的数组，故支持随机访问
 * - 不同步
 * @author yincongyang
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class ArrayListTest {
	private static final Logger logger = LoggerFactory.getLogger(ArrayListTest.class);

	@Test
	public void test() {
		List list = new ArrayList();
		for (int i = 0; i < 100000; i++){
			list.add(i);
		}
		/**
		 * 3种遍历方式：
		 * for循环：随机访问：不会fail-fast
		 * 迭代器：iterator：会fail-fast
		 * foreach对象：会fail-fast
		 * 
		 * 推荐使用for循环，随机访问方式效率高
		 */
		iteratorThroughRandomAccess(list);//recommend
		iteratorThroughIterator(list);//good
		iteratorThroughFor2(list);

		/**
		 * ToArray使用方法
		 * Object[] toArray() 返回Object[]，不能向下转型，会抛java.lang.ClassCastException异常
		 * 
		 * 常用<T> T[] toArray(T[] contents)方法
		 */
		ArrayList<Integer> list1 = new ArrayList();
		for (int i = 0; i < 100000; i++){
			list1.add(i);
		}
		Integer[] resArr = new Integer[0];
		//resArr = (Integer[]) list1.toArray();//返回Object[]，不能向下转型 bad
		resArr = arrayListToArray1(list1);
		resArr = arrayListToArray2(list1);//(Integer[]) list1.toArray(new Integer[0]);//recommend good
		resArr = arrayListToArray3(list1);

		/**
		 * ArrayList删除步骤，会将其后面所有的值往前挪一位，
		 * - 比较耗性能，
		 * - list的大小发生了变化，并且该元素后面所有的元素下标会发生变化，
		 *   所以通过从前往后循环删除元素可能存在漏删元素的问题。
		 */
		ArrayListTest.removeThroughForAsc();//bad
		ArrayListTest.removeThroughForDesc();//good
		ArrayListTest.removeThroughForeach();//bad
		ArrayListTest.removeThroughIterator();//good recommend
	}

	public static void iteratorThroughRandomAccess(List list) {

		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
			//list.remove(0);// not fail-fast
		}
		endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println("iteratorThroughRandomAccess：" + interval + " ms");
	}

	public static void iteratorThroughIterator(List list) {

		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			iter.next();
			//list.remove(0);//fail-fast
		}
		endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println("iteratorThroughIterator：" + interval + " ms");
	}

	public static void iteratorThroughFor2(List list) {

		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();
		for (Object obj : list) {
			//list.remove(0);//fail-fast
		}
		endTime = System.currentTimeMillis();
		long interval = endTime - startTime;
		System.out.println("iteratorThroughFor2：" + interval + " ms");
	}

	// toArray(T[] contents)调用方式一
	public static Integer[] arrayListToArray1(ArrayList<Integer> v) {
		Integer[] newText = new Integer[v.size()];
		v.toArray(newText);
		return newText;
	}

	// toArray(T[] contents)调用方式二。最常用！
	public static Integer[] arrayListToArray2(ArrayList<Integer> v) {
		Integer[] newText = v.toArray(new Integer[0]);
		return newText;
	}

	// toArray(T[] contents)调用方式三
	public static Integer[] arrayListToArray3(ArrayList<Integer> v) {
		Integer[] newText = new Integer[v.size()];
		Integer[] newStrings = v.toArray(newText);
		return newStrings;
	}

	/**
	 * 有可能会漏掉某些元素：不推荐
	 */
	public static void removeThroughForAsc() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(5);
		logger.info("原list={}", list);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 1){
				list.remove(i);
			}
		}
		logger.info("removeThroughForAsc删除值为1后={}", list);
	}

	/**
	 * 不会漏元素，推荐使用
	 * 
	 * 性能好于迭代器删除
	 */
	public static void removeThroughForDesc() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(5);
		logger.info("原list={}", list);
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i) == 1){
				list.remove(i);
			}
		}
		logger.info("removeThroughForDesc删除值为1后={}", list);
	}

	/**
	 * 只能删除一个元素，不推荐
	 */
	public static void removeThroughForeach() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(5);
		logger.info("原list={}", list);
		for (int value : list) {
			if (value == 1) {
				list.remove(value);
				break;//需要立即跳出，否则会fail-fast
			}
		}
		logger.info("removeThroughForeach删除值为1后={}", list);
	}

	/**
	 * 迭代器删除，推荐
	 */
	public static void removeThroughIterator() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(5);
		logger.info("原list={}", list);
		for (Iterator it = list.iterator(); it.hasNext();) {
			if (it.next().equals(1)) {
				it.remove();
			}
		}
		logger.info("removeThroughIterator删除值为1后={}", list);
	}

}
