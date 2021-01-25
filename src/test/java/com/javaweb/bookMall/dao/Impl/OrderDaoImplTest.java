package com.javaweb.bookMall.dao.Impl;

import com.javaweb.bookMall.bean.Order;
import com.javaweb.bookMall.dao.OrderDao;
import com.javaweb.bookMall.dao.OrderItemDao;
import com.javaweb.bookMall.service.Impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImplTest {

    @Test
    void saveOrder() {
        OrderDaoImpl orderItemDao = new OrderDaoImpl();
        Boolean flag = orderItemDao.saveOrder(new Order("123456", new Date(), new BigDecimal(100), 0, 1));
        System.out.println(flag);

    }

    @Test
    void queryUserId() {
        System.out.println( new OrderDaoImpl().queryUserId("admin"));
    }
}