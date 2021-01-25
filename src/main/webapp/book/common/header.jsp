<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--    导航条部分-->
<div class="row">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="collapse navbar-collapse" >
            <ul class="nav navbar-nav">
                <li><a href="/book_ctiy/bookServlet?action=paging" ><span class="glyphicon glyphicon-home">首页</span></a></li>
                <li> <a  href="/book_ctiy/book/admin/shoppingCart.jsp"><span class="glyphicon glyphicon-shopping-cart">我的购物车</span></a></li>
                <li><a class="glyphicon glyphicon-folder-close" href="/book_ctiy/bookServlet?action=queryBook">图书管理</a></li>
                <li ><a class="glyphicon glyphicon-modal-window" href="/book_ctiy/userManagerServlet?action=queryUser">用户管理</a></li>
                <li ><a class="glyphicon glyphicon-modal-window" href="/book_ctiy/orderServlet?action=queryAllOrder">查看所有订单</a></li>
                </li>
            </ul>
            <ul class="nav navbar-nav pull-right ">
                <li ><a href="#">登录状态:${empty sessionScope.username?"暂未登录":sessionScope.username}</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" id="userManger" data-toggle="dropdown">个人管理 <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="/book_ctiy/book/Login.jsp"><span class="glyphicon glyphicon-user"></span> 登录</a></li>
                        <li><a href="/book_ctiy/book/Registered.jsp"><span class="glyphicon glyphicon-ok"></span> 注册</a></li>
                        <li><a href="/book_ctiy/userServlet?action=loginOut"><span class="glyphicon glyphicon-remove"></span> 注销</a></li>
                        <li><a href="/book_ctiyRegistered.jsp"><span class="glyphicon glyphicon-eye-open"></span> 查看个人资料</a></li>
                        <li><a href="/book_ctiy/book/sttelement.jsp" ><span class="glyphicon glyphicon-pencil"></span> 修改个人资料</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</div>

