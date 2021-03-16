package com.geek.entity;

import lombok.Setter;

/**
 * @Author Robert
 * @create 2021/3/16 19:54
 * @Version 1.0
 * @Description:
 */

@Setter
public class Course {
    private String cname;

    @Override
    public String toString() {
        return "Course{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
