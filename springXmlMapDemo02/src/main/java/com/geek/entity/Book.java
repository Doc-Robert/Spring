package com.geek.entity;

import lombok.Setter;

import java.util.List;

/**
 * @Author Robert
 * @create 2021/3/16 20:13
 * @Version 1.0
 * @Description:
 */
@Setter
public class Book {
    private List<String> list;

    public void test(){
        System.out.println(list);
    }
}
