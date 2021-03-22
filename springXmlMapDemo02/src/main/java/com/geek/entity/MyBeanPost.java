package com.geek.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author Robert
 * @create 2021/3/22 10:04
 * @Version 1.0
 * @Description:
 */
public class MyBeanPost implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }

//    @Override
//    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
//        System.out.println("初始化前执行的方法");
//        return null;
//    }
//    @Override
//    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
//        System.out.println("初始化后执行的方法");
//        return null;
//    }
}
