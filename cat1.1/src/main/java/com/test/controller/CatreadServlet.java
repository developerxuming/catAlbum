package com.test.controller;

import com.test.entity.vo.CatModel;
import com.test.service.CatloadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 读取sql的流浪猫信息
 */
@WebServlet("/index")
public class CatreadServlet extends HttpServlet {
    private CatloadService catloadService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CatModel catModel = CatloadService.catread();
        // 将数据传递给前端（这里你需要将数据放入 request 或 session 中）
        request.setAttribute("catModel", catModel);
        // 转发到前端页面
        request.getRequestDispatcher("Categories.jsp").forward(request, response);
    }
}
