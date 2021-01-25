package com.javaweb.bookMall.service.Impl;

import com.javaweb.bookMall.bean.Book;
import com.javaweb.bookMall.bean.Paging;
import com.javaweb.bookMall.dao.BookDao;
import com.javaweb.bookMall.dao.Impl.BookDaoImpl;
import com.javaweb.bookMall.service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    //创建Dao层对象
    BookDao bookDao = new BookDaoImpl();
    /**
     * 查询所有的图书·
     *
     * @return 装在List集合里面
     */
    @Override
    public List<Book> queryAll() {
        List<Book> bookList = bookDao.queryAll();
        return bookList;
    }

    /**
     * 查询一条数据
     *
     * @param id 需要查询的id
     * @return 返回一个Book对象 没有查询到返回null
     */
    @Override
    public Book queryBook(int id) {

        List<Book> bookList = bookDao.queryAll();
        for (Book book : bookList) {
            if (book.getId()==id){
              return book;
            }
        }


        return null;
    }




    /**
     * 删除图书
     *
     * @param id 需要删除图书的id
     * @return 成功返回true 失败返回false
     */
    @Override
    public Boolean deleteBook(String id) {
        Boolean flag = bookDao.deleteBook(id);
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
        Boolean flag = bookDao.updateBook(book);
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
        Boolean flag = bookDao.addBook(book);
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
        List<Book> bookList = bookDao.likeBook("%"+name+"%");
        return bookList;
    }

    /**
     *
     * @param pageNo   当前页
     * @param pageSize 当前显示数量
     * @return 返回一个分页对象
     * @throws SQLException
     */
    @Override
    public Paging<Book> page(int pageNo, int pageSize) throws SQLException {

        BookDao bookDao = new BookDaoImpl();
        Paging<Book>  paging = new Paging<Book>();

        //设置每页显示的数量
        paging.setPageSize(pageSize);

        //设置总记录数 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        paging.setPageTotalCount(pageTotalCount);

        //求总页码=总记录数/每页显示的数量  (设置总页码)
        Integer pageTotal = pageTotalCount/pageSize;
        //如果除不尽 总页码加1
        pageTotal =pageTotalCount % pageSize > 0?pageTotal+1:pageTotal;
        paging.setPageTotal(pageTotal);

        //设置当前页码(数据边界有效检查)
        if (pageNo < 1) { pageNo = 1; }
        if (pageNo > pageTotal) { pageNo = pageTotal; }
        paging.setPageNo(pageNo);


        //求当前页数据
        int begin = (paging.getPageNo()-1)*pageSize;

        List<Book> bookList =bookDao.queryForPageItems(begin,pageSize);
        paging.setItems(bookList);

        return paging;
    }

    //价格区间查询方法
    @Override
    public Paging<Book> pageByPrice(int pageNo, int pageSize, int min, int max)  {

        BookDao bookDao = new BookDaoImpl();
        Paging<Book>  paging = new Paging<Book>();

        //设置每页显示的数量
        paging.setPageSize(pageSize);

        //设置总记录数 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        paging.setPageTotalCount(pageTotalCount);

        //求总页码=总记录数/每页显示的数量  (设置总页码)
        Integer pageTotal = pageTotalCount/pageSize;
        //如果除不尽 总页码加1
        pageTotal =pageTotalCount % pageSize > 0?pageTotal+1:pageTotal;
        paging.setPageTotal(pageTotal);

        //设置当前页码(数据边界有效检查)
        if (pageNo < 1) { pageNo = 1; }
        if (pageNo > pageTotal) { pageNo = pageTotal; }
        paging.setPageNo(pageNo);


        //求当前页数据
        int begin = (paging.getPageNo()-1)*pageSize;

        List<Book> bookList =bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        paging.setItems(bookList);

        return paging;

    }

}
