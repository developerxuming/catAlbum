package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.test.entity.entityAPI.ImageSearchInfo;
import com.test.service.ImageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 后端，这是负责对相册内容的更新
 * 需求表
 * 1，在进入页面时：page=0，album_id=0，order="newest“，q=‘null’
 * 2，未选择相册时点击下一页：page++，album_id=0，order="newest“，q=‘null’
 * 3，未选择相册时选择任意页面时：page=n，album_id=0，order="newest“，q=‘null’
 * 4，选择了某个相册时：page=0，album_id=x，order="newest“，q=‘null’
 * 5，选择了某个相册的下一页时：page++，album_id=x，order="newest“，q=‘null’
 * 6，选择相册时选择任意页面时：page=n，album_id=x，order="newest“，q=‘null’
 *
 * 页面读取流程表
 * 1，首先进入页面时，或直接从链接进入页面时
 * 2，初始化页面选择第1条需求
 * 3，如果读取到，输出message，并继续加载图片
 * 4，加载图片后，记录此时的page，album_id，order="newest“，q=‘null’，current_page，last_page，per_page，total组成信息集合
 * 5，产生需求2时
 * 6，将记录的信息集合发送至后端，后端开始判断
 * 7，如果选择了没有的页数（如在第一页选前一页，最后一页选下一页，随机选择没有的页码），则不刷新网页，返回没有更多内容了的弹幕
 * 8，如果选择了有的页码则刷新进入需求2,3,5,6中的对应页码进入流程3.4
 *
 * 后端处理逻辑
 * 1，收到发送的内容将内容转化为int或String
 * 2，发送至service，返回带有message的imageSearchInfo
 * 3，如果imageSearchInfo中只有message而没有其他内容，则不刷新页面
 * 4，如果imageSearchInfo中message为success，则正常刷新
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doPost method called...");
        // 获取请求参数
        String albumIdParameter = request.getParameter("albumId");
        String pageParameter = request.getParameter("page");
        String order = request.getParameter("order");
        // 更改请求参数的格式
        int albumId = 0;
        int page = 0;
        if (!albumIdParameter.equals("0") && !albumIdParameter.isEmpty()) {
            albumId = Integer.parseInt(albumIdParameter.trim());
        }
        if (!pageParameter.equals("0") && !albumIdParameter.isEmpty()) {
            page = Integer.parseInt(pageParameter.trim());
        }

        System.out.println("page:" + page);
        System.out.println("albumId: " + albumId);

        // 调用加载图片信息的方法
        String json = ImageService.loadImage(albumId, page, order);
//        ImageSearchInfo imageSearchInfo = ImageService.loadImage(albumId, page, order);
//        request.setAttribute("imageSearchInfo", imageSearchInfo);
//        request.getRequestDispatcher("imageWall.jsp").forward(request, response);
        // 设置响应的内容类型为 JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("json", json);
        // 将 JSON 字符串作为响应发送给前端
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}


