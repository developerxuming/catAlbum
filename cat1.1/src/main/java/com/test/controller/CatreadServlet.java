package com.test.controller;

import com.test.entity.Category;
import com.test.service.CatCategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 废案,已废弃
 */
@WebServlet("/index")
public class CatreadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String temp = "";
        List<Category> Category = CatCategoryService.readCatCategory(temp);
        // 将数据传递给前端（这里你需要将数据放入 request 或 session 中）
        request.setAttribute("Category", Category);
        // 转发到前端页面
        request.getRequestDispatcher("Categories.jsp").forward(request, response);
    }
}
