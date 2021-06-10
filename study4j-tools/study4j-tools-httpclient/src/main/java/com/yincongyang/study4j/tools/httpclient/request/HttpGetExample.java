package com.yincongyang.study4j.tools.httpclient.request;

import com.yincongyang.study4j.tools.httpclient.TESTURL;
import com.yincongyang.study4j.tools.httpclient.response.StringResponseHandle;
import java.io.IOException;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

/**
 * HttpGet 请求测试
 * <p>
 * HttpGet 请求参数直接使用addParameter方法即可自动添加。也可以使用BasicNameValuePair对象初始化并添加。
 * <p>
 * Created by yincongyang on 17/10/9.
 */
@Slf4j
public class HttpGetExample {

  @Test
  public void HttpGetTest() throws IOException, URISyntaxException {
    long startTime = System.currentTimeMillis();
    //创建默认HttpClient
    CloseableHttpClient client = HttpClients.createDefault();
    try {
      /**
       * 创建HttpRequest(HttpGet，HttpHead， HttpPost，HttpPut，HttpDelete，HttpTrace和HttpOptions),
       * 常用HttpGet，HttpPost
       *
       * HttpGet要想增加参数，则需要使用URIBuilder
       */
      HttpGet httpGet = new HttpGet(new URIBuilder(TESTURL.BAIDU)
          .setParameters(new BasicNameValuePair("name1", "value1"),
              new BasicNameValuePair("name2", "value2"))
          .addParameter("add3", "value2")
          .setParameter("name", "value")
          .build());

      //创建代理(代理也是一个URL地址)
//            HttpHost proxy = new HttpHost("127.0.0.1", 8888);
//            HttpHost proxy = new HttpHost("172.31.19.108", 3128);

      //创建HttpRequest的配置项（主要是一些网络通讯方面的设置：如超时时间，代理等）
      RequestConfig config = RequestConfig.custom()
//                    .setProxy(proxy)
          .setConnectTimeout(100)//连接超时时间，单位毫秒
          .setSocketTimeout(1000)//请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用
          .setConnectionRequestTimeout(
              5000)//设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
          .build();
      //设置HttpRequest的配置项
      httpGet.setConfig(config);

      //发送http请求，并指定返回报文处理器，也可以不指定，直接返回HttpResponse。
      //推荐使用ResponseHandle
      log.info("返回报文：{}", client.execute(httpGet, StringResponseHandle.getInstance()));

      long endTime = System.currentTimeMillis();

      log.info("请求时间为：{}ms", endTime - startTime);
    } finally {
      //关闭连接
      client.close();
    }
  }
}
