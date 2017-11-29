package com.yincongyang.spring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.yincongyang.spring.BaseJunit4Test;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

/**
 * springMVC 测试用例
 * Created by yincongyang on 17/11/13.
 */
public class JsonControllerTest extends BaseJunit4Test {

    private static Logger logger = LoggerFactory.getLogger(JsonControllerTest.class);

    @Test
    public void getUser() throws Exception {
        logger.info("JsonControllerTest getUser is running");
        this.mockMvc.perform(get("/json/get").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("李四"));
    }

    @Test
    public void postUser() throws Exception {
        logger.info("JsonControllerTest postUser is running");
        this.mockMvc.perform(post("/json/post/haha").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("haha"));
    }

}
