
<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%--引入资源--%>
    <link rel="icon" type="image/png" href="./img/favicon.png"/>
    <link rel="stylesheet" href="/book_ctiy/book/css/bootstrap.min.css">
    <link rel="stylesheet" href="/book_ctiy/bookcss/book.css">
    <script src="/book_ctiy/book/js/jquery-3.4.1.min.js"></script>
    <script src="/book_ctiy/book/js/bootstrap.min.js"></script>
    <title>用户管理页面</title>
    <style>
        tr td{
            text-align: center;
        }
    </style>

</head>
<body>
<div class="container-fluid">
    <!--    头部信息-->
    <%@ include file="/book/common/resoures.jsp"%>
    <!--    数据表格展示-->

    <div class="col-lg-11 ">

        <div style="position: relative;margin-bottom: 20px ;margin-top: 80px" class="text-center" >
            <input type="text" id="search" placeholder="请输入用户名" >
            <button class="btn-default btn-success" > <span class="glyphicon glyphicon-search"></span>搜索</button>
            <div class="pull-right operation_btn">
                <!-- 按钮触发模态框 -->
                <button class="btn btn-success " data-toggle="modal" data-target="#myModal"> <span class="glyphicon glyphicon-plus"></span>添加</button>

            </div>
        </div>
        <table border="1" class=" table table-hover" >
            <thead >
            <tr class="btn-primary">
                <th class="hidden">id</th>
                <th>用户名</th>
                <th>密码</th>
                <th>邮箱</th>
                <th>电话</th>

                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody id="tbody" >


            <c:forEach  items="${sessionScope.userList}" var="user" >
                <tr>

                    <td class="hidden">${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>
                        <button class='btn-danger noupdate '>取消</button>
                        <button class='btn-primary confirm_update updatein '>确认修改</button>
                        <button class=' btn-danger del' type="button">删除</button>
                        <button class='btn-primary update' type="button">修改</button>
                    </td>
                </tr>

            </c:forEach>


            </tbody>
        </table>

    </div>


    <div class="row">

        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title text-center text-primary btn btn-success" id="myModalLabel">添加用户</h4>
                    </div>
                    <div class="modal-body .box" id="input-content">

                        <form id="serialize_list">
                            <p>	<button class="btn btn-info text-center" style="width: 60px;height: 30px;">用户名: </button>    <input name="username"  type="text" value="root"  ></p>
                            <p>	<button class="btn btn-info text-center" style="width: 60px;height: 30px;">密码: </button>  <input name="password" type="text" value="root123" ></p>
                            <p><button class="btn btn-info text-center" style="width: 60px;height: 30px;">邮箱 : </button> <input name="email"  type="text" value="root@qq.com" ></p>
                            <p><button class="btn btn-info text-center" style="width: 60px;height: 30px;">电话 : </button> <input name="phone"  type="text" value="1008622" ></p>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button"  id="add_data" class="btn btn-success">提交更改</button>

                            </div>
                        </form>
                    </div>

                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
    </div>

</div>


<script type="text/javascript">
    $(function () {
        //一开始先让去修修改和确认修改隐藏
        $(".noupdate").hide();
        $(".updatein").hide();

        //添加功能
        $("#add_data").click(function () {
            // 把参数序列化

            $.post("/book_ctiy/userManagerServlet","action=addUser&" + $("#serialize_list").serialize(),function () {
                window.location.href = "/book_ctiy/userManagerServlet?action=queryUser";

            });
        });



        //删除功能
        $(".del").click(function () {
            // 提起获取到需要删除的哪一行
            var values =  $(this).parent().parent();
            if (confirm("确认删除?")){
                //获取id值
                var id = $(this).parent().parent().find("td:eq(0)").text();
                //通过ajax post请求 删除id
                $.post("/book_ctiy/userManagerServlet","action=delUser&id="+id,function (data) {
                    //将json字符串转化为js对象
                    var thisTr =JSON.parse(data);
                    if (thisTr.resultFlag){
                        values.remove();
                    }
                })
            }
        })

        //搜索功能 focusout:输入框失去焦点事件
        $("#search").focusout(function () {
            var input_val = $(this).val();
            $.post("/book_ctiy/userManagerServlet","action=likeUser&username=" +input_val ,function () {
                window.location.href="userManager.jsp";
            });


        })

        //修改功能
        var arr=new Array(5);

        $(".update").click(function () {
            for (var i = 0;i<5;i++){
                var td_val=$(this).parent().parent().find("td:eq("+i+")").text();
                $(this).parent().parent().find("td:eq("+i+")").html("<input type='text' value='"+td_val+"'>");
            }
            $(this).parent().find(".update").hide();
            $(this).parent().find(".del").hide();
            $(this).parent().find(".noupdate").show();
            $(this).parent().find(".updatein").show();
            //取消操作
            $(this).parent().find(".noupdate").click(function () {
                for (var i = 0;i<5;i++){
                    var input_val=$(this).parent().parent().find("td:eq("+i+")>input:text").val();
                    $(this).parent().parent().find("td:eq("+i+")").html(input_val);
                }
                $(this).parent().find(".update").show();
                $(this).parent().find(".del").show();
                $(this).parent().find(".noupdate").hide();
                $(this).parent().find(".updatein").hide();

            })
        })
        $(".confirm_update").click(function () {
            //获取修改后的所有input——value值
            for (var i = 0 ; i<5; i++){
                var ip_val =$(this).parent().parent().find("td:eq("+i+")>input:text").val();
                arr[i] =ip_val;
                console.log(arr);
            }
            $.post("/book_ctiy/userManagerServlet?action=updateUser",{

                id:arr[0],
                username:arr[1],
                password:arr[2],
                email :arr[3],
                phone:arr[4],


            },function () {
                window.location.href = "/book_ctiy/userManagerServlet?action=queryUser";
            });

        })
    })


</script>

</body>
</html>