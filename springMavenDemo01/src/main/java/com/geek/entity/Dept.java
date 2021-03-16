package com.geek.entity;

/**
 * @Author Robert
 * @create 2021/3/16 15:48
 * @Version 1.0
 * @Description:
 */
public class Dept {
    private String dname;

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                '}';
    }
}
