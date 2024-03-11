package com.test.test;

import com.test.API.AlbumAPI;
import com.test.API.ImageAPI;
import com.test.API.Parameter;
import com.test.entity.entityAPI.AlbumSearchInfo;
import com.test.entity.entityAPI.ImageSearchInfo;
import com.test.entity.entityAPI.ImageInfo;
import com.test.entity.entityAPI.LinksInfo;
import com.test.parsingJSON.ReadAlbum;
import com.test.parsingJSON.ReadImage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageListApiClient {

    public static void main(String[] args) {
        String json = null;
        try {
            int albumId = 41;
            int page = 0;
//            String order = "newest";
            String q = "default";
            String order = "default";

            json = callApi(albumId, page, order, q);
//            json = AlbumAPI.getJSON(page, order, q);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageListApiClient imageListApiClient = new ImageListApiClient();
        ImageSearchInfo s = ReadImage.parseJson(json);
//        AlbumSearchInfo s = ReadAlbum.parseJson(json);
        int aaaa = 11;
    }

    private static final Logger logger = Logger.getLogger(ImageAPI.class.getName());
    public static String callApi(int albumId, int page, String order, String q) {
        Parameter parameter = new Parameter();
        StringBuilder response = null;
        HttpURLConnection connection = null;
        try {
            // API 请求 URL
//            String apiUrl = "https://wmimg.com/api/v1/images";
            String apiUrl = parameter.getRequestImageAPIUrl();
            // 设置请求参数
            String token = parameter.getToken();
            // 构建 URL
            StringBuilder urlBuilder = new StringBuilder(apiUrl);
            urlBuilder.append("?album_id=").append(albumId);

            if (page > 1) {
                urlBuilder.append("&page=").append(page);
            }
            if (!order.equals("default")) {
                urlBuilder.append("&order=").append(order);
            }
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

    public static ImageSearchInfo parseJson(String json) {
        // JSON 数据字符串
        String jsonData = json;
        // 解析JSON字符串
        JSONObject jsonObject = new JSONObject(jsonData);
        // 获取"data"下的"links"的值
        //描述信息
        String message = jsonObject.getString("message");
        JSONObject dataObject = jsonObject.getJSONObject("data");  //读取第一部分data，该部分data包含了页码信息，和图片信息的json
        Integer current_page = (Integer) dataObject.get("current_page");  //当前所在页页码
        Integer last_page = (Integer) dataObject.get("last_page");  // 最后一页页码
        Integer per_page = (Integer) dataObject.get("per_page");  //每页展示数据数量
        Integer total = (Integer) dataObject.get("total");  //图片总数量
        System.out.println(current_page);
        System.out.println(last_page);
        System.out.println(per_page);
        System.out.println(total);
        JSONArray dataArray = dataObject.getJSONArray("data");  // 从 JSON 对象中获取名为 "data" 的字段
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
        ArrayList<LinksInfo> linksInfos = new ArrayList<>();
        System.out.println(dataArray);
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject imageObject = dataArray.getJSONObject(i);
            String key = imageObject.getString("key");  //图片唯一密钥
            String name = imageObject.getString("name");  //图片名称
            String originName = imageObject.getString("origin_name");  //图片原始名称
            String pathname = imageObject.getString("pathname");  //图片路径名
            float size = imageObject.getFloat("size");  // 图片大小，单位 KB
            int width = imageObject.getInt("width");  // 图片宽度
            int height = imageObject.getInt("height");  // 图片高度
            String md5 = imageObject.getString("md5");  // 图片 md5 值
            String sha1 = imageObject.getString("sha1");  // 图片 sha1 值
            String humanDate = imageObject.getString("human_date");  //  上传时间(友好格式)
            String date = imageObject.getString("date");  //  上传日期(yyyy-MM-dd HH:mm:ss)
            JSONObject links = imageObject.getJSONObject("links");  // 链接，与上传接口返回参数中的 links 相同
            String url = links.getString("url");
            String html = links.getString("html");
            String bbcode = links.getString("bbcode");
            String markdown = links.getString("markdown");
            String markdown_with_link = links.getString("markdown_with_link");
            String thumbnail_url = links.getString("thumbnail_url");
            String delete_url = links.getString("delete_url");
            LinksInfo linksInfo = new LinksInfo(url, html, bbcode, markdown, markdown_with_link, thumbnail_url, delete_url);
            ImageInfo imageInfo = new ImageInfo(key, name, originName, pathname, size, width, height, md5, sha1, humanDate, date, linksInfo);
            imageInfos.add(imageInfo);
        }
        ImageSearchInfo imageSearchInfo = new ImageSearchInfo(message,current_page, last_page, per_page, total, imageInfos);
        return imageSearchInfo;
    }
}

