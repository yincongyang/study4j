package com.yincongyang.spring.controller;

import javax.validation.constraints.Max;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.Transient;


/**
 * javaBean
 * Created by yincongyang on 17/11/13.
 */
@XmlRootElement
public class User {

    @Max(value = 30L,message = "年龄最大不能超过30")
    private int age;
    private String name;
    private String sex;

    public User() {
    }

    public User(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    /**
     * @Transient 注解可以在返回json时排除掉该字段
     * @XmlTransient 注解可以在返回xml时排除该字段
     * @return
     */
    //@Transient
    //@XmlTransient
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" + "age=" + age + ", name='" + name + '\'' + ", sex='" + sex + '\'' + '}';
    }
}
