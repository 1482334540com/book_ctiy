package com.javaweb.bookMall.dao.Impl;

import com.javaweb.bookMall.bean.Book;
import com.javaweb.bookMall.dao.BookDao;
import com.javaweb.bookMall.utils.JDBCUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    //创建JDBCUtils对象
    JDBCUtils jdbcUtils = new JDBCUtils();



    /**
     * 查询所有的图书·
     *
     * @return 装在List集合里面
     */
    @Override
    public List<Book> queryAll() {
        String sql = "select * from t_book";
        List<Book> bookList = jdbcUtils.QueryForList(Book.class, sql);
        return bookList;
    }



    /**
     * 删除图书
     *
     * @param id 需要删除图书的id
     * @return 成功返回true 失败返回false
     */
    @Override
    public Boolean deleteBook(String id) {
        String sql = "delete from t_book where id=?";
        Boolean flag = jdbcUtils.GeneralUpdate(sql, id);
        return flag;
    }

    /**
     * 修改图书
     *
     * @param book 需要修改的图书对象
     * @return 成功返回true 失败返回false
     */
    @Override
    public Boolean updateBook(Book book) {

        String sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img=? where id =?" ;
        Boolean flag = jdbcUtils.GeneralUpdate(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImg(),book.getId());
        return flag;
    }

    /**
     * 添加图书
     *
     * @param book
     * @return 添加成功返回true 失败返回false
     */
    @Override
    public Boolean addBook(Book book) {
        String sql = "insert into t_book values(?,?,?,?,?,?,?)";
        Boolean flag = jdbcUtils.GeneralUpdate(sql,book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg());
        return flag;
    }

    /**
     * 按照书名查找图书
     *
     * @param name 需要查找的书名
     * @return 查询到返回一个List集合
     */
    @Override
    public List<Book> likeBook(String name) {
        String sql = "select * from t_book where name like ?";
        List<Book> bookList = jdbcUtils.QueryForList(Book.class,sql, name);
        return bookList;

    }

    /**
     * 查询总记录数
     *
     * @return 返回数据库的总记录数
     */
    @Override
    public Integer queryForPageTotalCount() {

        String sql = "select count(*) from t_book";
        Connection connection = null;
        Integer count = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
             preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = Integer.parseInt(resultSet.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }
        return count;
    }




    /**
     * 当前页数据为： 当前页-1*总记录数
     *
     * @param begin    从什么地方开始查，
     * @param pageSize 从什么地方开始结束
     * @return 返回一个List集合
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {

        String sql = "select * from t_book limit ?,? ";
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        List<Book> bookList = new ArrayList<Book>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,begin);
            preparedStatement.setObject(2,pageSize);
             resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String author = resultSet.getString(3);
                int price = resultSet.getInt(4);
                int sales = resultSet.getInt(5);
                int stock = resultSet.getInt(6);
                String img = resultSet.getString(7);

                Book book = new Book(id,name,author,price,sales,stock,img);
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }

        return bookList;
    }

    /**
     * 按照价格区间查询总记录数
     *
     * @return
     */
    @Override
    public Integer queryForPageTotalCountByPrice(int min,int max) {
        String sql = "select count(*) from t_book where price  between  ? and ?";
        Connection connection = null;
        Integer count = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,min);
            preparedStatement.setInt(2,max);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = Integer.parseInt(resultSet.getString(1));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }
        return count;
    }

    /**
     * @param begin    开始查询位置
     * @param pageSize 查询多少条数据
     * @param min      最小价格
     * @param max      最大价格
     * @return 查询到的数据装到List集合中
     */
    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {

        String sql = "select * from t_book where  price  between  ? and ?  order by price limit ?,?; ";
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        List<Book> bookList = new ArrayList<Book>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1,min);
            preparedStatement.setObject(2,max);
            preparedStatement.setObject(3,begin);
            preparedStatement.setObject(4,pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String author = resultSet.getString(3);
                int price = resultSet.getInt(4);
                int sales = resultSet.getInt(5);
                int stock = resultSet.getInt(6);
                String img = resultSet.getString(7);

                Book book = new Book(id,name,author,price,sales,stock,img);
                bookList.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }

        return bookList;
    }
}
