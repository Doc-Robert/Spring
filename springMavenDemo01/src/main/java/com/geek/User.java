package com.geek;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @Author Robert
 * @create 2021/3/15 9:49
 * @Version 1.0
 * @Description:
 */

public class User implements BeanNameAware {

    private String name;

    private String beanName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeanName() {
        return beanName;
    }
    public void setBeanName(String s) {
        this.beanName = beanName;
    }

    public void add(){
        System.out.println("add...");
    }
}
