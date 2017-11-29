package com.yincongyang.spring.dispatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一转发中心
 * Created by yincongyang on 17/11/17.
 */
//@Controller
@RequestMapping("/dispatch")
public class DispatchController {

    @RequestMapping(path = "mapping", method = RequestMethod.POST)
    @ResponseBody
    public String dispatch(HttpServletRequest request, HttpServletResponse response){
        return null;
    }

}
