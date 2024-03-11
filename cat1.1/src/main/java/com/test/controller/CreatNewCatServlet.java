package com.test.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@WebServlet("/newCat")
public class CreatNewCatServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CreatNewCatServlet.class.getName());
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取字符输入
        request.setCharacterEncoding("UTF-8");
        System.out.println("后端处理中");
        logger.info("variety: " + request.getParameter("variety"));
        logger.info("name: " + request.getParameter("name"));
        logger.info("gender: " + request.getParameter("gender"));
        logger.info("age: " + request.getParameter("age"));
        logger.info("address: " + request.getParameter("address"));
        logger.info("appearance: " + request.getParameter("appearance"));
        logger.info("other: " + request.getParameter("other"));

        String variety = request.getParameter("variety"); // 品种信息
        String name = request.getParameter("name");  // 名字
        String gender = request.getParameter("gender");  // 性别
        String age = request.getParameter("age");  // 年龄
        String address = request.getParameter("address");  // 生活地区
        String appearance = request.getParameter("appearance");  // 外貌特征
        String other = request.getParameter("other");  // 性格习性信息
        // 获取文件部分
        Part image1Part = request.getPart("image1");  // 第一张描述图片
        Part image2Part = request.getPart("image2");  // 第二张描述图片
        // 获取文件输入流
        InputStream image1InputStream = image1Part.getInputStream();
        InputStream image2InputStream = image2Part.getInputStream();
        // 将文件内容读取为字节数组
        byte[] image1Bytes = image1InputStream.readAllBytes();
        byte[] image2Bytes = image2InputStream.readAllBytes();
    }
}
