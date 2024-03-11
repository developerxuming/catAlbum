package com.test.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
/**
 * 图片上传功能
 */
@WebServlet("/upload")
@MultipartConfig
public class uploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 获取所有文件部分
            Collection<Part> fileParts = request.getParts();
            String selectedName = request.getParameter("name");
            // 检查名字是否为空
            if (selectedName == null || selectedName.trim().isEmpty()) {
                throw new IllegalArgumentException("名字不能为空");
            }
            // 指定保存文件的目录
            String uploadDirectory = "storage" + File.separator + selectedName;
            // 如果目录不存在，则创建目录
            File uploadDir = new File(uploadDirectory);
            if (!uploadDir.exists()) {
                if (!uploadDir.mkdirs()) {
                    throw new IOException("无法创建目录: " + uploadDirectory);
                }
            }
            // 遍历所有文件
//            int len = fileParts.size();
//            int number = 0;
            for (Part filePart : fileParts) {
                // 从文件部分获取文件名
                String fileName = getSubmittedFileName(filePart);
                // 创建文件路径
                String filePath = uploadDirectory + File.separator + fileName;
                // 将文件复制到指定路径
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
//                number++;
//                if (len > 1) {
//                    response.getWriter().println("第" + number + "个文件上传成功！（共" + len + "个文件");
//                }
            // 响应客户端
            response.getWriter().println("所有文件上传成功！");
        } catch (Exception e) {
            e.printStackTrace();
            // 在上传失败时返回错误消息
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("文件上传失败: " + e.getMessage());
        }
    }

    // 辅助方法：获取提交的文件名
    private String getSubmittedFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}