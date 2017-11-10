package com.yincongyang.base.proxy.jdkdynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 * Created by yincongyang on 17/11/10.
 */
public class AccountProxyFactory implements InvocationHandler {

    private Object target;

    public Object bind(Object target){
        // 这里使用的是Jdk的动态代理，其必须要绑定接口，在我们的业务实现中有可能是没有基于接口是实现的。所以说这个缺陷cglib弥补了。
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        boolean objFlag = method.getDeclaringClass().getName().equals("java.lang.Object");

        Object result = null;
        if(!objFlag)
            System.out.println("代理before");
        result = method.invoke(this.target, args);
        if(!objFlag)
            System.out.println("代理after");
        return result;
    }


}
