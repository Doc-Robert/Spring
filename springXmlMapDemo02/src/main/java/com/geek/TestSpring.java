package com.geek;

import com.geek.entity.CollType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @Author Robert
 * @create 2021/3/15 9:54
 * @Version 1.0
 * @Description:
 */
public class TestSpring {
    public static void main(String[] args) {

        //获取类路径下xml配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("beanType.xml");
        CollType collType = context.getBean("CollType", CollType.class);
        collType.test();
    }
}
