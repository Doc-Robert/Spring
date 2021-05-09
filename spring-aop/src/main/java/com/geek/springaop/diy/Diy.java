package com.geek.springaop.diy;

/**
 * @Author Robert
 * @create 2021/5/9 16:23
 * @Version 1.0
 * @Description:
 */
//自定义before after 方法
public class Diy {

    public void before(){
        System.out.println("===方法执行前===");
    }

    public void after(){
        System.out.println("===方法执行后===");
    }
}
