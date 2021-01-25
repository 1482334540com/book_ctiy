package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.Book;
import com.javaweb.bookMall.bean.Paging;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    /**
     * 查询所有的图书·
     * @return 装在List集合里面
     */
    public List<Book> queryAll();

    /**
     * 查询一条数据
     * @param id 需要查询的id
     * @return 返回一个Book对象
     */
    public Book queryBook(int id);

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

    /**
     * 分页对象 处理分页查询
     * @param pageNo 当前页
     * @param pageSize 当前显示数量
     * @return
     */
    Paging<Book> page(int pageNo, int pageSize) throws SQLException;

    /**
     *  处理价格区间的分页查询
     * @param pageNO 当前页数据
     * @param pageSize 每页显示多少条数据
     * @param min 最小价格
     * @param max 最大价格
     * @return 返回一个page对象
     */
    Paging<Book> pageByPrice(int pageNO, int pageSize, int min, int max) throws SQLException;
}
