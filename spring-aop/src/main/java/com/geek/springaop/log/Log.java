package com.geek.springaop.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author Robert
 * @create 2021/5/9 15:01
 * @Version 1.0
 * @Description: Before log
 */
public class Log implements MethodBeforeAdvice {

    // method :要执行的目标对象的方法
    // args： 参数
    // target：目标对象
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"方法，被执行了");

    }

}
