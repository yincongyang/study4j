package com.yincongyang.spring.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


/**
 * @Valid springMVC自动验证框架
 *
 * Created by yincongyang on 17/11/16.
 */
@Controller
@RequestMapping("/valid")
@Validated
public class ValidController {

    /**
     * @Valid:
     *
     *
     * @param user
     * @return
     */
    @RequestMapping(path = "/post",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User post(@RequestBody @Valid User user){
        return user;
    }
}
