package com.yincongyang.httpclient.request;

import com.yincongyang.httpclient.TESTURL;
import com.yincongyang.httpclient.response.StringResponseHandle;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * RequestBuilder对象可以构建任意类型的请求，如HttpGet、HttpPost等
 *
 * Created by yincongyang on 17/10/9.
 */
public class RequestBuilderExample {

    private final static Logger logger = LoggerFactory.getLogger(RequestBuilderExample.class);

    /**
     * RequestBuilder对象可以构建任意类型的请求，如HttpGet、HttpPost等
     *
     * @throws IOException
     */
    @Test
    public void requestBuilderTest() throws IOException {
        //初始化并设置请求报文实体
        StringEntity entity = new StringEntity("this is request", ContentType.APPLICATION_JSON);
        //创建HttpRequest的配置项（主要是一些网络通讯方面的设置：如超时时间，代理等）
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(100)//连接超时时间，单位毫秒
                .setSocketTimeout(1000)//请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
                .setConnectionRequestTimeout(5000)//设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
                .build();
        //创建HttpPost请求
        HttpUriRequest request = RequestBuilder.post()
                .setUri(TESTURL.TXZF_TFB_ORDER)
                .setEntity(entity)
                .setConfig(config)
                .build();

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            logger.info("返回报文：{}", client.execute(request, StringResponseHandle.INSTANCE));
        } finally {
            client.close();
        }
    }
}
