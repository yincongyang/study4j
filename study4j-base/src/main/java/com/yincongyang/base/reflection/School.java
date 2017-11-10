package com.yincongyang.base.reflection;


import com.yincongyang.base.annotation.HelloAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yincongyang on 17/11/9.
 */
public class School {
    private String name;
    private String location;

    public School(){
        this.name = "学校";
        this.location="南京";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        School school = new School();
        Class<?> clazz = School.class;

        System.out.println("打印属性");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field:declaredFields) {
            field.setAccessible(true);//禁止java权限检查
            System.out.println(field.getName() + " : " + field.get(school));
        }

        System.out.println("打印方法");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method:declaredMethods) {
            method.setAccessible(true);//禁止java权限检查

            if(method.getName().startsWith("get")){
                System.out.println(method.getName() + ":" + method.invoke(school));
            }else if(method.getName().startsWith("set")){
                method.invoke(school,"test");
            }else{
                System.out.println(method.getName());
            }

        }

        System.out.println(school.getName() + ":" + school.getLocation());
    }
}
