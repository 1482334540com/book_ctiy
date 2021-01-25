
<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <%--引入资源--%>
    <link rel="icon" type="image/png" href="/book_ctiy/book/img/favicon.png"/>
    <%@ include file="/book/common/resoures.jsp"%>
    <title>图书管理页面</title>
    <style>
        tr td{
            text-align: center;
        }
    </style>

</head>
<body>
<div class="container-fluid">

    <!--    导航条部分-->
    <%@ include file="/book/common/header.jsp"%>
    <!--    数据表格展示-->

    <div class="col-lg-11 ">

        <div style="position: relative;margin-bottom: 20px ;margin-top: 80px" class="text-center" >
            <input type="text" id="search" placeholder="请输入书名" >
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
                <th class="text-center">书名</th>
                <th>作者</th>
                <th>价格</th>
                <th>销量</th>
                <th>库存</th>
                <th class="text-center">操作</th>
            </tr>
            </thead>
            <tbody id="tbody" >


                <c:forEach  items="${sessionScope.bookList}" var="book" >
                    <tr>
                        <td class="hidden">${book.id}</td>
                        <td>${book.name}</td>
                        <td>${book.author}</td>
                        <td>${book.price}</td>
                        <td>${book.sales}</td>
                        <td>${book.stock}</td>


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
                        <h4 class="modal-title text-center text-primary btn btn-success" id="myModalLabel">添加图书</h4>
                    </div>
                    <div class="modal-body .box" id="input-content">

                        <form id="serialize_list">
                            <p>	<button class="btn btn-info text-center" style="width: 60px;height: 30px;">书名: </button>    <input name="name"  type="text" value="jquery网页制作"  class="input-sm" ></p>
                            <p>	<button class="btn btn-info text-center" style="width: 60px;height: 30px;">价格: </button>  <input name="price" type="text" value="15" ></p>
                            <p><button class="btn btn-info text-center" style="width: 60px;height: 30px;">作者 : </button> <input name="author"  type="text" value="贝瓦夸" ></p>
                            <p><button class="btn btn-info text-center" style="width: 60px;height: 30px;">销量: </button> <input name="sales"  type="text" value="98"  ></p>
                            <p><button class="btn btn-info text-center" style="width: 60px;height: 30px;">库存: </button> <input name="stock"  type="text" value="91"  ></p>
<%--                            <p><button class="btn btn-info text-center" style="width: 60px;height: 30px;">封面: </button>  <input  name="img" type="text"  value="null" ></p>--%>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

                                <button type="button" id="add_data" class="btn btn-success">提交更改</button>

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
            $.post("/book_ctiy/bookServlet","action=addBook&" + $("#serialize_list").serialize(),function () {

                location.href="/book_ctiy/bookServlet?action=queryBook";
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
                $.post("/book_ctiy/bookServlet","action=delBook&id="+id,function (data) {
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
            $.post("/book_ctiy/bookServlet","action=likeBook&name=" +input_val ,function () {
                location.href="bookManager.jsp";
            });


        })

        //修改功能
        var arr=new Array(6);

        $(".update").click(function () {
            for (var i = 0;i<=5;i++){
                var td_val=$(this).parent().parent().find("td:eq("+i+")").text();
                $(this).parent().parent().find("td:eq("+i+")").html("<input type='text' value='"+td_val+"'>");
            }
            $(this).parent().find(".update").hide();
            $(this).parent().find(".del").hide();
            $(this).parent().find(".noupdate").show();
            $(this).parent().find(".updatein").show();
            //取消操作
            $(this).parent().find(".noupdate").click(function () {
                for (var i = 0;i<=5;i++){
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
            for (var i = 0 ; i<=5; i++){
                var ip_val =$(this).parent().parent().find("td:eq("+i+")>input:text").val();
                arr[i] =ip_val;
                console.log(arr);
            }
            $.post("/book_ctiy/bookServlet?action=updateBook",{

                id:arr[0],
                name:arr[1],
                author:arr[2],
                price :arr[3],
                sales:arr[4],
                stock:arr[5],

            },function () {
                location.href="/book_ctiy/bookServlet?action=queryBook";
            });

        })
    })


</script>

</body>
</html>