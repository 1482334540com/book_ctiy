<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


<head>

    <meta charset="UTF-8">
    <%@ include file="/book/common/resoures.jsp"%>
    <title>首页</title>
    <style>

        /*公共样式*/
        *{
            margin: 0;padding: 0;
            list-style-type:none
        }
        /*包含以下五种的链接*/
        a {
            text-decoration: none;
        }
        /*正常的未被访问过的链接*/
        a:link {
            text-decoration: none;
        }
        /*已经访问过的链接*/
        a:visited {
            text-decoration: none;
        }
        /*鼠标划过(停留)的链接*/
        a:hover {
            text-decoration: none;
        }
        /* 正在点击的链接，鼠标在元素上按下还没有松开*/
        a:active {
            text-decoration: none;
        }
        /* 获得焦点的时候 鼠标松开时显示的颜色*/
        a:focus {
            text-decoration: none;
        }

        /*尾部css样式开始*/
        .footer_img_content li{
            float: left; margin-left: 30px;
        }
        .friendly_link1 li a{
            margin-top: 20px;
            float: left; margin-left: 30px;
        }
        .friendly_link2 li a{
            margin-top: 20px;
            float: left; margin-left: 30px;
        }
        .friendly_button li button{
            margin-top: 20px;
            float: left; margin-left: 30px;
        }
        /*尾部css样式结束*/

        /*导航条公共部分开始*/

        #cartLog{
            width: 150px;height: 150px;
        }
        #navUl li{
            margin-left: 10px;
            float: left;
            font-size: 20px;
        }
        #navUl{
            margin-top: 25px;
        }

        /*导航条公共部分结束*/

        /*价格区间查询*/
        .priceInput{
            width: 50px;
        }
        .bookContent{
        margin-top: 40px;
        }
        #userManger{
            margin-right: 50px;
        }
        #page_nav{
            margin-bottom: 20px;
        }
        #bookImg{
            width: 200px;height: 200px;
        }
        #bookMessage{
            position: absolute;top: 100px; right:100px;
        }

    </style>
</head>
<body>
<div class="container-fluid">
<!--    导航条部分-->
    <div class="row">
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="collapse navbar-collapse" >
                        <ul class="nav navbar-nav">
                        <li><a href="/book_ctiy/bookServlet?action=paging" ><span class="glyphicon glyphicon-home">首页</span></a></li>
                            <li> <a  href="/book_ctiy/book/admin/shoppingCart.jsp"><span class="glyphicon glyphicon-shopping-cart">我的购物车</span></a></li>
                            <li><a class="glyphicon glyphicon-folder-close" href=" /book_ctiy/bookServlet?action=queryBook">图书管理</a></li>
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
                             <li><a href="/book_ctiy/admin/userServlet?action=loginOut"><span class="glyphicon glyphicon-remove"></span> 注销</a></li>

                                </ul>
                            </li>
                        </ul>
             </div>
        </nav>
    </div>
