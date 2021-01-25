<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/book/common/resoures.jsp"%>
    <title>我的订单详情信息</title>
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
        tr td{
            text-align: center;
        }
        tr th{
            text-align: center;
        }

        /*导航条公共部分结束*/


    </style>

</head>
<body>
<div class="container-fluid">
    <!--    头部信息-->
    <div class="row">
        <div class="col-lg-3 col-md-3 ">
            <img id="cartLog" src="/book_ctiy/book/img/bookLog.jpg" alt="">
        </div>

        <div class="col-lg-3 col-md-3">
            <h1 id="header_title" class="text-info"></h1>
        </div>

        <div class="col-lg-6 col-md-6">
            <nav class="navbar  ">
                <ul id="navUl">
                    <li> 欢迎</li>
                    <li class="text-danger"> admin</li>
                    <li> 光临故事书城</li>
                    <li><a href="/book_ctiy/bookServlet?action=paging">返回首页</a></li>
                </ul>
            </nav>
        </div>
    </div>

    <!--    购物车内容-->
    <div class="row">
        <div class="col-lg-8 col-md-8 col-0                                                                                                  lg-offset-2 col-md-offset-2">
            <table  border="1" class=" table table-hover">
                <thead >
                <tr class="bg-primary">
                    <th>下单日期日期</th>
                    <th>商品金额</th>
                    <th>商品状态</th>
                    <th>商品详情信息</th>

                </tr>
                </thead>

                    <tbody>
                    <c:forEach items="${requestScope.myOrders}" var="order">


                        <c:if test="${order.status == 0}">
                            <c:set var="status" value="未发货"/>
                        </c:if>

                        <c:if test="${order.status == 1}">
                            <c:set var="status" value="已发货"/>

                        </c:if>

                        <c:if test="${order.status == 2}">
                            <c:set var="status" value="待签收"/>
                        </c:if>

                        <c:if test="${order.status == 3}">
                            <c:set var="status" value="已签收"/>
                        </c:if>
                    <tr>
                        <td>${order.createTime}</td>
                        <td>${order.price}</td>

                        <td>
                                ${status}
                            <c:if test="${order.status == 1}">
                                <a  href="/book_ctiy/orderServlet?action=receivingOrder&orderId=${order.orderId}" class="btn btn-success " role="button"  style="margin-left: 15px;">确认签收</a>
                            </c:if>

                        </td>
                        <td><a href="/book_ctiy/orderServlet?action=showOrderDetails&orderId=${order.orderId}">查看详情</a></td>
<%--                        <td><a href="/book/orderDetails.jsp">查看详情</a></td>--%>

                    </tr>
                    </c:forEach>
                    </tbody>


            </table>
        </div>

    </div>


    <!--    网页尾部-->
    <div class="row">
        <footer class="bg-warning" role="navigation"  style="margin-top: 150px;">
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
    $(function () {
        //头部标题随着title变化
        $("#header_title").html($("title").html());

    })
</script>
</body>
</html>