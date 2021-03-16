package com.geek.service;

import com.geek.dao.UserDao;
import com.geek.dao.UserDaoImpl;

/**
 * @Author Robert
 * @create 2021/3/16 15:19
 * @Version 1.0
 * @Description:
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("service add ....");
        //创建userDao对象
//        UserDaoImpl userDao = new UserDaoImpl();
        userDao.update();
    }

}
