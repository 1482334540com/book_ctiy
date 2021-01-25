package com.javaweb.bookMall.dao;

import com.javaweb.bookMall.bean.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询用户名和密码是否正确
     * @param username 用户名
     * @param password 密码
     * @return 如果查询到返回一条数据
     */
    public List<User> queryUsernameAndPassword(String username,String password);

    /**
     * 查询用户名是否存在 如果查询到返回到集合里面 没有查询到返回null
     * @param username 用户名
     * @return 返回一个装有User对象的list集合
     */
    public List<User>  queryUsername (String username);

    /**
     * 将用户信息保存到数据库的t_user表中
     * @param user  一个user对象
     * @return 成功保存返回true 失败返回false
     */
    public Boolean saveUserInformation(User user);



}
