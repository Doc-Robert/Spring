package com.geek.springaop.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Author Robert
 * @create 2021/5/9 15:09
 * @Version 1.0
 * @Description: after log
 */
public class AfterLog implements AfterReturningAdvice {

    //returnValue 执行后返回值

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法，返回了结果为："+returnValue);
    }
}
