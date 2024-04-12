package com.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.Category;
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
import java.util.Arrays;

@WebServlet("/renewCat")
@MultipartConfig
public class RenewCategoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取字符输入
        request.setCharacterEncoding("UTF-8");
        System.out.println("newCat后端处理中");
        // 获取参数
        String variety = request.getParameter("variety"); // 品种信息
        System.out.println("variety为：" + variety);
        String name = request.getParameter("name");  // 名字
        String oldName = request.getParameter("oldName");  // 名字
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
        byte[] imageBytes = null;
        // 获取文件输入流
        if (imagePart != null) {
            InputStream imageInputStream = imagePart.getInputStream();
            imageBytes = imageInputStream.readAllBytes();
        }
        Category category = new Category();
        category.setName(name);
        category.setVariety(variety);
        category.setGender(gender);
        category.setAge(age);
        category.setAddress(address);
        if (albumId != null) {
            category.setAlbumId(albumId);
        }
        category.setNeutered(neutered);
        category.setRegion(region);
        category.setHealthy(healthy);
        category.setAppearance(appearance);
        category.setOther(other);
        if (imageBytes != null && imageBytes.length != 0 && !Arrays.equals(imageBytes, new byte[0])){
            category.setImage(imageBytes);
        }
        // 将文件内容读取为字节数组
        CategoryFeedbackModel categoryFeedbackModel = ProcessCategoryService.processRenew(oldName, category, isAdmin);
//        request.setAttribute("categoryFeedbackModel", categoryFeedbackModel);
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(categoryFeedbackModel);
        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
    }
}
