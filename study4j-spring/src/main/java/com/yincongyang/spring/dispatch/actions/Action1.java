package com.yincongyang.spring.dispatch.actions;

import com.yincongyang.spring.dispatch.GetWayController;
import com.yincongyang.spring.dispatch.GetWayMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * Created by yincongyang on 17/11/17.
 */
@GetWayController
public class Action1 {
    @GetWayMethod(name = "action1")
    public String method(HttpServletRequest request, HttpServletResponse response){
        return "action1";
    }
}
