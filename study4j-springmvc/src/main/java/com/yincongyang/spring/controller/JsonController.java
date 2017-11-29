package com.yincongyang.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 返回json
 * Created by yincongyang on 17/11/10.
 */
//@RequestMapping(path = "/json")
//@RequestMapping("json")
@RequestMapping(value = "/json")
@Controller
public class JsonController {
    private static Logger logger = LoggerFactory.getLogger(JsonController.class);


    @RequestMapping(path = "/get",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User get(){
        return new User(20,"李四","F");
    }

    /**
     * @PathVariable :其存放了URI模板变量中的值,
     *
     * @param name
     * @return
     */
    @RequestMapping(path = "/post/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User post(@PathVariable String name, Model model){
        logger.info("name : {}",name);
        return new User(20,name,"男");
    }

}
