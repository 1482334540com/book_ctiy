package com.javaweb.bookMall.service;

import com.javaweb.bookMall.bean.Cart;
import com.javaweb.bookMall.bean.Order;
import com.javaweb.bookMall.bean.OrderItem;

import java.util.List;

public interface OrderService {

    /**
     * 创建订单
     * @param cart 购物车对象
     * @param userId 用户的id
     * @return 返回一个字符串
     */
    public String  createOrder(Cart cart ,Integer userId);

    /**
     * 查询所有的订单
     * @return 返回一个List集合
     */
    public List<Order> queryAllOrder();

    /**
     * 发货
     * @param orderId 订单号
     * @return
     */
    public Boolean  delivery(String orderId);

    /**
     * 查询我的订单
     * @param userId 用户id
     * @return 返回一个list集合
     */
    public  List<Order> showMyOrder(String userId);

    /**
     * 查询订单详情
     * @param orderId 订单号
     * @return 返回一个订单项集合
     */
    public  List<OrderItem> showOrderDetails(String orderId);

    /**
     * 订单签收
     * @param orderId 订单号
     * @return 返回一个布尔值
     */
    public Boolean receivingOrder(String orderId);
}
