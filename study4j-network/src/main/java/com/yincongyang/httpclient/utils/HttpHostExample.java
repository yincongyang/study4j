package com.yincongyang.httpclient.utils;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;

/**
 * HttpHost 常用来设置代理服务器地址
 *
 * Created by yincongyang on 17/10/9.
 */
public class HttpHostExample {

    public static void main(String[] args) {
        //初始化代理服务器地址
        HttpHost proxy = new HttpHost("127.0.0.1",8080,"http");
        //使用RequestConfig对象初始化网络相关配置选项
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
    }
}
