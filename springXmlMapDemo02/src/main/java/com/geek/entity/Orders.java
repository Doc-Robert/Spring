package com.geek.entity;

/**
 * @Author Robert
 * @create 2021/3/22 9:43
 * @Version 1.0
 * @Description:
 */
public class Orders {

    public Orders() {
        System.out.println("1. 执行无参构造器创建bean实例");
    }
    private String oname;

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("2. 调用set 方法设置属性值");
    }
    public void initMethod(){
        System.out.println("3. 执行初始化方法");
    }
    public void DestroyMethod(){
        System.out.println("5. 调用销毁方法");
    }
}
