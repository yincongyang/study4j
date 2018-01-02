package com.yincongyang.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 返回xml格式的controller
 * Created by yincongyang on 17/11/13.
 */
@Controller
@RequestMapping("/xml")
public class XmlController {
    private static Logger logger = LoggerFactory.getLogger(XmlController.class);

    @RequestMapping(path = "/get",method = RequestMethod.GET,produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User get(){
        return new User(21,"张三","男");
    }

    /**
     * @ResponseBody
     *    通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
     * 使用时机：
     *    返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用；
     *
     * @param user
     * @return
     */
    @RequestMapping(path = "/post",method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User post(@RequestBody User user){
        logger.info(user.toString());
        return user;
    }

    /**
     * @RequestBody:
     * 该注解常用来处理Content-Type不是application/x-www-form-urlencoded编码的内容，例如application/json, application/xml等；
     * 它是通过使用HandlerAdapter 配置的HttpMessageConverters来解析post data body，然后绑定到相应的bean上的。
     *
     * @param user
     * @return
     */
    @RequestMapping(path = "/post1",method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public User post1(@RequestBody User user){
        logger.info(user.toString());
        return user;
    }

    /**
     * @RequestMapping
     *     path:路径
     *     value=path
     *     method:指定请求方式，使用RequestMethod.*
     *     produces:指定响应头中的ContentType
     *     consumes:指定请求头中的ContentType
     *     params:指定Request中的参数名称，用于将同一URL映射至不同方法.eg：params="name=k"
     * @param house
     * @return
     */
    @RequestMapping(path = "/postHouse",method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public House postHouse(@RequestBody House house){
        logger.info(house.toString());
        return house;
    }

}
