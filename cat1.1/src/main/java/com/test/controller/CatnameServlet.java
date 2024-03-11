package com.test.controller;

import com.test.entity.vo.CatModel;
import com.test.service.CatloadService;
import com.test.util.StringModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/catName")
/**
 * 返回猫的名字信息
 */
public class CatnameServlet extends HttpServlet {
    private CatloadService catloadService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CatModel catModel = CatloadService.catread();

        StringModel stringModel = new StringModel();
        stringModel.setStr(catModel.getNames());
        // 将数据传递给前端需要将数据放入 request 中
        request.setAttribute("nameModel", stringModel);
        // 转发到前端页面
        request.getRequestDispatcher("3.jsp").forward(request, response);
    }
}
