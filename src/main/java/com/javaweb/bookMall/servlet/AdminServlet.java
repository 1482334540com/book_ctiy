package com.javaweb.bookMall.servlet;

import com.google.gson.Gson;
import com.javaweb.bookMall.bean.User;
import com.javaweb.bookMall.service.Impl.UserManagerServiceImpl;
import com.javaweb.bookMall.service.UserManagerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userManagerServlet")
public class AdminServlet extends BaseServlet {

    UserManagerService userManagerService =new UserManagerServiceImpl();

    protected void queryUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> userList = userManagerService.queryUser();
        req.getSession().setAttribute("userList",userList);
        resp.sendRedirect("/book_ctiy/book/admin/userManager.jsp");

    }

    protected void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Boolean flag = userManagerService.addUser(new User(null, username, password, email, phone));


    }
    protected void delUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Boolean flag = userManagerService.delUser(id);

        List<User> userList = userManagerService.queryUser();
        req.getSession().setAttribute("userList",userList);


        Map<String,Boolean> resultFlag = new HashMap<String,Boolean>();
        resultFlag.put("resultFlag",flag);

        Gson gson = new Gson();
        String jsonFlag = gson.toJson(resultFlag);
        resp.getWriter().write(jsonFlag);



    }

    protected void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Integer id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        Boolean flag = userManagerService.updateUser(new User(id, username, password, email, phone));





    }
    protected void likeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        List<User> userList = userManagerService.likeUser(username);
        req.getSession().setAttribute("userList",userList);


    }
}
