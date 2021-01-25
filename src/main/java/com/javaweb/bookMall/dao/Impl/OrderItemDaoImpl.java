package com.javaweb.bookMall.dao.Impl;

import com.javaweb.bookMall.bean.OrderItem;
import com.javaweb.bookMall.dao.OrderItemDao;
import com.javaweb.bookMall.utils.JDBCUtils;

public class OrderItemDaoImpl  implements OrderItemDao {
    JDBCUtils jdbcUtils=new JDBCUtils();
    /**
     * 保存订单项
     *
     * @param orderItem 需要保存的订单项
     * @return
     */
    @Override
    public Boolean saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        Boolean flag = jdbcUtils.GeneralUpdate(sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

        return flag;
    }
}
