package com.test.entity.entityAPI;

import lombok.Data;

@Data
public class ImageInfo {
    private String key;
    private String name;
    private String originName;
    private String pathname;
    private float size;
    private int width;
    private int height;
    private String md5;
    private String sha1;
    private String humanDate;
    private String date;
    private LinksInfo links;
    // 构造函数
    public ImageInfo(String key, String name, String originName, String pathname,
                     float size, int width, int height, String md5, String sha1,
                     String humanDate, String date, LinksInfo links) {
        this.key = key;
        this.name = name;
        this.originName = originName;
        this.pathname = pathname;
        this.size = size;
        this.width = width;
        this.height = height;
        this.md5 = md5;
        this.sha1 = sha1;
        this.humanDate = humanDate;
        this.date = date;
        this.links = links;
    }
}