<!--    主体内容-->
    <div class="row ">
        <div class="jumbotron">
            <div class="container">
                <h2 class="text-primary">欢迎登来到故事书城！</h2>

                <form action="/book_ctiy/bookServlet" class="priceFrom text-center">
                    <input type="hidden" name="action" value="pageByPrice">
                    <span>价格区间查询:</span>
                    <input type="text" name="min" value="${param.min}" class="priceInput"> 元 -
                    <input type="text"  name="max" value="${param.max}" class="priceInput"> 元
                    <input type="submit" class="btn btn-success" role="button" value="查询">
                </form>

                <c:if test="${ empty sessionScope.cart.items}">
                    <div id="bookMessage">
                        <p> <b class="text-danger">当前购物车为空</b> </p>
                    </div>

                </c:if>


                <c:if test="${ not empty sessionScope.cart.items}">
                    <div id="bookMessage">
                        <p>您的购物车中有 <b class="text-danger">${ sessionScope.cart.totalCount}</b>本图书 </p>
                        <p>您刚刚将  <b class="text-danger">${sessionScope.lastName}</b> 加入了购物车 </p>
                    </div>

                </c:if>
                <div class="col-lg-12   text-center bookContent ">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title ">快将您心仪的商品加入购物车吧! </h3>
                        </div>
                        <div class="panel-body">
                            <!--  图书展示开始-->
                            <c:forEach items="${requestScope.page.items}" var="book">

                            <div class="col-sm-6 col-md-3">
                                <div class="thumbnail">
                                    <img id="bookImg" src="${book.img}"
                                         alt="通用的占位符缩略图">
                                    <div class="caption">
                                        <ul>
                                            <li>书名: ${sessionScope.path}</li>
                                            <li>书名: ${book.name}</li>
                                            <li>作者: ${book.author}</li>
                                            <li>价格: ${book.price}</li>
                                            <li>销量: ${book.sales}</li>
                                            <li>库存: ${book.stock}</li>
                                            <li>
                                                <button bookId="${book.id}" class="btn-success btn addToCart"  >加入购物车</button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                    </div>
                        <div id="page_nav">
                            <%--  如果当前页码大于1才会显示首页和下一页--%>
                            <c:if test="${requestScope.page.pageNo>1}">
                                <a href="${requestScope.page.url}&pageNo=1">首页</a>
                                <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
                            </c:if>
                                <%-- 页码输出开始--%>
                                <c:choose>
                                    <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
                                    <c:when test="${ requestScope.page.pageTotal <= 5 }">
                                        <c:set var="begin" value="1"/>
                                        <c:set var="end" value="${requestScope.page.pageTotal}"/>
                                    </c:when>
                                    <%--情况2：总页码大于5的情况--%>
                                    <c:when test="${requestScope.page.pageTotal > 5}">
                                        <c:choose>
                                            <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                                            <c:when test="${requestScope.page.pageNo <= 3}">
                                                <c:set var="begin" value="1"/>
                                                <c:set var="end" value="5"/>
                                            </c:when>
                                            <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                                            <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                                                <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                                                <c:set var="end" value="${requestScope.page.pageTotal}"/>
                                            </c:when>
                                            <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                                            <c:otherwise>
                                                <c:set var="begin" value="${requestScope.page.pageNo-2}"/>
                                                <c:set var="end" value="${requestScope.page.pageNo+2}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>

                                <c:forEach begin="${begin}" end="${end}" var="i">
                                    <c:if test="${i == requestScope.page.pageNo}">
                                        【${i}】
                                    </c:if>
                                    <c:if test="${i != requestScope.page.pageNo}">
                                        <a href="http://localhost:80${requestScope.page.url}&pageNo=${i}">${i}</a>
                                    </c:if>
                                </c:forEach>
                                <%--页码输出的结束--%>

                            <%--只有当前页码小于总页码的时候才会显示末页和下一页--%>
                                <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
                                    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
                                    <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
                                </c:if>
                            共${requestScope.page.pageTotal}页，总共 <span>${requestScope.page.pageTotalCount}</span> 条记录
                            跳转到<input class="priceInput"   value="${param.pageNo}" name="pn" id="pn_input"/>页
                            <input id="searchPageBtn" class="btn btn-success" type="button" value="确定"> </div>
                    </div>
                </div>
<!--  图书展示结束-->
            </div>
        </div>

    </div>
