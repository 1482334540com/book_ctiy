package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {
    //创建service层对象作为测试使用
    UserService userService =new UserServiceImpl();

    //测试用户名和密码是否正确
    @Test
    void queryUsernameAndPassword() {
        Boolean admin = userService.queryUsernameAndPassword("admin", "admin2");
        System.out.println(admin);


    }
    //测试用户名是否可用
    @Test
    void queryUsername() {
        Boolean admin = userService.queryUsername("admin2");
        System.out.println(admin);

    }
    //测试保存用户信息
    @Test
    void saveUserInformation() {

        User user = new User(null, "root", "root123", "root@qq.com", "1008622");
        System.out.println(user);
        Boolean flag = userService.saveUserInformation(user);
        System.out.println(flag);




    }
}