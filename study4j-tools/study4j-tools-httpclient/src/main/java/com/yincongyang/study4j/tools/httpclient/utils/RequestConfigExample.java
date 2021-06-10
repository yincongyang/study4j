package com.yincongyang.study4j.tools.httpclient.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

/**
 * RequestConfig 对象是用来配置http请求中网络相关配置，如超时时间，代理服务器地址等
 *
 * Created by yincongyang on 17/10/9.
 */
public class RequestConfigExample {
    public static void main(String[] args) {
        //创建HttpRequest的配置项（主要是一些网络通讯方面的设置：如超时时间，代理等）
        RequestConfig config = RequestConfig.custom()
                //.setProxy(proxy)
                .setConnectTimeout(100)//连接超时时间，单位毫秒
                .setSocketTimeout(1000)//请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
                .setConnectionRequestTimeout(5000)//设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
                .build();

        HttpGet httpget = new HttpGet();

        httpget.setConfig(config);
    }
}
