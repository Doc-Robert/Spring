package com.geek.springaop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @Author Robert
 * @create 2021/5/9 16:59
 * @Version 1.0
 * @Description:
 */
@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.geek.springaop.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("===方法执行前===");
    }

    @After("execution(* com.geek.springaop.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("===方法执行后===");
    }

    //在环绕增强中，可以给定一个参数 代表我们要获取处理切入的点
    @Around("execution(* com.geek.springaop.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕前");

        Signature signature = joinPoint.getSignature();//获得签名  执行的该方法
        System.out.println(signature);

        joinPoint.proceed();//执行方法

        System.out.println("环绕后");
    }
}
