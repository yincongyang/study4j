package com.yincongyang.base.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * Created by yincongyang on 17/11/9.
 */

public class AnnotationTest {

    @Test
    public void testHelloAnnotation() throws IllegalAccessException {
        User ob = new User();

        //利用反射获取class
        Class<?> clazz = User.class;

//        System.out.println("获得类的public类型的属性");
//        Field[] fields = clazz.getFields();
//        for (Field field:fields) {
//            System.out.println(field.getName()+"= "+field.get(ob));
//        }

        System.out.println("获得类上的注解内容，并打印");
        HelloAnnotation c = clazz.getAnnotation(HelloAnnotation.class);
        System.out.println(c.key()+": "+c.value());

        //field.get(ob) 不能访问对象的private数据成员
        System.out.println("获得类的所有属性上的注解内容，并打印");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field:declaredFields) {
            System.out.print(field.getName());
            if(field.isAnnotationPresent(HelloAnnotation.class)){
                HelloAnnotation a = field.getAnnotation(HelloAnnotation.class);
                System.out.println(a.key()+": "+a.value());
            }else{
                System.out.println("未声明注解");
            }

        }

        System.out.println("获得类的所有方法上的注解内容，并打印");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method:declaredMethods) {
            System.out.print(method.getName());
            HelloAnnotation a = method.getAnnotation(HelloAnnotation.class);
            System.out.println(a.key()+": "+a.value());
        }

//        System.out.println("获得类的public方法");
//        Method[] methods = clazz.getMethods();
//        for (Method method:methods) {
//            System.out.println(method.getName());
//        }


        //获取AnnotationTest类上的注解对象
//        HelloAnnotation annotation = User.class.getAnnotation(HelloAnnotation.class);
//        //调用注解对象的say方法，并打印到控制台
//        System.out.println(annotation.say());
    }

}
