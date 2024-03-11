package com.test.parsingJSON;

import com.test.entity.entityAPI.ImageSearchInfo;
import com.test.entity.entityAPI.ImageInfo;
import com.test.entity.entityAPI.LinksInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadImage {
    public static ImageSearchInfo parseJson(String json) {
        // JSON 数据字符串
        String jsonData = json;
        // 解析JSON字符串
        JSONObject jsonObject = new JSONObject(jsonData);
        //描述信息
        String message = jsonObject.getString("message");
        JSONObject dataObject = jsonObject.getJSONObject("data");  //读取第一部分data，该部分data包含了页码信息，和图片信息的json
        Integer current_page = (Integer) dataObject.get("current_page");  //当前所在页页码
        Integer last_page = (Integer) dataObject.get("last_page");  // 最后一页页码
        Integer per_page = (Integer) dataObject.get("per_page");  //每页展示相册数量
        Integer total = (Integer) dataObject.get("total");  //相册总数量
        JSONArray dataArray = dataObject.getJSONArray("data");  // 从 JSON 对象中获取名为 "data" 的字段
        ArrayList<ImageInfo> imageInfos = new ArrayList<>();
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
            LinksInfo linksInfo = ReadLinks.prasingLink(links);
            ImageInfo imageInfo = new ImageInfo(key, name, originName, pathname, size, width, height, md5, sha1, humanDate, date, linksInfo);
            imageInfos.add(imageInfo);
        }
        ImageSearchInfo imageSearchInfo = new ImageSearchInfo(message,current_page, last_page, per_page, total, imageInfos);
        return imageSearchInfo;
    }
}
