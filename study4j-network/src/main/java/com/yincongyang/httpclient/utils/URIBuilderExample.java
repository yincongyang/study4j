package com.yincongyang.httpclient.utils;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * URI生成器
 *
 * Created by yincongyang on 17/10/9.
 */
public class URIBuilderExample {

    @Test
    public void URIBuilderTest() throws URISyntaxException {
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameters(new BasicNameValuePair("name1", "value1"), new BasicNameValuePair("name2", "value2"))
                .addParameter("add3", "value2")
                .setParameter("name", "value")
                .build();
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());
    }
}
