package com.javaweb.bookMall.dao.Impl;

import com.javaweb.bookMall.bean.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    OrderDaoImpl orderItemDao = new OrderDaoImpl();
    @Test
    void queryAllOrder() {

        List<Order> orderList = orderItemDao.queryAllOrder();
        for (Order order : orderList) {
            System.out.println(order);
        }

    }
}