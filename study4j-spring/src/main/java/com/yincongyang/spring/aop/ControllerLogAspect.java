package com.yincongyang.spring.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * 定义切面支持类
 * Created by yincongyang on 17/11/27.
 */
@Component
@Aspect
public class ControllerLogAspect {
    private static Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    /**
     * 定义一个切入点(集合)：
     * 连接点的集合，Spring支持perl5正则表达式和AspectJ切入点模式，
     * Spring默认使用AspectJ语法，在AOP中表示为“在哪里干的集合”
     */
    @Pointcut("execution(* com.yincongyang.spring.controller..*.*(..))")
    private void anyMethod(){}

    /**
     * 前置通知：
     * 在切入点选择的连接点处的方法之前执行的通知，该通知不影响正常程序执行流程
     * (除非该通知抛出异常，该异常将中断当前方法链的执行而返回)
     * @param name
     */
    @Before(value =  "anyMethod()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("前置通知");
        logger.info("目标方法名为:" + joinPoint.getSignature().getName());
        logger.info("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        logger.info("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        logger.info("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            logger.info("第" + (i+1) + "个参数为:" + args[i]);
        }
        logger.info("被代理的对象:" + joinPoint.getTarget());
        logger.info("代理对象自己:" + joinPoint.getThis());
    }

    /**
     * 后置返回通知：
     * 在切入点选择的连接点处的方法正常执行完毕时执行的通知，必须是连接点处的方法没抛出任何异常正常返回时才调用后置通知。
     */
    @AfterReturning("anyMethod()")
    public void doAfter(){
        logger.info("后置通知");
    }

    /**
     * 后置异常通知：
     * 在切入点选择的连接点处的方法抛出异常返回时执行的通知，必须是连接点处的方法抛出任何异常返回时才调用异常通知
     */
    @AfterThrowing("anyMethod()")
    public void doAfterThrow(){
        logger.info("异常通知");
    }

    /**
     * 后置最终通知：
     * 在切入点选择的连接点处的方法返回时执行的通知，不管抛没抛出异常都执行，类似于Java中的finally块
     */
    @After("anyMethod()")
    public void after(){
        logger.info("最终通知");
    }

    /**
     * 绕着在切入点选择的连接点处的方法所执行的通知，环绕通知可以在方法调用之前和之后自定义任何行为，
     * 并且可以决定是否执行连接点处的方法、替换返回值、抛出异常等等
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("anyMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{
        logger.info("进入环绕通知：准备执行方法");
        //获取传入目标方法的参数
        Object[] args = pjp.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            logger.info("第" + (i+1) + "个参数为:" + args[i]);
//        }
        logger.info("请求报文：{}",JSON.toJSONString(args[0]));
        Object object = pjp.proceed();//执行该方法
        logger.info("返回报文：{}",JSON.toJSONString(object));
        logger.info("退出环绕通知：方法执行完毕");
        return object;
    }
}
