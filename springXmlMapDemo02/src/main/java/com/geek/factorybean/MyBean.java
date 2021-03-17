package com.geek.factorybean;

import com.geek.entity.Course;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author Robert
 * @create 2021/3/17 9:51
 * @Version 1.0
 * @Description:
 */
public class MyBean implements FactoryBean<Course> {
    //返回bean的实例
    //定义FactoryBean<Course>后 返回的是Course 对象，而不是myBean对象
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setCname("abcccc");
        return course;
    }
    //返回bean的类型
    public Class<?> getObjectType() {
        return null;
    }
    //bean 是否是一个单例
    public boolean isSingleton() {
        return false;
    }
}
