package com.test.controller;

import com.test.entity.vo.MessageModel;
import com.test.service.SignService;
import com.test.util.datatime;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@WebServlet("/Signin")
public class SigninServlet extends HttpServlet {
    private SignService signService = new SignService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer gender;
        String studentid = request.getParameter("studentid");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String genderstr = request.getParameter("gender");
//        Integer gender = Integer.valueOf(request.getParameter("gender"));
        String college = request.getParameter("college");

        if (Objects.equals(genderstr, "保密")) {
            gender = 0;
        } else if (Objects.equals(genderstr, "女")) {
            gender = 1;
        } else {
            gender = 2;
        }
        // 获取当前时间
        LocalDateTime currentDateTime = datatime.getCurrentDateTime();

        // 格式化为数据库中的 datetime 字符串
        String formattedDateTime = datatime.formatDateTimeForSQL(currentDateTime);
        MessageModel messageModel = SignService.userSignin(studentid, name, password, gender, college, formattedDateTime);

        if (messageModel.getCode() == 1) {
            request.getSession().setAttribute("user", messageModel.getObject());
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("messageModel", messageModel);
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }
}

