package com.yincongyang.base.thread;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.yincongyang.base.thread.annotation.Immutable;

/**
 * 要创建不可变类，要实现下面几个步骤：
 *   - 将类声明为final，所以它不能被继承
 *   - 将所有的成员声明为私有的，这样就不允许直接访问这些成员
 *   - 对变量不要提供setter方法
 *   - 将所有可变的成员声明为final，这样只能对它们赋值一次
 *   - 通过构造器初始化所有成员，进行深拷贝(deep copy)
 *   - 在getter方法中，不要直接返回对象本身，而是克隆对象，并返回对象的拷贝
 *   
 * 要注意 深拷贝和浅拷贝的区别！！
 * @author yincongyang
 *
 */
@Immutable
public final class FinalClassExample {

	private final int id;

	private final String name;

	/**
	 * 对于引用类型的处理很重要
	 */
	private final HashMap<String, String> testMap;

	private final String[] arr;

	public String[] getArr() {
		//保证arr的不可变性
		return Arrays.copyOf(this.arr, arr.length);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * 可变对象的访问方法
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getTestMap() {
		//return testMap;//bad 返回原始对象，则可以修改对象值，从而破坏了对象的不可变性

		//对于引用类型，需要返回其clone，不能返回原始对象
		return (HashMap<String, String>) testMap.clone();
	}

	/**
	 * 实现深拷贝(deep copy)的构造器
	 * @param i
	 * @param n
	 * @param hm
	 */
	public FinalClassExample(int i, String n, HashMap<String, String> hm, String[] arr) {
		System.out.println("Performing Deep Copy for Object initialization");
		this.id = i;
		this.name = n;
		this.arr = Arrays.copyOf(arr, arr.length);
		/**
		 * 对于引用类型的成员变量，采取深拷贝的模式初始化，保证其不受外部对象的改变而改变，从而达到对象的不变性
		 */
		HashMap<String, String> tempMap = new HashMap<String, String>();
		Iterator<Map.Entry<String, String>> it = hm.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			tempMap.put(entry.getKey(), entry.getValue());
		}
		this.testMap = tempMap;
	}

	/**
	 * 实现浅拷贝(shallow copy)的构造器
	 * @param i
	 * @param n
	 * @param hm
	 */
	/**
	public FinalClassExample(int i, String n, HashMap hm){
	    System.out.println("Performing Shallow Copy for Object initialization");
	    this.id=i;
	    this.name=n;
	    this.testMap=hm;//获取外部对象引用，会破坏对象的不可变性
	}
	*/

	/**
	 * 测试浅拷贝的结果
	 * 为了创建不可变类，要使用深拷贝
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, String> h1 = new HashMap<String, String>();
		h1.put("1", "first");
		h1.put("2", "second");

		String[] arr = new String[] { "aa", "bb" };

		String s = "original";
		int i = 10;

		FinalClassExample ce = new FinalClassExample(i, s, h1, arr);

		//Lets see whether its copy by field or reference
		System.out.println(s == ce.getName());
		System.out.println(h1 == ce.getTestMap());
		//print the ce values
		System.out.println("ce id:" + ce.getId());
		System.out.println("ce name:" + ce.getName());
		System.out.println("ce testMap:" + ce.getTestMap());
		//change the local variable values
		i = 20;
		s = "modified";
		h1.put("3", "third");
		//print the values again
		System.out.println("ce id after local variable change:" + ce.getId());
		System.out.println("ce name after local variable change:" + ce.getName());
		System.out.println("ce testMap after local variable change:" + ce.getTestMap());

		HashMap<String, String> hmTest = ce.getTestMap();
		hmTest.put("4", "new");

		System.out.println("ce testMap after changing variable from accessor methods:" + ce.getTestMap());

	}

}
