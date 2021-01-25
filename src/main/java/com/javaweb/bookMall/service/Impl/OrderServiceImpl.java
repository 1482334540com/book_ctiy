package com.javaweb.bookMall.service.Impl;

import com.javaweb.bookMall.bean.*;
import com.javaweb.bookMall.dao.Impl.OrderDaoImpl;
import com.javaweb.bookMall.dao.Impl.OrderItemDaoImpl;
import com.javaweb.bookMall.dao.OrderDao;
import com.javaweb.bookMall.dao.OrderItemDao;
import com.javaweb.bookMall.service.BookService;
import com.javaweb.bookMall.service.OrderService;


import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl() ;
    private BookService bookService = new BookServiceImpl();
    /**
     * 创建订单
     *
     * @param cart   购物车对象
     * @param userId 用户的id
     * @return 返回一个字符串
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成一个唯一的订单
        String orderId = System.currentTimeMillis()+""+userId;
        //创建订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
        //遍历购物车中的每一个商品项,转换为订单项保存到数据库
       for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
           CartItem cartItem = entry.getValue();
           OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
           //保存订单项到数据库
           orderItemDao.saveOrderItem(orderItem);

           //减少响应的库存和增加销量
           Book book = bookService.queryBook(cartItem.getId());
           book.setSales(book.getSales()+cartItem.getCount());
           book.setStock(book.getStock()-cartItem.getCount());
           bookService.updateBook(book);

       }
       //清空购物车
       cart.clear();
        return orderId;
    }

    /**
     * 查询所有的订单
     *
     * @return 返回一个List集合
     */
    @Override
    public List<Order> queryAllOrder() {
        List<Order> orderList = orderDao.queryAllOrder();
        return orderList;
    }

    /**
     * 发货
     *
     * @param orderId 订单号
     * @return
     */
    @Override
    public Boolean delivery(String orderId) {
        Boolean flag = orderDao.delivery(orderId);
        return flag;
    }

    /**
     * 查询我的订单
     *
     * @param userId 用户id
     * @return 返回一个list集合
     */
    @Override
    public List<Order> showMyOrder(String userId) {
        List<Order> orderList = orderDao.showMyOrder(userId);
        return orderList;
    }

    /**
     * 查询订单详情
     *
     * @param orderId 订单号
     * @return 返回一个订单项集合
     */
    @Override
    public List<OrderItem> showOrderDetails(String orderId) {
        List<OrderItem> orderItemList = orderDao.showOrderDetails(orderId);
        return orderItemList;
    }

    /**
     * 订单签收
     *
     * @param orderId 订单号
     * @return 返回一个布尔值
     */
    @Override
    public Boolean receivingOrder(String orderId) {
        Boolean flag = orderDao.receivingOrder(orderId);
        return flag;
    }
}
