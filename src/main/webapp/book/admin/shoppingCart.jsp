<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/png" href="/book_ctiy/book/img/favicon.png"/>
    <%@ include file="/book/common/resoures.jsp"%>
    <title>购物车</title>
    <style>
        /*公共样式*/
        *{
            margin: 0;padding: 0;
            list-style-type:none
        }
        #clearCart{
            color: white;
        }
        #clearCart:hover{
            color: white;
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
        tr td{
            text-align: center;
        }
        tr th{
            text-align: center;
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
        .cart_info{
            position: absolute;left: 50%; font-size: 16px;
        }
        .updateCount{
            width: 50px;
        }
    </style>

</head>
<body>
<div class="container-fluid">
<!--    头部信息-->
    <div class="row">
      <div class="col-lg-3">
          <img id="cartLog" src="../img/bookLog.jpg" alt="">
      </div>

        <div class="col-lg-3">
            <h1 class="text-info">购物车</h1>
        </div>

        <div class="col-lg-6">
            <nav class="navbar  ">
                <ul id="navUl">
                    <li> 欢迎</li>
                    <li class="text-danger"> ${sessionScope.username}</li>
                    <li> 光临故事书城</li>
                    <li><a href="/book_ctiy/orderServlet?action=showMyOrder">我的订单</a></li>
                    <li><a href="/book_ctiy/bookServlet?action=paging">返回首页</a></li>
                </ul>
            </nav>
        </div>
    </div>

<!--    购物车内容-->
    <div class="row">
       <div class="col-lg-8 col-lg-offset-2">
           <table  border="1" class=" table table-hover">
               <thead>
               <tr class="bg-primary">
                   <th>商品名称</th>
                   <th>数量</th>
                   <th>单价</th>
                   <th>金额</th>
                   <th>操作</th>
               </tr>
               </thead>
               <tbody>

                <%--购物车不为空的情况下--%>
               <c:if test="${ not empty sessionScope.cart.items}">
                   <c:forEach  items="${sessionScope.cart.items}" var="entry">
                       <tr >
                           <td>${entry.value.name}</td>
                           <td><input class="updateCount text-center" bookId="${entry.value.id}" type="text" value="${entry.value.count}"></td>
                           <td>${entry.value.price}</td>
                           <td>${entry.value.totalPrice}</td>
                           <td>
                               <a class="btn-danger deleteItem btn" role="button" href="/book_ctiy/CartServlet?action=delItem&id=${entry.value.id}">删除</a>

                           </td>
                       </tr>
                   </c:forEach>

               </c:if>

                <%--如果购物车为空的情况--%>
               <c:if test="${ empty sessionScope.cart.items}">
                   <tr class="text-center">
                       <td colspan="5"><a href="/book_ctiy/index.jsp">亲，当前购物车为空！快跟小伙伴们去浏览商品吧！！！</a> </td>
                   </tr>
               </c:if>

               </tbody>
           </table>
           <%--如果购物车非空才输出页面的内容--%>
           <c:if test="${ not empty sessionScope.cart.items}">
               <div class="cart_info">
                   <span class="cart_span">购物车中共有 <span class="b_count text-danger">${sessionScope.cart.totalCount } </span>件商品</span>
                   <span class="cart_span">总金额 <span class="b_price text-danger">${sessionScope.cart.totalPrice} </span>元</span>
                   <span class="cart_span btn btn-danger"><a id="clearCart" href="/book_ctiy/CartServlet?action=clear">清空购物车中商品</a></span>
                   <a class="btn-success btn" role="button" href="/book_ctiy/orderServlet?action=createOrder">去结账</a>

               </div>
           </c:if>
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
        //清空购物车
        $("#clearCart").click(function () {
            return confirm("你确认要清空购物车么?");
        })
        //删除购物车商品
        $("a.deleteItem").click(function () {
            return confirm("你确认要删除[ "+$(this).parent().parent().find("td:first").text()+" ]图书么?");
        })

        //修改商品数量
        $(".updateCount").change(function () {
            // 获取商品名称
            var name = $(this).parent().parent().find("td:first").text();
            let id = $(this).attr('bookId');
            // 获取商品数量
            var count = this.value;
            if ( confirm("你确定要将【" + name + "】商品修改数量为：" + count + " 吗?") ) {
                //发起请求。给服务器保存修改
                location.href = "${sessionScope.path}CartServlet?action=updateCount&count="+count+"&id="+id;
            } else {
                // defaultValue属性是表单项Dom对象的属性。它表示默认的value属性值。
                this.value = this.defaultValue;
            }
        });
    })
</script>
</body>
</html>