package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Category;
import com.test.service.CatCategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getCategoryByName")
public class ReadCategoryByNameServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name"); // 获取地区信息请求
        System.out.println("name为：" + name);
        Category categories = CatCategoryService.readCatCategoryByName(name);// 获取目录分类信息
        // 使用 Jackson ObjectMapper 将 List 转换为 JSON 字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(categories);
//        System.out.println(json);
        // 设置响应内容类型为 JSON
        response.setContentType("application/json");
        // 将 JSON 字符串写入到响应中
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
