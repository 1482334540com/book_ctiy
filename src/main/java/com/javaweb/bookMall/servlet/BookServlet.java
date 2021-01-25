package com.javaweb.bookMall.servlet;

import com.google.gson.Gson;
import com.javaweb.bookMall.bean.Book;
import com.javaweb.bookMall.bean.Paging;
import com.javaweb.bookMall.service.BookService;
import com.javaweb.bookMall.service.Impl.BookServiceImpl;
import com.javaweb.bookMall.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javaweb.bookMall.bean.Paging.PAGE_SIZE;

@WebServlet("/bookServlet")
public class BookServlet  extends BaseServlet{


    //创建Service层对象
    BookService bookService =new BookServiceImpl();

    protected void queryBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> bookList = bookService.queryAll();
        req.getSession().setAttribute("bookList",bookList);
         resp.sendRedirect("/book_ctiy/book/admin/bookManager.jsp");


    }


    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Integer price = WebUtils.parseInt(req.getParameter("price"),0);
        String author = req.getParameter("author");
        Integer stock = WebUtils.parseInt(req.getParameter("stock"),0);
        Integer sales= WebUtils.parseInt(req.getParameter("sales"),0);


        Boolean flag = bookService.addBook(new Book(null, name, author, price, sales, stock, null));



    }
    protected void delBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Boolean flag = bookService.deleteBook(id);

        List<Book> bookList = bookService.queryAll();
        req.getSession().setAttribute("bookList",bookList);


        Map<String,Boolean> resultFlag = new HashMap<String,Boolean>();
        resultFlag.put("resultFlag",flag);

        Gson gson = new Gson();
        String jsonFlag = gson.toJson(resultFlag);
        resp.getWriter().write(jsonFlag);

    }

    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name = req.getParameter("name");
        String author = req.getParameter("author");
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer price = Integer.parseInt(req.getParameter("price"));
        Integer sales = Integer.parseInt(req.getParameter("sales"));
        Integer stock = Integer.parseInt(req.getParameter("stock"));
        System.out.println("id："+id+",price："+price+",sales："+sales+",stock："+stock+"name"+name+"author"+author);

       bookService.updateBook(new Book(id,name,author,price,sales,stock,null));




    }
    protected void likeBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        List<Book> bookList = bookService.likeBook(name);
        req.getSession().setAttribute("bookList",bookList);
        resp.sendRedirect("/book_ctiy//book/admin/bookManager.jsp");

    }

    //分页查询
    protected void paging(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        String pageNo = req.getParameter("pageNo");
        String pageSize = req.getParameter("pageSize");

        Paging<Book> page = bookService.page(WebUtils.parseInt(pageNo, 1), WebUtils.parseInt(pageSize, PAGE_SIZE));
//        System.out.println("当前页："+pageNo+"当前页数据："+page);
        page.setUrl("/book_ctiy/bookServlet?action=paging");
        req.setAttribute("page",page);

        req.getRequestDispatcher("book/index.jsp").forward(req,resp);

    }
    //价格区间查询
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        /*1.获取请求的参数pageNO和pageSize*/
        int pageNO= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize= WebUtils.parseInt(req.getParameter("pageSize"), PAGE_SIZE);
        int min= WebUtils.parseInt(req.getParameter("min"),0);
        int max= WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        if (min>max){
            min=0;
            max=Integer.MAX_VALUE;
        }

        /* 2.调用bookServices.page(pageNO，pageSize)得到page对象*/
        Paging<Book> page = bookService.pageByPrice(pageNO,pageSize,min,max);
        StringBuilder sb = new StringBuilder("/book_ctiy/bookServlet?action=pageByPrice");
        // 如果有最小价格的参数,追加到分页条的地址参数中
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        // 如果有最大价格的参数,追加到分页条的地址参数中
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        req.setAttribute("page",page);
        /*4.请求转发到/pages/manager/book_edit.jsp页面中*/
        req.getRequestDispatcher("/book/index.jsp").forward(req,resp);



    }



}
