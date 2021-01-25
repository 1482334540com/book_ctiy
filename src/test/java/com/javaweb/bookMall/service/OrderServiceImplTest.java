package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.Cart;
import com.javaweb.bookMall.bean.CartItem;
import com.javaweb.bookMall.bean.Order;
import com.javaweb.bookMall.bean.OrderItem;
import com.javaweb.bookMall.service.Impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    OrderServiceImpl orderService = new OrderServiceImpl();

    //创建订单
    @Test
    void createOrder() {

        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java入门到精通",1,new BigDecimal(1000), new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100), new BigDecimal(100)));

        String orderId = orderService.createOrder(cart, 1);
        System.out.println(orderId);

    }
    //发货
    @Test
    void delivery() {

        Boolean flag = orderService.delivery("16088085650281");
        System.out.println(flag);
    }
    //查看我的订单
    @Test
    void showMyOrder() {
        List<Order> orderList = orderService.showMyOrder("1");
        System.out.println(orderList);

    }
    //查看订单详情
    @Test
    void showOrderDetails() {
        List<OrderItem> orderItemList = orderService.showOrderDetails("16088085650281");
        for (OrderItem orderItem : orderItemList) {
            System.out.println(orderItem);
        }
    }
    //确认收货
    @Test
    void receivingOrder() {
        Boolean flag = orderService.receivingOrder("16088085650281");
        System.out.println(flag);

    }
}