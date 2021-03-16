package com.geek.entity;

/**
 * @Author Robert
 * @create 2021/3/16 15:44
 * @Version 1.0
 * @Description:
 */
public class Emp {

    private String ename;

    private String gender;


    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }



    public void add(){
        System.out.println(ename+','+gender+','+dept.toString());
    }
}