<!--    网页尾部-->
    <div class="row">
        <footer class="" role="navigation"  style="margin-top: 25px;">
            <ul>
                <li>
                    <p><b>网站信息&emsp; 	&emsp; 	&emsp; 	&emsp; 	</b> 建站时间： <span id="data2"></span> &emsp; 	&emsp; 	访问人数：23351人</p>
                </li>

                <li><span><b>友情链接</b></span></li>

                <ul class="footer_img_content clearfix">
                    <li class="col-lg-offset-1"><img src="https://www.51zxw.net/images/u_link/haoqq_51.jpg" alt=""></li>
                    <li><img src="https://www.51zxw.net/images/u_link/360.png" alt=""></li>
                    <li><img src="https://www.51zxw.net/images/u_link/baidu.png" alt=""></li>
                    <li><img src="https://www.51zxw.net/images/u_link/rising_51.png" alt=""></li>
                    <li><img src="https://www.51zxw.net/images/u_link/haoqq2019.png" alt=""></li>
                </ul></li>
                <li>

                <li> <!--   友情链接第一行-->
                    <ul class="friendly_link1 clearfix">
                        <li><a href="">大耳朵英语</a></li>
                        <li><a href="">上学吧</a></li>
                        <li><a href="">3d模型</a></li>
                        <li><a href="">中华室内设计网</a></li>
                        <li><a href="">UI制作者</a></li>
                        <li><a href="">华图教育</a></li>
                        <li><a href="">律师365</a></li>
                        <li><a href="">高分网</a></li>
                        <li><a href="">中华考试网</a></li>
                        <li><a href="">百分网</a></li>
                        <li><a href="">河源下载站</a></li>
                        <li><a href="">读书人</a></li>
                        <li><a href="">精英家教网</a></li>
                        <li><a href="">漫漫看漫画</a></li>
                        <li><a href="">2345导航</a></li>
                        <li><a href="">FLVCD</a></li>


                    </ul>
                </li>
                <li>   <!--   友情链接第二行-->
                    <ul class="friendly_link2 clearfix">
                        <li><a href="">JSON在线工具</a></li>
                        <li><a href="">学习啦</a></li>
                        <li><a href="">站长网</a></li>
                        <li><a href="">考研网</a></li>
                        <li><a href="">思缘设计论坛</a></li>
                        <li><a href="">学习方法</a></li>
                        <li><a href="">多多软件站</a></li>
                        <li><a href="">中公教育</a></li>
                        <li><a href=""电脑报在线></a></li>
                        <li><a href="">数码资源网</a></li>
                        <li><a href="">w3cschool</a></li>
                        <li><a href="">新浪爱问分享</a></li>
                        <li><a href="">Edong网</a></li>
                    </ul>
                </li>

                <li>
                    <ul class="friendly_button clearfix"  style="margin-left: 17%">
                        <li><button type="button" class="btn btn-info">关于网站</button></li>
                        <li><button type="button" class="btn btn-info">关于我们</button></li>
                        <li><button type="button" class="btn btn-info">网站地图</button></li>
                        <li><button type="button" class="btn btn-info">建议反馈</button></li>
                        <li><button type="button" class="btn btn-info">购买教程</button></li>
                        <li><button type="button" class="btn btn-info">在线咨询</button></li>
                        <li><button type="button" class="btn btn-info">业务合作</button></li>
                    </ul>
                </li>
                <br>
                <li>
                    <p class="text-center">IT学院,版权所有.未经许可,本网站任何视频教程不得转载 法律顾问：xx律师 粤ICP备075244235号
                        公安局备案编号:44060434553 客服电话: 4008-3822-14
                        <img src="https://icon.cnzz.com/img/pic.gif" alt="">
                    </p>
                </li>
                <li class="text-center">
                    <img src="https://www.51zxw.net/NewAn/Content/Image/footPic1.png" alt="">
                    <img src="https://www.51zxw.net/NewAn/Content/Image/footPic2.png" alt="">
                </li>
            </ul>
        </footer>
    </div>
</div>

<script>
        //添加商品
        $("button.addToCart").click(function () {
            var bookId=$(this).attr('bookId')

            window.location.href= "${sessionScope.path}/book_ctiy/CartServlet?action=addItem&id="+bookId;

        })


    // 跳 到 指 定 的 页 码
    $("#searchPageBtn").click(function () {
        var pageNo = $("#pn_input").val();
        location.href = "${sessionScope.path}${requestScope.page.url}&pageNo=" + pageNo;
    });
</script>
</body>
</html>