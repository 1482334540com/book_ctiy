package com.javaweb.bookMall.dao.Impl;


import com.javaweb.bookMall.bean.Order;

import com.javaweb.bookMall.bean.OrderItem;
import com.javaweb.bookMall.dao.OrderDao;

import com.javaweb.bookMall.utils.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl implements OrderDao {


     JDBCUtils jdbcUtils=new JDBCUtils();


    /**
     * 保存订单
     *
     * @param order 需要一个订单对象
     * @return
     */
    @Override
    public Boolean saveOrder(Order order) {


        String sql = "insert into t_order values(?,?,?,?,?)";

        Boolean flag = jdbcUtils.GeneralUpdate(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());

        return flag;
    }

    /**
     * 查看所有的订单
     *
     * @return 返回一个List集合
     */
    @Override
    public List<Order> queryAllOrder() {

        String sql = "select * from t_order";
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        List<Order> orderList = new ArrayList<Order>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                String orderId = resultSet.getString(1);
                Date createTime = resultSet.getDate(2);
                BigDecimal price = resultSet.getBigDecimal(3);
                int status = resultSet.getInt(4);
                int userId = resultSet.getInt(5);

                Order order = new Order(orderId,createTime,price,status,userId);
                orderList.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }

        return orderList;
    }

    /**
     * 发货 将订单状态改为 1
     * @param  order_id  订单号
     * @return
     */
    @Override
    public Boolean delivery(String order_id) {
        String sql ="update t_order set status=1 where order_id=?";
        Boolean flag = jdbcUtils.GeneralUpdate(sql ,order_id);
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

       String sql =  "select * from t_order where user_id=?";

       Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        List<Order> orderList = new ArrayList<Order>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                String orderId = resultSet.getString(1);
                Date createTime = resultSet.getDate(2);
                BigDecimal price = resultSet.getBigDecimal(3);
                int status = resultSet.getInt(4);
                int orderID= resultSet.getInt(5);

                Order order = new Order(orderId,createTime,price,status,orderID);
                orderList.add(order);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }

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
        String sql = "select * from t_order_item where order_id=?";

        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        List<OrderItem> orderItemList  = new ArrayList<OrderItem>();
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,orderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int count = resultSet.getInt(3);
                BigDecimal price = resultSet.getBigDecimal(4);
                BigDecimal totalPrice = resultSet.getBigDecimal(5);
                String orderID = resultSet.getString(6);

                OrderItem orderItem = new OrderItem(id,name,count,price,totalPrice,orderID);
                orderItemList.add(orderItem);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }

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
        String sql ="update t_order set status=3 where order_id=?";
        Boolean flag = jdbcUtils.GeneralUpdate(sql ,orderId);
        return flag;
    }

    /**
     * 通过用户名查找id
     *
     * @param username 用户名
     * @return
     */
    @Override
    public String queryUserId(String username) {
        String sql  = "select id from t_user where username=? ";
        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String  id = null;

        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                //将int类型转化为String
                id = Integer.toString(resultSet.getInt("id"));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.closeStatement(connection,preparedStatement,resultSet);
        }
        return id;


    }





}
