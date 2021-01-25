package com.javaweb.bookMall.dao;

import com.javaweb.bookMall.bean.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    /**
     * 查询所有的图书·
     * @return 装在List集合里面
     */
    public List<Book> queryAll();




    /**
     * 删除图书
     * @param id 需要删除图书的id
     * @return 成功返回true 失败返回false
     */
    public Boolean deleteBook(String id);

    /**
     *  修改图书
     * @param book  需要修改的图书对象
     * @return 成功返回true 失败返回false
     */
    public Boolean updateBook(Book book);

    /**
     * 添加图书
     * @param book
     * @return 添加成功返回true 失败返回false
     */
    public Boolean addBook(Book book);


    /**
     *
     *按照书名查找图书
     * @param name 需要查找的书名
     * @return 查询到返回一个List集合
     */
    public List<Book> likeBook(String name);


    //分页模型实现
    /**
     * 查询总记录数
     * @return 返回数据库的总记录数
     */
    public Integer queryForPageTotalCount() throws SQLException;

    /**
     * 当前页数据为： 当前页-1*总记录数
     * @param begin 从什么地方开始查，
     * @param pageSize 从什么地方开始结束
     * @return 返回一个List集合
     */
    public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException;

    /**
     *
     * @param begin 开始查询位置
     * @param pageSize 查询多少条数据
     * @param min 最小价格
     * @param max 最大价格
     * @return 查询到的数据装到List集合中
     */
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);

    /**
     * 按照价格查询记录总数
     * @param min 最小价格
     * @param max 最大价格
     * @return  返回一个（在指定价格区间内）的总记录数
     */

    public Integer queryForPageTotalCountByPrice(int min, int max);
}
