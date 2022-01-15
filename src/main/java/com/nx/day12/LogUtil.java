package com.nx.day12;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: wep
 * @Since: 2021/5/16 12:42
 */
@Component
@Aspect
public class LogUtil {
    /**
     * 我们在xml中已经使⽤了通⽤切⼊点表达式，供多个切⾯使⽤，那么在注解中如何使⽤呢？
     * 第⼀步：编写⼀个⽅法
     * 第⼆步：在⽅法使⽤@Pointcut注解
     * 第三步：给注解的value属性提供切⼊点表达式
     * 细节：
     * 1.在引⽤切⼊点表达式时，必须是⽅法名+()，例如"pointcut()"。
     * 2.在当前切⾯中使⽤，可以直接写⽅法名。在其他切⾯中使⽤必须是全限定⽅法名。
     */
    @Pointcut("execution(* com.nx.day12.service.impl.*.*(..))")
    public void pointcut(){}
    @Before("pointcut()")
    public void beforeMethod(JoinPoint jp){
        Object[] args = jp.getArgs();
//        System.out.println("前置通知：beforeMethod，参数是："+
//                Arrays.toString(args));
    }
    @AfterReturning(value = "pointcut()",returning = "rtValue")
    public void afterReturningMethod(Object rtValue){
//        System.out.println("返回通知：afterReturningMethod，返回值是："+rtValue);
    }

    @After("pointcut()")
    public void afterMethod(){
//        System.out.println("后置通知：afterMethod");
    }
    /**
     * 环绕通知
     * @param pjp
     * @return
     */
    //  @Around("pointcut()")
    public Object aroundMethod(ProceedingJoinPoint pjp){
        //定义返回值
        Object rtValue = null;
        try{
            //前置通知
            System.out.println("前置通知");
            //1.获取参数
            Object[] args = pjp.getArgs();
            //2.执⾏切⼊点⽅法
            rtValue = pjp.proceed(args);
            //后置通知
            System.out.println("后置通知");
        }catch (Throwable t){
            //异常通知
            System.out.println("异常通知");
            t.printStackTrace();
        }finally {
            //最终通知
            System.out.println("最终通知");
        }
        return rtValue;
    }
}
