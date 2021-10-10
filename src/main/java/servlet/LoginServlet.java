package servlet;

import pojo.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Servlet 调用service层
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从请求中获取表单提交的参数
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        UserService userService = new UserService();
        if(id!=null&&password!=null) {
            //调用service层的login方法
            User user = userService.login(id, password);
            if(user != null) {
                resp.sendRedirect(req.getContextPath()+"/success.jsp");
            } else {
                req.getRequestDispatcher("fail.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
