package com.ycy.study4j.jdk.collection.container.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Comparable 是排序接口。
 * 若一个类实现了Comparable接口，就意味着“该类支持排序”。类似于“内部排序器”
 * 即然实现Comparable接口的类支持排序，
 * 假设现在存在“实现Comparable接口的类的对象的List列表(或数组)”，
 * 
 * 调用排序方法：
 *     1、则该List列表(或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序。
 *     2、此外，“实现Comparable接口的类的对象”可以用作“有序映射(如TreeMap)”中的键或“有序集合(TreeSet)”中的key(默认排序)，而不需要指定比较器。
 * @author yincongyang
 *
 */
public class ComparableTest {
	private static final Logger logger = LoggerFactory.getLogger(ComparableTest.class);

	@Test
	public void basetypeSortTest() {
		List<Person> list = new ArrayList<Person>();

		list.add(new Person(7, "a"));
		list.add(new Person(8, "b"));
		list.add(new Person(1, "k"));
		list.add(new Person(2, "l"));

		logger.info("list 默认顺序为：{}", list);
		Collections.sort(list);//调用Comparable.compareTo进行排序（对象内部排序）
		logger.info("实现Comparable顺序后为：{}", list);

		/**
		 * Comparator 外部比较器
		 * 
		 * 推荐：使用方便，直观
		 * 
		 * 调用方法：
		 * 1、 Collections.sort(collection,Comparator)
		 * 2、 TreeMap或者TreeSet初始化时传入
		 */
		Collections.sort(list, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				return o2.age - o1.age;
			}
		});
		logger.info("调用Comparator顺序后为：{}", list);

	}

	public static class Person implements Comparable<Person> {

		int age;
		String name;

		public Person(int age, String name) {
			super();
			this.age = age;
			this.name = name;
		}

		@Override
		public String toString() {
			return "[age=" + age + ", name=" + name + "]";
		}

		@Override
		public int compareTo(Person p) {
			return age - p.age;
		}

	}
}
