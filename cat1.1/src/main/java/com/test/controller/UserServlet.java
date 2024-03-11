package com.test.controller;

import com.test.entity.vo.MessageModel;
import com.test.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    /**
     * 用户登录
     * 1，接收客户端请求（参数，姓名，密码）
     * 2，调用service层方法，返回消息模型对象
     * 3，判断消息的状态码
     * 如果失败则将消息模型对象设置到request作用域中，请求转发到login.jsp
     * 如果成功则将消息模型中的用户信息设置到session作用域中，重定向跳转到index.jsp
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1，接收客户端请求（参数，姓名，密码）
        String studentid = request.getParameter("studentid");
        String password = request.getParameter("password");
        // 2，调用service层方法，返回消息模型对象
        MessageModel messageModel = userService.userLogin(studentid, password);
        // 3，判断消息的状态码
        if (messageModel.getCode() == 1) { // 成功
            // 将消息模型中的用户信息设置到session作用域中，重定向跳转到index.jsp
            request.getSession().setAttribute("user", messageModel.getObject());
            response.sendRedirect("index.jsp");
        } else {  //失败
            // 将消息模型对象设置到request作用域中，请求转发到login.jsp
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
