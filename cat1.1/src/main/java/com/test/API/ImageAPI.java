package com.test.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageAPI {
    private static final Logger logger = Logger.getLogger(ImageAPI.class.getName());
    Parameter parameter = new Parameter();
    public static String getJSON(int albumId, int page, String order, String q) {
        Parameter parameter = new Parameter();
        StringBuilder response = null;
        HttpURLConnection connection = null;
        try {
            // API 请求 URL
            String apiUrl = parameter.getRequestImageAPIUrl();
            // 设置请求参数
            String token = parameter.getToken();
            // 构建 URL
            StringBuilder urlBuilder = new StringBuilder(apiUrl);
            if (albumId > 1) {
                urlBuilder.append("?album_id=").append(albumId);
            }
            if (page > 1) {
                urlBuilder.append("&page=").append(page);
            }
//            if (!order.equals("default")) {
//                urlBuilder.append("&order=").append(order);
//            }
            if (!q.equals("default")) {
                urlBuilder.append("&q=").append(q);
            }
            URL url = new URL(urlBuilder.toString());
            // 打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置请求头
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", token);
            connection.setRequestProperty("Accept", "application/json");
            // 获取响应代码
            int responseCode = connection.getResponseCode();
            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            // 打印响应
            System.out.println("request: " + connection);
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());
            reader.close();
            logger.log(Level.INFO, "Request: {0}", connection);
            logger.log(Level.INFO, "Response Code: {0}", responseCode);
            logger.log(Level.INFO, "Response Body: {0}", response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception Message: " + e.getMessage());
        } finally {
            // 关闭连接
            if (connection != null) {
                connection.disconnect();
            }
        }
        return response.toString();
    }
}
