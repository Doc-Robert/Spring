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

    private String author;


    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void add(){
        System.out.println(name + ','+ author);
    }


    public void setBeanName(String s) {

    }


}
