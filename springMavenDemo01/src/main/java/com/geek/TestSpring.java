package com.geek;

import com.geek.entity.Order;
import com.geek.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author Robert
 * @create 2021/3/15 9:54
 * @Version 1.0
 * @Description:
 */
public class TestSpring {
    public static void main(String[] args) {
        //1.加载spring配置文件
        //获取类路径下xml配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");

        //2.获取配置创建的对象
        //注意id对应
        User user = context.getBean("user", User.class);
        Order orders = context.getBean("order", Order.class);
//        System.out.println(user);
        user.add();
//        orders.orderTest();
    }


}
