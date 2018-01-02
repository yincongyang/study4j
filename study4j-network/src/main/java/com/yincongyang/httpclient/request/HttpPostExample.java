package com.yincongyang.httpclient.request;

import com.yincongyang.httpclient.TESTURL;
import com.yincongyang.httpclient.response.StringResponseHandle;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * HTTP POST请求测试
 *
 * post请求使用HttpPost，请求中需要使用HttpEntity
 * 代理实体HttpHost对象
 *
 * Created by yincongyang on 17/10/9.
 */
public class HttpPostExample {
    private final static Logger logger = LoggerFactory.getLogger(HttpPostExample.class);

    /**
     * POST请求测试
     * post请求使用HttpPost，请求中需要使用HttpEntity
     *
     * @throws IOException
     */
    @Test
    public void HttpPostTest() throws IOException {
        long startTime = System.currentTimeMillis();

        //创建默认HttpClient
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            /**
             * 创建HttpRequest(HttpGet，HttpHead， HttpPost，HttpPut，HttpDelete，HttpTrace和HttpOptions),
             * 常用HttpGet，HttpPost
             */
            HttpPost httpPost = new HttpPost(TESTURL.BAIDU);

            //初始化并设置请求报文实体
            StringEntity entity = new StringEntity("this is request", ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            //创建代理(代理也是一个URL地址)
            HttpHost proxy = new HttpHost("127.0.0.1", 8888);
            //HttpHost proxy = new HttpHost("172.31.19.108", 3128);

            //创建HttpRequest的配置项（主要是一些网络通讯方面的设置：如超时时间，代理等）
            RequestConfig config = RequestConfig.custom()
                    //.setProxy(proxy)
                    .setConnectTimeout(100)//连接超时时间，单位毫秒
                    .setSocketTimeout(1000)//请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
                    .setConnectionRequestTimeout(5000)//设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
                    .build();
            //设置HttpRequest的配置项
            httpPost.setConfig(config);

            //发送http请求，并指定返回报文处理器，也可以不指定，直接返回HttpResponse。
            //推荐使用ResponseHandle
            logger.info("返回报文：{}", client.execute(httpPost, StringResponseHandle.getInstance()));


            long endTime = System.currentTimeMillis();

            logger.info("请求时间为：{}ms", endTime - startTime);
        } finally {
            //关闭连接
            client.close();
        }
    }
}
