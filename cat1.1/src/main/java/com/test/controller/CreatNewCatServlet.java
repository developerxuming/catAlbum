package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.vo.CategoryFeedbackModel;
import com.test.service.ProcessCategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/newCat")
@MultipartConfig
public class CreatNewCatServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CreatNewCatServlet.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取字符输入
        request.setCharacterEncoding("UTF-8");
        System.out.println("newCat后端处理中");
        // 获取参数
        String variety = request.getParameter("variety"); // 品种信息
        String name = request.getParameter("name");  // 名字
        String gender = request.getParameter("gender");  // 性别
        String age = request.getParameter("age");  // 年龄
        String address = request.getParameter("address");  // 生活地区
        Integer albumId = null;
        if (request.getParameter("albumId") != null) {
            albumId = Integer.valueOf(request.getParameter("albumId")); // 相册
        }
        String neutered = request.getParameter("neutered");
        String region = request.getParameter("region");
        String healthy = request.getParameter("healthy");
        String appearance = request.getParameter("appearance");  // 外貌特征
        String other = request.getParameter("other");  // 性格习性信息
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
        // 获取文件部分
        Part imagePart = request.getPart("image");  // 第一张描述图片
        // 获取文件输入流
        InputStream imageInputStream = imagePart.getInputStream();
        // 将文件内容读取为字节数组
        byte[] imageBytes = imageInputStream.readAllBytes();
        CategoryFeedbackModel categoryFeedbackModel = ProcessCategoryService.processUpload(name, imageBytes, albumId, age, variety, gender,
                appearance, neutered, healthy, address, region, other, isAdmin);

//        request.setAttribute("categoryFeedbackModel", categoryFeedbackModel);
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(categoryFeedbackModel);
        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
    }
}
