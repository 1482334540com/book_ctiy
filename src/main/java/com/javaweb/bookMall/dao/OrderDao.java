package com.javaweb.bookMall.dao;

import com.javaweb.bookMall.bean.Order;
import com.javaweb.bookMall.bean.OrderItem;

import java.util.List;

public interface OrderDao {
    /**
     * 保存订单
     * @param order 需要一个订单对象
     * @return
     */
    public Boolean saveOrder( Order order);

    /**
     * 查看所有的订单
     * @return  返回一个List集合
     */
    public List<Order> queryAllOrder();

    /**
     * 发货 将订单状态改为 1
     * @param  order_id  订单号
     * @return
     */
    public Boolean delivery(String order_id);

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

    /**
     * 通过用户名查找id
     * @param username 用户名
     * @return
     */
    public  String queryUserId(String username);


}
