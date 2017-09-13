package com.yincongyang.base.concept;

/**
 * javabean是一种约定俗成的规范
 * 
 * 目的主要是方便业界主要的工具库或者标准库的操作和向后升级：如持久层的映射，JSON，XML字段的转化等
 * 
 * 主要规范：
 *  - 所有的属性是私有的（通过getters/setters处理属性）
 *  - 一个公有的无参数的构造器
 *  - 实现了序列化（Serializable）
 *  
 * 主要名称解释：
 * 	- PO(persistence object):用于持久化时(例如保存到数据库或者缓存);
 *  - VO(value object):用于前端展示使用(例如放置到JSP中解析或者给前端传递数据)
 *  - DTO(data transfer object):用于接口互相调用返回,数据传输(例如很多接口调用返回值或消息队列内容);
 *  - POJO：普通java对象
 *  - BO(business object) 业务对象
 * 
 * 以下是一个javabean的统称
 * @author yincongyang
 *
 */
public class JavaBeanTest {
	private String name;
	private int age;
	private String school;

	public JavaBeanTest() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}
