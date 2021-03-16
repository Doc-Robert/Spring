package com.geek.entity;

/**
 * @Author Robert
 * @create 2021/3/15 20:20
 * @Version 1.0
 * @Description: orders
 */
public class Order {

    private String oName;
    private String address;

    public Order(String oName, String address) {
        this.oName = oName;
        this.address = address;
    }
    public void orderTest(){
        System.out.println(oName+"::"+address);
    }
}
