package com.yincongyang.base.structure.container;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.yincongyang.base.structure.basetype.RangeOfTypes;

/**
 * 
 * 对Set的一些学习与测试
 * @author yincongyang
 *
 */
public class HashSetTest {
	private static final Logger logger = LoggerFactory.getLogger(HashSetTest.class);

	private Set set1;
	private Set<String> set2;
	private Set set3;

	public HashSetTest() {
		this.set1 = new HashSet();//直接初始化
		this.set2 = new HashSet<String>();//泛型
		this.set3 = Sets.newHashSet();//guava初始化
	}

	public static void hashSet() {
		Set hashSet = new HashSet();
		Set<String> hashSetString = new HashSet<String>();

		hashSet.add(1);
		hashSet.add(2);
		hashSet.add(3);
		hashSet.add(6);
		hashSet.add(5);

		hashSetString.add("1");
		hashSetString.add("5");
		hashSetString.add("3");
		hashSetString.add("4");
		hashSetString.add("2");

		logger.info(hashSet.toString());
		logger.info(hashSetString.toString());

	}

	public static void main(String[] args) {
		HashSetTest.hashSet();
	}
}
