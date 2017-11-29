package com.yincongyang.spring.controller;

import com.alibaba.fastjson.JSON;
import com.yincongyang.spring.BaseJunit4Test;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * springMVC 测试用例
 * Created by yincongyang on 17/11/13.
 */
public class ValidControllerTest extends BaseJunit4Test {

    private static Logger logger = LoggerFactory.getLogger(ValidControllerTest.class);

    @Test
    public void postTest() throws Exception {

        User user = new User(50, "王五", "男");
        String content = JSON.toJSONString(user);

        logger.info("content:{}", content);

        this.mockMvc.perform(post("/valid/post").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(content))
                .andExpect(content().string("4211"));
    }

}
