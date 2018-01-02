package com.yincongyang.base.annotation;

/**
 *
 * Created by yincongyang on 17/11/9.
 */
@HelloAnnotation(value = "classValue",key = "classKey")
public class User {
    public User(){
        this.privateField = "p";
        this.publicFiled = "v";
    }


    @HelloAnnotation(value = "privateFieldValue",key = "privateFieldKey")
    private String privateField;

    @HelloAnnotation(value = "publicFiledValue",key = "publicFieldKey")
    public String publicFiled;

    public String otherFiled;

    @HelloAnnotation(value = "methodValue",key = "methodKey")
    public void method(){}

    @HelloAnnotation(value = "methodValue",key = "methodKey")
    private void method1(){}

}
