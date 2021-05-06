package com.geek.autowire;

/**
 * @Author Robert
 * @create 2021/4/23 9:01
 * @Version 1.0
 * @Description:
 */
public class Emp {

    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }

    public void test(){
        System.out.println(dept);
    }
}
