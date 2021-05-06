package com.geek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author Robert
 * @create 2021/4/26 15:41
 * @Version 1.0
 * @Description:
 */


//在注解里面value属性值可以忽略不写
//默认值是类名称，首字母小写
@Component
public class UserService {

//    @Autowired
//    @Resource

//    @Override
    public void add() {
        System.out.println("service add");
    }
}
