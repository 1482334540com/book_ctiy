<%--jsp标准代码--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ include file="/book/common/resoures.jsp"%>
    <title>注册</title>
    <style>
        /*注册背景*/
        body{
            background-image: url("/book_ctiy/book/img/registered.jpg") ;
            background-size: 100% 100%;
            background-repeat:no-repeat ;
            background-position:0 0px ;
            background-attachment: fixed;

        }

        /*注册*/
        .form {

            background-color: rgba(225,225,225,0.9);
            margin-top: 8%;
            font-size: 18px;
        }



        .registered-box {
            margin-left: 20px;
            margin-top: 25px;
        }

        label {
            margin-right: 20px;
            margin-top: 30px;
        }

        .condition-filter {
            margin-top: 2%;

        }

        .btn-group-justified {
            height: 50px;
            line-height: 40px;
        }

        .input-box {
            display: inline-block;
        }

        .form-control {
            margin-top: 30px;
        }

        .control-label {
            margin-left: 20px;
        }

        .btn-i {
            background-color: rgba(225, 225, 225, 0.9);
            border: none;
        }
        .input-width{
            width: 10%;height: 10%;
        }


    </style>
</head>
<body>
<div class="container-fluid">

    <div class="row  screen">
        <div class="col-lg-4 col-lg-offset-4  col-sm-8  col-sm-offset-2    form ">

          <form class="form-inline " role="form" action="/book_ctiy/admin/userServlet"   method="post">

              <input type="hidden" name="action" value="register">
                <div id="" class="form-group ">
                    <div class="registered-box">
                        <h2 class="registered">欢迎注册</h2>
                        <span>已有账号？<a href="Login.jsp">登录</a></span>
                        <span style="margin-left: 8px" class="msg text-danger">${requestScope.registerMessage}</span>
                    </div>

                    <div class="form-group">
                        <div class="input-box">
                            <label  class=" control-label">手机号</label>
                            <input type="text" class="form-control" class="input-width" name="phone" value="1008633" placeholder="可用于登录和找回密码">

                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="input-box">
                            <label  class=" control-label">邮<span style="color: rgba(225,225,225,0.1);">11</span>箱</label>
                            <input type="text" class="form-control input-width" name="email" value="admin@qq.com"  placeholder="方便获取最新消息">
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="input-box">
                            <label  class=" control-label">用户名</label>
                            <input type="text" class="form-control input-width" id="username" name="username" placeholder="请设置用户名">
                        </div>
                        <button type="button" class="btn btn-default glyphicon glyphicon-exclamation-sign btn-i" data-container="body"
                                data-toggle="popover" data-placement="right" data-content="长度为8~14个字符
																字母/数字以及标点符号至少包含2种
																不允许有空格、中文"
                                style="position: relative;top: 15px;">
                        </button>
                    </div>

                    <div class="form-group">
                        <div class="input-box">
                            <label  class=" control-label">密<span style="color: rgba(225,225,225,0.1);">11</span>码</label>
                            <input type="text" id="password" class="form-control input-width" name="password" value="admin123?"  placeholder="请设置登录密码">

                            <button type="button" class="btn btn-default glyphicon glyphicon-exclamation-sign btn-i" data-container="body"
                                    data-toggle="popover" data-placement="right" data-content="长度为8~14个字符
																字母/数字以及标点符号至少包含2种
																不允许有空格、中文"
                                    style="position: relative;top: 15px;">
                            </button>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="input-box">
                            <label  class=" control-label ">验证码</label>
                            <input type="text" class="form-control"  name="code" placeholder="请输入验证码">
                            <img src="/book_ctiy/kaptcha.jpg" id="validation" style="margin-top: 22px" width="75px" height="35px" alt="">
                            <button type="button" class="btn btn-default form-control" id="code_img" >更换验证码</button>
                        </div>
                    </div>
               </div>
              <div class="condition-filter text-center">
                  <button type="submit" id="submit" class="btn btn-group-justified btn-info">立即注册</button>
                  <br>
                  <input type="checkbox" name="deal" id="optionsRadios4" value="option2">阅读并接受
                  <a href="https://passport.baidu.com/static/passpc-account/html/protocal.html">&lt;&lt;IT用户协议&gt;&gt;</a> <a class="btn btn-success pull-right" role="button" href="/book_ctiy/bookServlet?action=paging">返回首页</a>
              </div>
            </form>


        </div>
    </div>
</div>
<script type="text/javascript">
    $(function() {
        //调用提示功能
        $(function () {
            $("[data-toggle='popover']").popover();
        });

        //给注册按钮绑定点击事件，如果返回false就不会提交表单
        $("#submit").click(function () {

            //获取是否同意协议的input框值
            var flag = $("input[name='deal']:checked").is(':checked')?true:false;
            if (flag){
                return true;  //return true 则表单不会提交
            }
            else{
                $(".msg").text("请勾选IT用户协议！");
                return  false;  //return false则表单不会提交
            }

        })

        //验证密码是否达到要求
        $("#password").blur(function () {
            // 验证密码：必须由字母，数字特殊字符线组成，并且长度为5到12位
            //1 获取用户名输入框里的内容
            var passwordText = $("#password").val();
            //2 创建正则表达式对象
            var passwordPatt = /([a-zA-Z0-9][!@#$%^&()*])|([!@#$%^&()*][a-zA-Z0-9])+/;
            //3 使用test方法验证
            if (!passwordPatt.test(passwordText)) {
                //4 提示用户结果
                $(".msg").text("密码不符合要求");
                return false;
            }
        });

        // 用ajax发送请求到服务器验证用户名是否可用
        $("#username").blur(function () {
            if(this.value!=""){
                var username = this.value;
                $.getJSON("/book_ctiy/admin/userServlet","action=ajaxValidationUsername&username=" + username,function (data) {
                    console.log(data)
                    if (!data.resultName) {
                        $(".msg").text("用户名已存在！请重新更换用户名");
                        return false;
                    } else {
                        $(".msg").text("用户名可用！");
                    }
                });
            }
            else{
                $(".msg").text("请输入用户名");
            }
        });
        //验证用户是否输入验证码
        $("input[name='code']").blur(function () {
            if(this.value == ""){
                $(".msg").text("请输入验证码");
            }
        })

        //点击图片更换验证码，new一个data对象，传递一个随机参数保证每次请求的参数不一样，请求回来的图片都不一样
        $("#code_img").click(function () {

            $("#validation").prop("src","/book_ctiy/kaptcha.jpg?data=" + new Date())

        })


    });


</script>
</body>
</html>