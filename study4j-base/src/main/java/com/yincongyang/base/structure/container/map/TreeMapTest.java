package com.yincongyang.base.structure.container.map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * treeMap总结
 * 
 * 数据结构：红黑树
 * 排序：根据key进行排序：自然排序、定制排序
 * TreeMap基于红黑树（Red-Black tree）实现。该映射根据其键的自然顺序进行排序，或者根据创建映射时提供的 Comparator 进行排序，具体取决于使用的构造方法。
 * TreeMap的基本操作 containsKey、get、put 和 remove 的时间复杂度是 log(n) 。
 * 
 * 非同步 
 * fail-fast
 * @author yincongyang
 *
 */
public class TreeMapTest {
	private static final Logger logger = LoggerFactory.getLogger(TreeMapTest.class);

	/**
	 * TreeMap 初始化测试
	 */
	@Test
	public void treeMapInitTest() {
		Map<Integer, Object> map1 = new TreeMap<Integer, Object>();//自然排序
		Map<Integer, Object> map2 = new TreeMap<Integer, Object>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});

		map1.put(3, "1");
		map1.put(7, "6");
		map1.put(6, "7");
		map1.put(1, "2");
		map1.put(9, "4");
		logger.info("TreeMap默认排序后为：{}", map1);

		map2.put(3, "3");
		map2.put(7, "6");
		map2.put(6, "7");
		map2.put(1, "2");
		map2.put(9, "4");
		logger.info("TreeMap自定义排序为：{}", map2);

	}

	/**
	 * treeMap 搜索的最坏复杂度为 O(logn) 基本保持不变
	 */
	@Test
	public void treeMapGetTest() {
		TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();//自然排序
		map.put(2, "1");
		map.put(6, "6");
		map.put(4, "7");
		map.put(8, "2");
		map.put(10, "4");

		long startTime = System.nanoTime();//获取开始时间
		String value = (String) map.get(2);
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("TreeMap().get={}程序运行时间：{} ns", value, (endTime - startTime));
	}

	/**
	 * treeMap 相关方法
	 */
	@Test
	public void treeMapMethodTest() {
		TreeMap<Integer, Object> map = new TreeMap<Integer, Object>();//自然排序
		map.put(2, "1");
		map.put(6, "6");
		map.put(4, "7");
		map.put(8, "2");
		map.put(10, "4");

		logger.info("TreeMap= {}", map);
		logger.info("TreeMap.ceilingKey(9)=   {}", map.ceilingKey(9));//获取TreeMap中大于/等于key的最小的节点，若不存在(即TreeMap中所有节点的键都比key大)，就返回null
		logger.info("TreeMap.ceilingEntry(9)= {}", map.ceilingEntry(9));
		logger.info("TreeMap.floorKey(9)=     {}", map.floorKey(9));//小于等于
		logger.info("TreeMap.firstKey()=      {}", map.firstKey());
		logger.info("TreeMap.firstEntry()=    {}", map.firstEntry());
		logger.info("TreeMap.lastKey()=       {}", map.lastKey());
		logger.info("TreeMap.lastEntry()=     {}", map.lastEntry());
		logger.info("TreeMap.lowerKey(9)=     {}", map.lowerKey(9));//返回小于指定key的最大map键，如果不存在指定map键，返回null
		logger.info("TreeMap.higherKey(9)=    {}", map.higherKey(9));//返回大于指定key的最小map键，如果不存在指定map键，返回null
		logger.info("TreeMap.tailMap(2)=      {}", map.tailMap(2));//返回map中map键大于指定值的子map
		logger.info("TreeMap.subMap(2,8)=     {}", map.subMap(2, 8));//返回map中map键在指定范围内的子map
		logger.info("TreeMap.pollFirstEntry=  {}", map.pollFirstEntry());//移除，并返回map中最小map键关联的map键值对，如果map为空，返回null
		logger.info("TreeMap.pollLastEntry=   {}", map.pollLastEntry());//移除，并返回map中最大map键关联的map键值对，如果map为空，返回null
	}

	/**
	 * treeMap 遍历操作性能测试
	 * 
	 * 推荐使用Iterator<Map.Entry<K, V>> 
	 * !important：性能最高！！！
	 * 
	 * 因为 foreach 方式不可以在循环内进行新增put或者删除remove操作
	 * 原因：hashmap中 expectedModCount!=modCount（修改次数） 时会抛异常。只有Iterator.remove()同步了expectedModCount=modCount
	 */
	@Test
	@SuppressWarnings("rawtypes")
	public void iteratorTest() {
		TreeMap<Integer, String> map = new TreeMap<Integer, String>();//自然排序
		map.put(2, "1");
		map.put(6, "6");
		map.put(4, "7");
		map.put(8, "2");
		map.put(10, "4");

		Integer key = 0;
		String value = "";

		/**
		 * foreach语句
		 * 1、如果你遍历的map是null的话，For-Each循环会抛出NullPointerException异常，所以在遍历之前你应该判断是否为空引用。
		 * 2、不可以在foreach语句里面加入remove操作？？
		 */
		//foreach(Map.EntrySet)
		long startTime = System.nanoTime();//获取开始时间
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			//map.remove(key);//ConcurrentModificationException
		}
		long endTime = System.nanoTime(); //获取结束时间  
		logger.info("foreach(Map.EntrySet) 最后一个值为：{} 程序运行时间：{} ns", value, (endTime - startTime));

		//foreach Map.KeySet
		long startTime1 = System.nanoTime();//获取开始时间
		for (Integer k : map.keySet()) {
			key = k;
		}
		long endTime1 = System.nanoTime(); //获取结束时间  
		logger.info("foreach Map.KeySet 最后一个值为：{} 程序运行时间：{} ns", map.get(key), (endTime1 - startTime1));

		//foreach Map.ValueSet
		long startTime2 = System.nanoTime();//获取开始时间
		for (String v : map.values()) {
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
			key = (Integer) entry.getKey();
			value = (String) entry.getValue();
		}
		long endTime3 = System.nanoTime(); //获取结束时间  
		logger.info("Iterator 最后一个值为：{} 程序运行时间：{} ns", value, (endTime3 - startTime3));

		//使用泛型
		long startTime4 = System.nanoTime();//获取开始时间
		Iterator<Map.Entry<Integer, String>> entries1 = map.entrySet().iterator();
		while (entries1.hasNext()) {
			Map.Entry<Integer, String> entry = entries1.next();
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
		Map<String, Integer> map = new TreeMap<String, Integer>();
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
	 * TreeMap key不允许为null value允许为null
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void treeMapNullTest() {
		Map map = new TreeMap();

		//map.put(null, null);
		map.put("1", null);

		//logger.info(map.get(null) + "");
		logger.info(map.get("1") + "");
	}

}
