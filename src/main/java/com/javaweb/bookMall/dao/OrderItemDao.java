package com.javaweb.bookMall.dao;


import com.javaweb.bookMall.bean.OrderItem;

public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem 需要保存的订单项
     * @return
     */
    public Boolean saveOrderItem(OrderItem orderItem);
}
