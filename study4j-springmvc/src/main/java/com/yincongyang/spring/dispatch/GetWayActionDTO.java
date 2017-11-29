package com.yincongyang.spring.dispatch;

import java.lang.reflect.Method;

/**
 *
 * Created by yincongyang on 17/11/17.
 */
public class GetWayActionDTO {

    private Class<?> object;
    private Method method;

    public GetWayActionDTO() {
    }

    public GetWayActionDTO(Class<?> object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Class<?> getObject() {
        return object;
    }

    public void setObject(Class<?> object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
