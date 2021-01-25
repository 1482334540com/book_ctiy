package com.javaweb.bookMall.service.Impl;

import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.dao.Impl.UserDaoImpl;
import com.javaweb.bookMall.dao.UserDao;
import com.javaweb.bookMall.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    //创建 dao层对象
    UserDao userDao = new UserDaoImpl();
    /**
     * 验证用户名和密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果正确返回一个 true 错误返回1一个false
     */
    @Override
    public Boolean queryUsernameAndPassword(String username, String password) {
        List<User> userList = userDao.queryUsernameAndPassword(username, password);

        
       return (userList!=null&&!userList.isEmpty())?true:false;
    }

    /**
     * 验证用户名是否可用
     *
     * @param username 用户名
     * @return 如果不可用 返回false 可用返回true
     */
    @Override
    public Boolean queryUsername(String username) {
        List<User> userList = userDao.queryUsername(username);
        return (userList!=null&&!userList.isEmpty()?false:true);
    }

    /**
     * 将用户信息保存到数据库t_user表中
     *
     * @param user 一个user对象
     * @return 返回一个boolean值
     */
    @Override
    public Boolean saveUserInformation(User user) {
        Boolean flag = userDao.saveUserInformation(user);
        return flag;
    }
}
