package com.test.service;

import com.test.API.ImageAPI;
import com.test.entity.entityAPI.ImageSearchInfo;
import com.test.parsingJSON.ReadImage;

public class ImageService {
    /**
     * 获取数据
     * 如果，相册id与页码的值不为空
     * 则返回对应相册id中对应页码的图片，并将其作为暂存值
     * 如果，相册id为空，页码不为空，则判断页码
     * 如果申请的页码大于当前已有页码，
     * 则返回当前相册尾页图片。
     * 如果申请的页码小于1，
     * 则返回当前相册首页图片。
     * 如果，相册id不为空，页码为空
     * 则返回对应相册的首页图片
     */
// 实例变量用于存储 Image 和 ImageModel 信息
    public static String loadImage(int albumId, int page, String order) {
        String q = "default";
        String json = ImageAPI.getJSON(albumId, page, order, q);  // 获取json
        // 解析JSON字符串
        ImageSearchInfo imageSearchInfo = ReadImage.parseJson(json);
        imageSearchInfo.setAlbumId(String.valueOf(albumId));
        System.out.println(imageSearchInfo.getMessage());
        return json;
    }
}
