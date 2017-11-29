package com.yincongyang.spring.controller;

import com.yincongyang.spring.BaseJunit4Test;
import com.yincongyang.spring.util.xml.JaxbUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * springMVC 测试用例
 * Created by yincongyang on 17/11/13.
 */
public class XmlControllerTest extends BaseJunit4Test {
    private static Logger logger = LoggerFactory.getLogger(XmlControllerTest.class);


    @Test
    public void getUser() throws Exception {
        logger.info("XmlControllerTest getUser is running");

        this.mockMvc.perform(get("/xml/get")).andExpect(status().isOk()).andExpect(content().contentType
                ("application/xml")).andExpect(xpath("/user/name", new HashMap<String, String>()).string("张三"));
    }

    @Test
    public void postUser() throws Exception {
        logger.info("XmlControllerTest postUser is running");

        User user = new User(10, "王五", "男");
        String content = JaxbUtils.toXml(user);

        logger.info("content:{}", content);

        this.mockMvc.perform(post("/xml/post").contentType(MediaType.APPLICATION_XML_VALUE).content(content))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE)).andExpect(xpath
                ("/user/sex", new HashMap<String, String>()).string("男"));
    }

    @Test
    public void postHouse() throws Exception {
        logger.info("XmlControllerTest postHouse is running");

        User user = new User(30, "王五", "男");
        House house = new House(user,"南京");
        String content = JaxbUtils.toXml(house);

        logger.info("content:{}", content);

        this.mockMvc.perform(post("/xml/postHouse").contentType(MediaType.APPLICATION_XML_VALUE).content(content))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE)).andExpect(xpath
                ("/house/user/sex", new HashMap<String, String>()).string("男"));
    }
}
