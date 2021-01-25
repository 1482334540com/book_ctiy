package com.javaweb.bookMall.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/orderServlet")
public class OrderItemFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpSession session = httpServletRequest.getSession();
        Object username = session.getAttribute("username");
        String usernames = (String) username;

        if(username!=null){
            chain.doFilter(req, resp);
        }
        else{
            req.getRequestDispatcher("/book/Login.jsp").forward(req,resp);
        }




    }

    public void init(FilterConfig config) throws ServletException {

    }

}
