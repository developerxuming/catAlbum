package com.test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class imageAPI {
    public static String api(int albumId, int page, String order) {
        StringBuilder response = null;
        try {
            // API 请求 URL
            String apiUrl = "https://wmimg.com/api/v1/images";
            // 设置请求参数
            String token = "Bearer 95|6nk2pFUcl4P2I36kOblyP1Y3obTP8Sv8dWBVrKeR";
            // 构建 URL
            StringBuilder urlBuilder = new StringBuilder(apiUrl);
            urlBuilder.append("?album_id=").append(albumId);

            if (page > 1) {
                urlBuilder.append("&page=").append(page);
            }
            if (!order.equals("default")) {
                urlBuilder.append("&order=").append(order);
            }
            URL url = new URL(urlBuilder.toString());
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
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
            // 关闭连接
            connection.disconnect();
            // 打印响应
            System.out.println("request: " + connection);
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception Message: " + e.getMessage());
        }
        return response.toString();
    }
}
