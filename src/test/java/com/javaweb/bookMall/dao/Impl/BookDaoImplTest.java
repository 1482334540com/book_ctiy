package com.javaweb.bookMall.dao.Impl;

import com.javaweb.bookMall.bean.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoImplTest {
    BookDaoImpl bookDao = new BookDaoImpl();
    @Test
    void queryForPageTotalCountByPrice() {
        Integer integer = bookDao.queryForPageTotalCountByPrice(10, 20);
        System.out.println(integer);


    }

    @Test
    void queryForPageItemsByPrice() {
        List<Book> bookList = bookDao.queryForPageItemsByPrice(0, 4, 10, 50);
        for (Book book : bookList) {
            System.out.println(book);
        }

    }
}