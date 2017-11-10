package com.yincongyang.httpclient.utils;

import com.yincongyang.httpclient.TESTURL;
import com.yincongyang.httpclient.response.StringResponseHandle;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自定义httpclient连接池
 * <p>
 * 调用方法：
 * CloseableHttpClient client = HttpClients
 * .custom()
 * .setConnectionManager(MyPoolingHttpClientConnectionManager.INSTANCE)
 * .build();
 * <p>
 * 无需调用client.close()
 * <p>
 * Created by yincongyang on 17/10/30.
 */
public class MyPoolingHttpClientConnectionManager {

    public static final PoolingHttpClientConnectionManager INSTANCE = MyPoolingHttpClientConnectionManager.build();

    private MyPoolingHttpClientConnectionManager() {
    }

    /**
     * 创建PoolingHttpClientConnectionManager对象
     *
     * @return
     */
    private static PoolingHttpClientConnectionManager build() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //将http连接池可用数量设置为200
        cm.setMaxTotal(300);
        //设置单个主机的最大连接数
        cm.setDefaultMaxPerRoute(30);
        return cm;
    }

    /**
     * 使用PoolingHttpClientConnectionManager无需调用client.close()，只需保证response.close()就可以了
     */
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    HttpUriRequest request = RequestBuilder.get(TESTURL.BAIDU).build();
                    CloseableHttpClient client = HttpClients.custom().setConnectionManager
                            (MyPoolingHttpClientConnectionManager.INSTANCE).build();
                    try {
                        client.execute(request, StringResponseHandle.INSTANCE);
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.shutdown();
    }
}
