package com.yincongyang.base.structure.container.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * LinkedHashMap
 * 
 * 继承自HashMap 其维护一个双向链表来记录插入顺序或者是访问顺序
 *
 * 非同步
 * fail-fast机制
 * @author yincongyang
 *
 */
public class LinkedHashMapTest {
	private static final Logger logger = LoggerFactory.getLogger(LinkedHashMapTest.class);

	@Test
	@SuppressWarnings("rawtypes")
	public void LinkedHashMapInitTest() {
		//HashMap无序
		Map<String, String> map = new HashMap<String, String>();
		map.put("apple", "苹果");
		map.put("watermelon", "西瓜");
		map.put("banana", "香蕉");
		map.put("peach", "桃子");

		logger.info("HashMap存储顺序为：");
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			logger.info(entry.getKey() + "=" + entry.getValue());
		}

		//默认为按照插入顺序排序
		Map<String, String> map1 = new LinkedHashMap<String, String>();
		map1.put("apple", "苹果");
		map1.put("watermelon", "西瓜");
		map1.put("banana", "香蕉");
		map1.put("peach", "桃子");

		logger.info("LinkedHashMap按照插入顺序排序为：");
		Iterator iter1 = map1.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry entry = (Map.Entry) iter1.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}

		//LinkedHashMap(int initialCapacity,float loadFactor,boolean accessOrder) 
		//accessOrder= true 是按照访问顺序
		Map<String, String> map2 = new LinkedHashMap<String, String>(16, 0.75f, true);
		map2.put("apple", "苹果");
		map2.put("watermelon", "西瓜");
		map2.put("banana", "香蕉");
		map2.put("peach", "桃子");

		map2.get("banana");
		map2.get("apple");

		logger.info("LinkedHashMap按照访问顺序排序为：");
		Iterator iter2 = map2.entrySet().iterator();
		while (iter2.hasNext()) {
			Map.Entry entry = (Map.Entry) iter2.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}

	/**
	 * LinkedHashMap遍历操作性能测试
	 * 
	 * 推荐使用Iterator<Map.Entry<K, V>> 
	 * 
	 * 注意：若是声明为访问排序：则在迭代时使用了LinkedHashMap.get(key)也会造成fail-fast
	 * 	    若是默认的插入排序：则不会
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void iteratorTest() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>(16, 0.75f, true);
		for (int i = 0; i < 50000; i++) {
			map.put(String.valueOf(i), i);
		}

		String key = "";
		Integer value = 0;

		/**
		 * foreach语句
		 * 1、如果你遍历的map是null的话，For-Each循环会抛出NullPointerException异常，所以在遍历之前你应该判断是否为空引用。
		 * 2、不可以在foreach语句里面加入remove操作？？
		 */
		//foreach(Map.EntrySet)
		long startTime = System.nanoTime();//获取开始时间
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			//map.remove(key);//ConcurrentModificationException
			//			map.get(key);//访问排序：ConcurrentModificationException 插入排序：good
		}
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("foreach(Map.EntrySet) 最后一个值为：{} 程序运行时间：{} ns", value.toString(), (endTime - startTime));

		//foreach Map.KeySet
		long startTime1 = System.nanoTime();//获取开始时间
		for (String k : map.keySet()) {
			key = k;
		}
		long endTime1 = System.nanoTime(); //获取结束时间  
		logger.info("foreach Map.KeySet 最后一个值为：{} 程序运行时间：{} ns", map.get(key), (endTime1 - startTime1));

		//foreach Map.ValueSet
		long startTime2 = System.nanoTime();//获取开始时间
		for (Integer v : map.values()) {
			value = v;
		}
		long endTime2 = System.nanoTime(); //获取结束时间  
		logger.info("foreach Map.ValueSet 最后一个值为：{} 程序运行时间：{} ns", value, (endTime2 - startTime2));

		/**
		 * 使用迭代器iterator
		 */
		//不使用泛型
		long startTime3 = System.nanoTime();//获取开始时间
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			key = (String) entry.getKey();
			value = (Integer) entry.getValue();
		}
		long endTime3 = System.nanoTime(); //获取结束时间  
		logger.info("Iterator 最后一个值为：{} 程序运行时间：{} ns", value, (endTime3 - startTime3));

		//使用泛型
		long startTime4 = System.nanoTime();//获取开始时间
		Iterator<Map.Entry<String, Integer>> entries1 = map.entrySet().iterator();
		while (entries1.hasNext()) {
			Map.Entry<String, Integer> entry = entries1.next();
			key = entry.getKey();
			value = entry.getValue();
		}
		long endTime4 = System.nanoTime(); //获取结束时间  
		logger.info("Iterator<Map.Entry<K, V>> 最后一个值为：{} 程序运行时间：{} ns", value, (endTime4 - startTime4));
	}

}
