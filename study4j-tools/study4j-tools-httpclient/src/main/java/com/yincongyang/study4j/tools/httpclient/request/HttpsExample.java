package com.yincongyang.study4j.tools.httpclient.request;

import com.yincongyang.study4j.tools.httpclient.TESTURL;
import com.yincongyang.study4j.tools.httpclient.response.StringResponseHandle;
import com.yincongyang.study4j.tools.httpclient.utils.TrustAllCASSLConnectionSocketFactory;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

/**
 * Created by yincongyang on 17/10/9.
 */
@Slf4j
public class HttpsExample {

  /**
   * 经过官方CA认证的网站，如支付宝，苏宁，京东等
   */
  @Test
  public void httpsWithDefaultCA() throws IOException {
    HttpUriRequest httpPost = RequestBuilder.post().setUri(TESTURL.SN_YFB_QUERY).build();

    CloseableHttpClient client = HttpClients.createDefault();

    try {
      log.info("返回报文：{}", client.execute(httpPost, StringResponseHandle.getInstance()));
    } finally {
      client.close();
    }
  }

  /**
   * 未经过官方CA认证的网站，如12306等
   */
  @Test
  public void httpsWithoutCA() {
    HttpUriRequest httpGet = RequestBuilder.get().setUri(TESTURL.NOT_SECURITY_SSL).build();

    CloseableHttpClient client = HttpClients
        .custom()
        .setSSLSocketFactory(TrustAllCASSLConnectionSocketFactory.INSTANCE)
        .build();

    try {
      log.info("返回报文：{}", client.execute(httpGet, StringResponseHandle.getInstance()));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 打印JRE支持的协议和加密套接字，可以在启动参数中设置
   */
  @Test
  public void test() {
    System.out.println(System.getProperty("https.protocols"));
    System.out.println(System.getProperty("https.cipherSuites"));
  }
}
