package com.yincongyang.base.structure.container.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.yincongyang.base.structure.basetype.RangeOfTypes;

/**
 * TreeSet测试
 * - 基于TreeSet的key实现：故不允许使用null元素、不允许重复、有序、非同步、fail-fast
 * - 支持按照自然排序或者可实现Comparable实现自定义排序
 * 
 * @author yincongyang
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class TreeSetTest {
	private static final Logger logger = LoggerFactory.getLogger(TreeSetTest.class);

	public static void main(String[] args) {
		testTreeSetAPIs();
	}

	// 测试TreeSet的api
	public static void testTreeSetAPIs() {
		String val;

		// 新建TreeSet
		TreeSet tSet = new TreeSet();
		// 将元素添加到TreeSet中
		tSet.add("aaa");
		// Set中不允许重复元素，所以只会保存一个“aaa”
		tSet.add("aaa");
		tSet.add("bbb");
		tSet.add("eee");
		tSet.add("ddd");
		tSet.add("ccc");
		System.out.println("TreeSet:" + tSet);

		// 打印TreeSet的实际大小
		System.out.printf("size : %d\n", tSet.size());

		// 导航方法
		// floor(小于、等于)
		System.out.printf("floor bbb: %s\n", tSet.floor("bbb"));
		// lower(小于)
		System.out.printf("lower bbb: %s\n", tSet.lower("bbb"));
		// ceiling(大于、等于)
		System.out.printf("ceiling bbb: %s\n", tSet.ceiling("bbb"));
		System.out.printf("ceiling eee: %s\n", tSet.ceiling("eee"));
		// ceiling(大于)
		System.out.printf("higher bbb: %s\n", tSet.higher("bbb"));
		// subSet()
		System.out.printf("subSet(aaa, true, ccc, true): %s\n", tSet.subSet("aaa", true, "ccc", true));
		System.out.printf("subSet(aaa, true, ccc, false): %s\n", tSet.subSet("aaa", true, "ccc", false));
		System.out.printf("subSet(aaa, false, ccc, true): %s\n", tSet.subSet("aaa", false, "ccc", true));
		System.out.printf("subSet(aaa, false, ccc, false): %s\n", tSet.subSet("aaa", false, "ccc", false));
		// headSet()
		System.out.printf("headSet(ccc, true): %s\n", tSet.headSet("ccc", true));
		System.out.printf("headSet(ccc, false): %s\n", tSet.headSet("ccc", false));
		// tailSet()
		System.out.printf("tailSet(ccc, true): %s\n", tSet.tailSet("ccc", true));
		System.out.printf("tailSet(ccc, false): %s\n", tSet.tailSet("ccc", false));

		// 删除“ccc”
		tSet.remove("ccc");
		// 将Set转换为数组
		String[] arr = (String[]) tSet.toArray(new String[0]);
		for (String str : arr)
			System.out.printf("for each : %s\n", str);

		// 打印TreeSet
		System.out.printf("TreeSet:%s\n", tSet);

		// 遍历TreeSet
		for (Iterator iter = tSet.iterator(); iter.hasNext();) {
			System.out.printf("iter : %s\n", iter.next());
		}

		// 删除并返回第一个元素
		val = (String) tSet.pollFirst();
		System.out.printf("pollFirst=%s, set=%s\n", val, tSet);

		// 删除并返回最后一个元素
		val = (String) tSet.pollLast();
		System.out.printf("pollLast=%s, set=%s\n", val, tSet);

		// 清空HashSet
		tSet.clear();

		// 输出HashSet是否为空
		System.out.printf("%s\n", tSet.isEmpty() ? "set is empty" : "set is not empty");
	}
}
