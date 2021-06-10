package com.ycy.study4j.springboot.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @MatrixVariable: 矩阵变量可以在任何路径段落中出现，每对矩阵变量之间使用一个分号“;”隔开。 比如这样的URI："/cars;color=red;year=2012"。
 * 多个值可以用逗号隔开"color=red,green,blue"，或者重复变量名多次"color=red;color=green;color=blue"
 * <p>
 * 如果要允许矩阵变量的使用，你必须把RequestMappingHandlerMapping类的removeSemicolonContent属性设置为false。该值默认是true的。 设置方法：
 * <mvc:annotation-driven enable-matrix-variables="true"/>
 * <p>
 * Created by yincongyang on 17/11/16.
 */
@Controller
@RequestMapping("/matrix")
public class MatrixController {

  private static final Logger logger = LoggerFactory.getLogger(MatrixController.class);

  //GET /pets/42;q=11;r=22
  @RequestMapping(path = "/find/{petId}", method = RequestMethod.GET)
  @ResponseBody
  public String find(@PathVariable String petId, @MatrixVariable int q) {
    logger.info("petId:{},q:{}", petId, q);
    // petId == 42
    // q == 11
    return petId + q;
  }

  // GET /owners/42;q=11/pets/21;q=22
  @RequestMapping(path = "/owners/{ownerId}/pets/{petId}", method = RequestMethod.GET)
  @ResponseBody
  public String find1(
      @MatrixVariable(name = "q", pathVar = "ownerId") int q1,
      @MatrixVariable(name = "q", pathVar = "petId") int q2) {

    // q1 == 11
    // q2 == 22
    return String.valueOf(q1 + q2);
  }
}
