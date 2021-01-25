package com.javaweb.bookMall.dao.Impl;

import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.dao.UserDao;
import com.javaweb.bookMall.utils.JDBCUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {
    //创建jdbc工具类对象
    JDBCUtils jdbcUtils =new JDBCUtils();
    /**
     * 查询用户名和密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果查询到返回一条数据
     */
    @Override
    public List<User> queryUsernameAndPassword(String username, String password) {
        String sql = "select * from t_user where username = ? and password = ?";

        List<User> userList = jdbcUtils.QueryForList(User.class, sql, username, password);
        return userList;
    }

    /**
     * 查询用户名是否存在 如果查询到返回到集合里面 没有查询到返回null
     *
     * @param username 用户名
     * @return 返回一个装有User对象的list集合
     */
    @Override
    public List<User> queryUsername(String username) {
        String sql = "select * from t_user where username=?";
        List<User> userList = jdbcUtils.QueryForList(User.class, sql, username);
        return userList;
    }

    /**
     * 将用户信息保存到数据库的t_user表中
     * @param user  一个user对象
     * @return 成功保存返回true 失败返回false
     */
    @Override
    public Boolean saveUserInformation(User user) {

        String sql = "insert into t_user VALUES(null,?,?,?,?)";
        Boolean flag = jdbcUtils.GeneralUpdate(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone() );
        return flag;
    }
}
