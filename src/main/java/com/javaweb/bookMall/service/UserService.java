package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.User;

import java.util.List;

public interface UserService {
    /**
     * 验证用户名和密码是否正确
     * @param username 用户名
     * @param password  密码
     * @return 如果正确返回一个 true 错误返回1一个false
     */
    public Boolean queryUsernameAndPassword( String username, String password);

    /**
     * 验证用户名是否可用
     * @param username 用户名
     * @return 如果不可用 返回false 可用返回true
     *
     */
    public  Boolean queryUsername(String username);

    /**
     * 将用户信息保存到数据库t_user表中
     * @param user 一个user对象
     * @return 返回一个boolean值
     */
    public Boolean saveUserInformation(User user);

}
