package com.ycy.study4j.jdk.collection.container.map;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hashtable总结
 * 
 * 同步
 * fail-fast机制
 * @author yincongyang
 *
 */
public class HashTableTest {
	private static final Logger logger = LoggerFactory.getLogger(HashTableTest.class);

	/**
	 * 当Hashtable元素越来越多时，其出现hash冲突的概率越来越大，为了提高查找效率，则会进行自动扩容。
	 * 
	 * 当 Hashtable.size() > threshold = (int)(capacity * loadFactor)时会自动扩容；
	 * 其中capacity默认为11；loadFactor默认为0.75；默认超过8个元素就会自动扩容
	 * 
	 * 自动扩容最消耗性能，但是比较后发现：Hashtable 初始化时不指定大小时性能要高一些，
	 * 
	 * 推荐直接 new Hashtable()
	 */
	@Test
	public void hashtableReSizeTest() {
		Hashtable<String, Object> table = new Hashtable<String, Object>();

		long startTime = System.nanoTime();//获取开始时间
		for (int i = 0; i < 500000; i++) {
			table.put(String.valueOf(i), i);
		}
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("Hashtable.put程序运行时间：{} ns", (endTime - startTime));
	}

	@Test
	public void hashtableReSizeTest1() {
		Hashtable<String, Object> table = new Hashtable<String, Object>(1 << 20);//1048576

		long startTime = System.nanoTime();//获取开始时间
		for (int i = 0; i < 500000; i++) {
			table.put(String.valueOf(i), i);
		}
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("Hashtable(i).put程序运行时间：{} ns", (endTime - startTime));
	}

	/**
	 * hashtable 搜索的复杂度为 O(1+n) = O(n) 并且n比较小
	 */
	@Test
	public void hashtableGetTest() {
		Hashtable<String, Object> table = new Hashtable<String, Object>();
		for (int i = 0; i < 500000; i++) {
			table.put(String.valueOf(i), i);
		}

		long startTime = System.nanoTime();//获取开始时间
		Integer value = (Integer) table.get(49900);
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("Hashtable.get={}程序运行时间：{} ns", value, (endTime - startTime));
	}

	/**
	 * hashtable 遍历操作性能测试
	 * 
	 * 推荐使用entrySet.Iterator<Map.Entry<K, V>> 或者 keySet.Iterator<T>
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void iteratorTest() {
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		for (int i = 0; i < 500000; i++) {
			table.put(String.valueOf(i), i);
		}

		String key = "";
		Integer value = 0;

		/**
		 * Enumeration 通过枚举遍历
		 */
		//table.keys()
		long startTime = System.nanoTime();//获取开始时间
		Enumeration enu = table.keys();
		while (enu.hasMoreElements()) {
			key = (String) enu.nextElement();
			table.remove(key);//Enumeration没有fail-fast机制
		}
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("table.keys() 最后一个值为：{} 程序运行时间：{} ns", table.get(key), (endTime - startTime));

		//table.elements()
		long startTime1 = System.nanoTime();//获取开始时间
		Enumeration enu1 = table.elements();
		while (enu1.hasMoreElements()) {
			value = (Integer) enu1.nextElement();
		}
		long endTime1 = System.nanoTime(); //获取结束时间  
		logger.info("table.elements() 最后一个值为：{} 程序运行时间：{} ns", value, (endTime1 - startTime1));

		/**
		 * 使用迭代器iterator
		 */
		//entrySet不使用泛型
		long startTime3 = System.nanoTime();//获取开始时间
		Iterator it = table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			key = (String) entry.getKey();
			value = (Integer) entry.getValue();
		}
		long endTime3 = System.nanoTime(); //获取结束时间  
		logger.info("entrySet.Iterator 最后一个值为：{} 程序运行时间：{} ns", value, (endTime3 - startTime3));

		//entrySet使用泛型
		long startTime4 = System.nanoTime();//获取开始时间
		Iterator<Map.Entry<String, Integer>> it1 = table.entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry<String, Integer> entry = it1.next();
			key = entry.getKey();
			value = entry.getValue();
		}
		long endTime4 = System.nanoTime(); //获取结束时间  
		logger.info("entrySet.Iterator<Map.Entry<K, V>> 最后一个值为：{} 程序运行时间：{} ns", value, (endTime4 - startTime4));

		// keySet使用泛型
		long startTime5 = System.nanoTime();//获取开始时间
		Iterator<String> it2 = table.keySet().iterator();
		while (it2.hasNext()) {
			key = it2.next();// 获取key
		}
		long endTime5 = System.nanoTime(); //获取结束时间  
		logger.info("keySet.Iterator<T> 最后一个值为：{} 程序运行时间：{} ns", table.get(key), (endTime5 - startTime5));

		// values使用泛型
		long startTime6 = System.nanoTime();//获取开始时间
		Iterator<Integer> it3 = table.values().iterator();
		while (it3.hasNext()) {
			value = it3.next();// 获取value
		}
		long endTime6 = System.nanoTime(); //获取结束时间  
		logger.info("values.Iterator<T> 最后一个值为：{} 程序运行时间：{} ns", value, (endTime6 - startTime6));
	}

	/**
	 * fail-fast机制
	 * 
	 */
	@Test
	public void hashtableRemoveTest() {
		Hashtable<String, Integer> table = new Hashtable<String, Integer>();
		int initSize = 3000;
		for (int i = 0; i < initSize; i++) {
			table.put(String.valueOf(i), i);
		}

		//Enumeration 不是fail-fast的
		String key = "";
		Enumeration<String> enu = table.keys();
		while (enu.hasMoreElements()) {
			key = enu.nextElement();
			table.remove(key);//Enumeration没有fail-fast机制
		}

		//Iterator是fail-fast的
		Iterator<Map.Entry<String, Integer>> it = table.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			if (entry.getValue() % 3 == 0) {
				it.remove();
				//table.put("fail-fast", 2);//ConcurrentModificationException
				//table.remove("fail-fast");//ConcurrentModificationException
			}
		}
		logger.info("Iterator.remove 初始大小：{} 删除后大小：{}", initSize, table.size());
	}

	/**
	 * hashtable key不允许为null value不允许为null
	 */
	//	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void hashtableNullTest() {
		//		Hashtable table = new Hashtable();

		//		table.put(null, "1");
		//		table.put("1", null);

		//		logger.info(table.get(null) + "");
		//		logger.info(table.get("1") + "");
	}

}
