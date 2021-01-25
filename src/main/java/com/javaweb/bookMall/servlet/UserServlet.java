package com.javaweb.bookMall.servlet;


import com.google.gson.Gson;
import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.service.Impl.UserManagerServiceImpl;
import com.javaweb.bookMall.service.UserService;
import com.javaweb.bookMall.service.Impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet("/admin/userServlet")
public class UserServlet extends BaseServlet {
    //创建Service成对象，调用相应的方法处理相应的请求
    UserService userService =  new UserServiceImpl ();

  //处理登录业务
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println(req.getServletContext().getContextPath());

      //获取请求参数
      String username = req.getParameter("username");
      String password = req.getParameter("password");
      //调用service层验证用户名和密码是否正确
      Boolean flag = userService.queryUsernameAndPassword(username, password);

      if(flag){

        //根据用户名查找user对象 将user对象放到session域中
        UserManagerServiceImpl userManager = new UserManagerServiceImpl();
        User user = userManager.queryOneUser(username);
        req.getSession().setAttribute("user",user);
        req.getSession().setAttribute("username",username);
        resp.sendRedirect("/book_ctiy/index.jsp");

      }else{
        req.setAttribute("log_message","用户名或密码错误，请重新输入");
        req.getRequestDispatcher("/book/Login.jsp").forward(req,resp);

      }

    }

  //处理注销用业务
  protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.getSession().removeAttribute("username");
       resp.sendRedirect("/book_ctiy/book/Login.jsp");

  }

    //处理注册业务
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String username = req.getParameter("username");
      String password = req.getParameter("password");
      String email = req.getParameter("email");
      String phone = req.getParameter("phone");
      String code = req.getParameter("code");


      //获取客户端生成的验证码
      String code_google =(String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
      //删除客户端验证码
      req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
      //验证码正确
      if(code.equalsIgnoreCase(code_google)&&code!=null){
        //用户名可用
        if(userService.queryUsername(username)){
          //保存用户信息到数据库
          userService.saveUserInformation(new User(null,username,password,email,phone));
          resp.sendRedirect("/book_ctiy/bookServlet?action=paging");
        }
        else{
          req.setAttribute("registerMessage","用户名已存在");
          req.getRequestDispatcher("book/Registered.jsp").forward(req,resp);
        }

      }else{
        req.setAttribute("registerMessage","验证码错误");
        req.getRequestDispatcher("/book/Registered.jsp").forward(req,resp);
      }



    }
    //处理验证用户名是否可用业务
    protected void ajaxValidationUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String username = req.getParameter("username");
      Boolean flag = userService.queryUsername(username);

      Map<String,Boolean> resultName = new HashMap<String,Boolean>();
      resultName.put("resultName",flag);
      Gson gson = new Gson();
      String jsoName = gson.toJson(resultName);
      resp.getWriter().write(jsoName);
    }




}
