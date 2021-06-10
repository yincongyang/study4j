package com.ycy.study4j.springboot.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yincongyang on 17/11/17.
 */
@Controller
@RequestMapping("/paramsMapping")
public class ParamsController {

  @RequestMapping(method = RequestMethod.GET, params = "name=map1")
  @ResponseBody
  public String map1() {
    return "map1";
  }

  @RequestMapping(method = RequestMethod.GET, params = "name=map2")
  @ResponseBody
  public String map2() {
    return "map2";
  }
}
