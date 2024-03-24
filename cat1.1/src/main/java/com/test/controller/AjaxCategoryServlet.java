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
import java.util.ArrayList;
import java.util.List;

/**
 *全新方法，读取前端ajax发送放入的请求根据其值的有无获取是否分类和分类信息
 */
@WebServlet("/getCategory")
public class AjaxCategoryServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String region = request.getParameter("region"); // 获取地区信息请求
        if (region == null) {
            region = "";  //  如果没有region请求，则将regin1设为空，即返回所有地区
        }
        List<Category> categories = CatCategoryService.readCatCategory(region);// 获取目录分类信息
        // 使用 Jackson ObjectMapper 将 List 转换为 JSON 字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(categories);
        System.out.println(json);
        // 设置响应内容类型为 JSON
        response.setContentType("application/json");
        // 将 JSON 字符串写入到响应中
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
