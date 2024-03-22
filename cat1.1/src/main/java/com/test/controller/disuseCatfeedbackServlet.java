package com.test.controller;

import com.test.entity.vo.FeedbackModel;
import com.test.service.Cat_feedbackService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
/**
 * 废弃
 */
@WebServlet("/uploadNewCat")
@MultipartConfig
public class disuseCatfeedbackServlet extends HttpServlet {
    private Cat_feedbackService catFeedbackService;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取字符输入
        String variety = request.getParameter("variety");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String message = request.getParameter("message");
        // 获取文件部分
        Part image1Part = request.getPart("image1");
        Part image2Part = request.getPart("image2");
        // 获取文件输入流
        InputStream image1InputStream = image1Part.getInputStream();
        InputStream image2InputStream = image2Part.getInputStream();
        // 将文件内容读取为字节数组
        byte[] image1Bytes = image1InputStream.readAllBytes();
        byte[] image2Bytes = image2InputStream.readAllBytes();
        FeedbackModel feedbackModel = Cat_feedbackService.save_feedback(name, variety, message, address, image1Bytes, image2Bytes);

        if (feedbackModel.getCode() == 1) {
            response.sendRedirect("congratulate_feedback.jsp");
        } else {
            request.setAttribute("feedbackModel", feedbackModel);
            request.getRequestDispatcher("newcat.jsp").forward(request, response);
        }
    }
}
