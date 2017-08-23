package com.yincongyang.base.structure.container.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hashMap总结
 * 
 * 非同步
 * fail-fast机制
 * @author yincongyang
 *
 */
public class HashMapTest {
	private static final Logger logger = LoggerFactory.getLogger(HashMapTest.class);

	/**
	 * 当hashmap元素越来越多时，其出现hash冲突的概率越来越大，为了提高查找效率，则会进行自动扩容。
	 * 
	 * 当 hashmap.size() > threshold = (int)(capacity * loadFactor)时会自动扩容；
	 * 其中capacity默认为16；loadFactor默认为0.75；默认超过12个元素就会自动扩容
	 * 
	 * 自动扩容最消耗性能，但是比较后发现：HashMap 初始化时不指定大小时性能要高一些，可能进行位运算或者其它运算也比较耗性能
	 */
	@Test
	public void putReSizeTest() {
		Map<String, Object> map = new HashMap<String, Object>();

		long startTime = System.nanoTime();//获取开始时间
		for (int i = 0; i < 500000; i++) {
			map.put(String.valueOf(i), i);
		}
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("HashMap().put程序运行时间：{} ns", (endTime - startTime));
	}

	@Test
	public void putReSizeTest1() {
		Map<String, Object> map1 = new HashMap<String, Object>(1 << 20);//8388608

		long startTime1 = System.nanoTime();//获取开始时间
		for (int i = 0; i < 500000; i++) {
			map1.put(String.valueOf(i), i);
		}
		long endTime1 = System.nanoTime(); //获取结束时间  
		logger.info("HashMap(i,f).put程序运行时间：{} ns", (endTime1 - startTime1));
	}

	/**
	 * hashmap 搜索的最坏复杂度为 O(1+n) = O(n) 并且n比较小   大部分情况为O(1)
	 */
	@Test
	public void getTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < 500000; i++) {
			map.put(String.valueOf(i), i);
		}

		long startTime = System.nanoTime();//获取开始时间
		Integer value = (Integer) map.get(49900);
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("HashMap().get={}程序运行时间：{} ns", value, (endTime - startTime));
	}

	/**
	 * hashMap遍历操作性能测试
	 * 
	 * 推荐使用Iterator<Map.Entry<K, V>> 
	 * 
	 * 因为 foreach 方式不可以在循环内进行新增put或者删除remove操作
	 * 原因：hashmap中 expectedModCount!=modCount（修改次数） 时会抛异常。只有Iterator.remove()同步了expectedModCount=modCount
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void iteratorTest() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < 500000; i++) {
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

	/**
	 * fail-fast机制: 
	 * 		可以使用Iterator.remove 但是没有Iterator.add，也不能对集合本身调用put、remove方法
	 * 推荐使用Iterator<Map.Entry<K, V>> 
	 * 
	 * 因为 foreach 方式不可以在循环内进行新增put或者删除remove操作
	 * 原因：hashmap中 expectedModCount!=modCount（修改次数） 时会抛异常。只有Iterator.remove()同步了expectedModCount=modCount
	 */
	@Test
	public void removeTest() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int initSize = 3000;
		for (int i = 0; i < 3000; i++) {
			map.put(String.valueOf(i), i);
		}

		//使用泛型
		Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			if (entry.getValue() % 3 == 0) {
				it.remove();
			}
			//map.put("fail-fast", 0);//ConcurrentModificationException
			//map.remove("fail-fast");//ConcurrentModificationException
		}
		logger.info("Iterator.remove 初始大小：{} 删除后大小：{}", initSize, map.size());
	}

	/**
	 * hashmap key允许为null value允许为null
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void hashMapNullTest() {
		Map map = new HashMap();

		map.put(null, null);
		map.put("1", null);

		logger.info(map.get(null) + "");
		logger.info(map.get("1") + "");
	}

	/**
	 * hashmap 防止hash碰撞的方式为：
	 * 	  n = hash(hashcode) //此算法加入了高位计算，防止低位不变，高位变化时，造成的 hash 冲突。
	 *    index = n & (length - 1)//l-1 地位均为1111，可以算出[0,length)之间的值
	 * 其数组长度永远为2的n此方，计算位置时用 n & (length - 1)
	 */
	@Test
	public void hashMapIndexTest() {
		int n = 99;//hash(hashcode)通过计算得到的hash值
		int length = 16;//数组长度

		int index = n & (length - 1);
		logger.info("hash(hashcode) = 99 计算后的位置为：" + index);
	}

}
