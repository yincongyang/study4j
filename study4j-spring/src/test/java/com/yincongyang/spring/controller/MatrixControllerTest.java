package com.yincongyang.spring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.yincongyang.spring.BaseJunit4Test;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

/**
 * springMVC 测试用例
 * Created by yincongyang on 17/11/13.
 */
public class MatrixControllerTest extends BaseJunit4Test {

    private static Logger logger = LoggerFactory.getLogger(MatrixControllerTest.class);

    @Test
    public void find() throws Exception {
        this.mockMvc.perform(get("/matrix/find/42;q=11;r=22"))
                .andExpect(status().isOk())
                .andExpect(content().string("4211"));
    }

    @Test
    public void find1() throws Exception {
        this.mockMvc.perform(get("/matrix/owners/42;q=11/pets/11;q=22"))
                .andExpect(status().isOk())
                .andExpect(content().string("33"));
    }

}
