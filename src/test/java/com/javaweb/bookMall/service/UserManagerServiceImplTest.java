package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.service.Impl.UserManagerServiceImpl;
import com.javaweb.bookMall.service.UserManagerService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerServiceImplTest {
    //创建service层对线下
    UserManagerService userManagerService =new UserManagerServiceImpl();
    //查询所有用户数据
    @Test
    void queryUser() {
        List<User> users = userManagerService.queryUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
    //添加用户
    @Test
    void addUser() {
        User user = new User(null,"Test","Test123","Test@qq.com","1008633");
        Boolean flag = userManagerService.addUser(user);
        System.out.println(flag);

    }
    //删除用户
    @Test
    void delUser() {
        Boolean flag = userManagerService.delUser("5");
        System.out.println(flag);
    }
    //修改用户
    @Test
    void updateUser() {
        User user = new User(5,"Test1","Test123","Test@qq.com","1008633");
        Boolean flag = userManagerService.updateUser(user);
        System.out.println(flag);

    }
    //按照用户名模糊查询
    @Test
    void likeUser() {
        List<User> userList = userManagerService.likeUser("root");
        System.out.println(userList);
    }
}

