package com.geek.springaop.service;

/**
 * @Author Robert
 * @create 2021/5/9 14:58
 * @Version 1.0
 * @Description:
 */


public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加用户");
    }

    @Override
    public void update() {
        System.out.println("修改用户");

    }

    @Override
    public void delete() {
        System.out.println("删除用户");

    }

    @Override
    public void select() {
        System.out.println("查询用户");

    }
}
