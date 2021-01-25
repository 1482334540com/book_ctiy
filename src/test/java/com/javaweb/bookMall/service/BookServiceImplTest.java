package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.Book;
import com.javaweb.bookMall.bean.Paging;
import com.javaweb.bookMall.service.Impl.BookServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;


class BookServiceImplTest {
    //创建service层对象
    BookService bookService = new BookServiceImpl();

    @Test
    void queryAll() {
        List<Book> bookList = bookService.queryAll();
        System.out.println(bookList);

    }

    @Test
    void deleteBook() {
        Boolean flag = bookService.deleteBook("10");
        System.out.println(flag);
    }

    @Test
    void updateBook() {
        Book book = new Book(7, "JavaScript圣经", "小明", 85, 66, 51, null);
        Boolean flag = bookService.updateBook(book);
        System.out.println(flag);
    }

    @Test
    void addBook() {
        Book book = new Book(null, "活着", "胡雨", 85, 66, 51, null);
        Boolean flag = bookService.addBook(book);
        System.out.println(flag);

    }

    @Test
    void likeBook() {
        List<Book> javascript = bookService.likeBook("java");
        System.out.println(javascript);

    }

    @Test
    void queryForPageTotalCount() throws SQLException {
        Paging<Book> page = bookService.page(0, 4);
        System.out.println(page);

    }


    @Test
    void pageByPrice() throws SQLException {
        Paging<Book> page = bookService.pageByPrice(0, 4,10,50);
        System.out.println(page);
    }
}