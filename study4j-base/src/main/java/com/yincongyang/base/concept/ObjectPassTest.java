package com.yincongyang.base.concept;

/**
 * java 按值传递和按引用传递问题
 * 
 * 对象传递方式：
 * - 通过“=”进行赋值操作
 * - 通过方法参数传递
 * 
 * 传递类型：
 * - 按值传递：传递的是值本身，不会出现两个字面量实际指向同一个对象的问题。
 * - 按引用传递(句柄||指针)：传递是的对象的地址，会出现多个字面量指向同一个对象。
 * - java中除了基本数据类型的传递外，其余均为对象，所以都是按引用传递。
 * 
 * 别名问题：
 * - 按引用传递带来的问题，当a和b同时指向同一个对象时，若修改了b指向对象本身的值，则也会修改a的值。这种场景有可能带来意料之外的效果。
 * - 解决方案：使用Object.clone()。//使用较繁琐，尽量不要使用
 * - 创建不可变类(即所有内部成员变量均为private且不提供方法对其进行修改)及其对应修改类(类似String，StringBuffer)。
 * 
 * @author yincongyang
 *
 */
public class ObjectPassTest {
	private String value;

	public ObjectPassTest(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "[value=" + value + "]";
	}

	public static void method1(ObjectPassTest o1, int i) {
		o1.setValue("5555");
		++i;
		System.out.printf("将Object修改为： %s int修改为： %d\n", o1, i);
	}

	public static void main(String[] args) {
		ObjectPassTest o1 = new ObjectPassTest("1234");
		ObjectPassTest o2 = o1;//o2和o1是按照引用传递，故二者指向同一个对象
		o1.setValue("4444");//此时改变o1指向对象的值，则o2也会跟着改变
		System.out.println(o2);

		ObjectPassTest o3 = new ObjectPassTest("1234");
		int i = 1;
		System.out.println("Object初始化为： " + o3 + "int初始化为： " + i);
		ObjectPassTest.method1(o3, i);
		System.out.println("Object修改后为： " + o3 + "int修改后为： " + i);
	}
}
