package com.javaweb.bookMall.service.Impl;

import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.dao.Impl.UserDaoImpl;
import com.javaweb.bookMall.dao.Impl.UserManagerImpl;
import com.javaweb.bookMall.dao.UserManagerDao;
import com.javaweb.bookMall.service.UserManagerService;

import java.util.List;

public class UserManagerServiceImpl implements UserManagerService {
    //创建Dao层对象
    UserManagerDao userManagerDao = new UserManagerImpl();
    /**
     * 查询所有的数据 用于视图展示
     *
     * @return 返回一个List集合
     */
    @Override
    public List<User> queryUser() {
        List<User> userList = userManagerDao.queryUser();
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
        Boolean flag = userManagerDao.addUser(user);
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
        Boolean flag = userManagerDao.delUser(id);
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
        Boolean flag = userManagerDao.updateUser(user);
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
        List<User> likeUser = userManagerDao.likeUser("%"+name+"%");
        return likeUser;
    }

    /**
     * 按照用户名查询一个user对象
     *
     * @param name 用户名
     * @return 返回一个user对象
     */
    @Override
    public User queryOneUser(String name) {
        List<User> likeUser = userManagerDao.likeUser("%"+name+"%");
        User user = likeUser.get(0);

        return user ;

    }
}
