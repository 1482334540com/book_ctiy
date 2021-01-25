package com.javaweb.bookMall.servlet;

import com.javaweb.bookMall.bean.Cart;
import com.javaweb.bookMall.bean.Order;
import com.javaweb.bookMall.bean.OrderItem;
import com.javaweb.bookMall.bean.User;

import com.javaweb.bookMall.dao.Impl.OrderDaoImpl;

import com.javaweb.bookMall.service.Impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderServlet")
public class OrderServlet extends BaseServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();

    //生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart =(Cart) req.getSession().getAttribute("cart");
        User user =(User) req.getSession().getAttribute("user");
        //获取用户id
        Integer loginId = user.getId();

        //调用service层生成订单 保存到数据库
        String orderId = orderService.createOrder(cart, loginId);



        //用重定向防止表单重复提交
        req.getSession().setAttribute("orderId",orderId);
        //请求重定向到结账成功页面
        resp.sendRedirect("/book_ctiy/book/admin/checkoutSuccess.jsp");


    }
    //查看所有订单
    protected void queryAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orderList = orderService.queryAllOrder();

        req.setAttribute("orderList",orderList);

        req.getRequestDispatcher("book/admin/queryAllOrder.jsp").forward(req,resp);
    }

    //发货
    protected void delivery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");
        Boolean flag = orderService.delivery(orderId);

        //跳转回原来的位置
        resp.sendRedirect(req.getHeader("Referer"));


    }
    //查看我的订单
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String username = (String)req.getSession().getAttribute("username");
        //调用dao层按照用户名查询id
        String id = new OrderDaoImpl().queryUserId(username);

        List<Order> myOrders = orderService.showMyOrder(id);
        req.setAttribute("myOrders",myOrders);

        req.getRequestDispatcher("book/admin/order.jsp").forward(req,resp);


    }
    //确认签收
    protected void receivingOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String orderId = req.getParameter("orderId");
        Boolean flag = orderService.receivingOrder(orderId);
        System.out.println(flag);

        //跳转回原来的位置
        resp.sendRedirect(req.getHeader("Referer"));


    }

    //查看订单详情
    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");
        List<OrderItem> itemLists = orderService.showOrderDetails(orderId);
        req.setAttribute("itemLists",itemLists);
        req.getRequestDispatcher("book/admin/orderDetails.jsp").forward(req,resp);

    }




}
