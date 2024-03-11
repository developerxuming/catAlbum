package com.test.parsingJSON;

import com.test.entity.entityAPI.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadAlbum {
    public static AlbumSearchInfo parseJson(String json) {
        // JSON 数据字符串
        // 解析JSON字符串
        JSONObject jsonObject = new JSONObject(json);
        //描述信息
        String message = jsonObject.getString("message");
        JSONObject dataObject = jsonObject.getJSONObject("data");  //读取第一部分data，该部分data包含了页码信息，和图片信息的json
        Integer current_page = dataObject.getInt("current_page");  //当前所在页页码
        Integer last_page = dataObject.getInt("last_page");  // 最后一页页码
        Integer per_page =  dataObject.getInt("per_page");  //每页展示数据数量
        Integer total = dataObject.getInt("total");  //图片总数量
        JSONArray dataArray = dataObject.getJSONArray("data");  // 从 JSON 对象中获取名为 "data" 的字段
        System.out.println(dataArray);
        ArrayList<AlbumInfo> albumInfos = new ArrayList<>();
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject imageObject = dataArray.getJSONObject(i);
            Integer id = imageObject.getInt("id");  //相册自增ID
            String name = imageObject.getString("name");  //相册名称
            String intro = imageObject.getString("intro");  //相册简介
            Integer image_num = imageObject.getInt("image_num");  //相册图片数量
            AlbumInfo albumInfo = new AlbumInfo(id, name, intro, image_num);
            albumInfos.add(albumInfo);
        }
        return new AlbumSearchInfo(message, current_page, last_page, per_page, total, albumInfos);
    }
}
