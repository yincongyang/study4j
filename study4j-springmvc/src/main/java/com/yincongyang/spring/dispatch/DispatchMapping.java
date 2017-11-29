package com.yincongyang.spring.dispatch;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 扫描
 * Created by yincongyang on 17/11/17.
 */
@Component
@Lazy(false)
public class DispatchMapping {
    private static Logger logger = LoggerFactory.getLogger(DispatchMapping.class);
    private static HashMap<String,GetWayActionDTO> map = Maps.newHashMap();

    public static HashMap<String,GetWayActionDTO> getMap(){
        return map;
    }

    @PostConstruct
    public void initDispatchMapping(){
        List<Class<?>> list = PackageScanner.getClasses("com.yincongyang.spring.dispatch.actions");

        for (Class<?> clazz : list) {
            GetWayController classAnnotation = clazz.getAnnotation(GetWayController.class);
            if (classAnnotation != null) {
                logger.info("正在扫描类：{}",clazz.getName());
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for (Method method:declaredMethods) {
                    GetWayMethod methodAnnotation = method.getAnnotation(GetWayMethod.class);
                    if(methodAnnotation != null){
                        logger.info("发现注解@GetWayController类:{}, 注解@GetWayMethod方法：{}",clazz.getName(),method.getName());
                        map.put(methodAnnotation.name(),new GetWayActionDTO(clazz,method));
                    }
                }
            }
        }
    }
}
