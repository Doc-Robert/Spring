package com.geek.entity;

import lombok.Data;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Robert
 * @create 2021/3/16 19:08
 * @Version 1.0
 * @Description:
 */

@Setter
public class CollType {
    //1 数组类型属性
    private String[] courses;
    //2 list集合
    private List<String> lists;
    //3 map集合
    private Map<String,String> maps;
    //4 set集合
    private Set<String> sets;
    //list 集合中放置对象
    private List<Course> courseList;

    public void test(){
        System.out.println(Arrays.toString(courses));
        System.out.println(lists);
        System.out.println(maps);
        System.out.println(sets);
        System.out.println(courseList.toString());
    }
}
