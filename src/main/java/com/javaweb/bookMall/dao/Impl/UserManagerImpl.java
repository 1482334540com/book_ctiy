package com.javaweb.bookMall.dao.Impl;

import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.dao.UserManagerDao;
import com.javaweb.bookMall.utils.JDBCUtils;

import java.util.List;

public class UserManagerImpl implements UserManagerDao {
    //创建JDBCUtils层对象
    JDBCUtils jdbcUtils = new JDBCUtils();
    /**
     * 查询所有的数据 用于视图展示
     *
     * @return 返回一个List集合
     */
    @Override
    public List<User> queryUser() {
        String sql = "select * from t_user";
        List<User> userList = jdbcUtils.QueryForList(User.class, sql);
        return userList;
    }

    /**
     * 添加
     *
     * @param user 一个user对象
     * @return 成功返回true 失败返回false
     */
    @Override
    public Boolean addUser(User user) {
        String sql = "insert into t_user values(null,?,?,?,?)";
        Boolean flag = jdbcUtils.GeneralUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone());
        return flag;
    }

    /**
     * 删除用户
     *
     * @param id 需要删除的用户id
     * @return 成功返回true  失败返回false
     */
    @Override
    public Boolean delUser(String id) {

        String sql = "delete from t_user where id=?";
        Boolean flag = jdbcUtils.GeneralUpdate(sql, id);
        return flag;
    }

    /**
     * 修改用户信息
     *
     * @param user 需要一个user对象
     * @return 成功返回true 失败返回false
     */
    @Override
    public Boolean updateUser(User user) {

        String sql = "update t_user set username=?,password=?,email=?,phone=? where id=?";
        Boolean flag = jdbcUtils.GeneralUpdate(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(),user.getId());

        return flag;
    }

    /**
     * 按照用户名查找用户
     *
     * @param name 需要查找的用户名
     * @return 返回一个List集合
     */
    @Override
    public List<User> likeUser(String name) {
        String sql = "select * from t_user where username like ?";
        List<User> userList = jdbcUtils.QueryForList(User.class, sql, name);
        return userList;
    }
}
