package com.test.entity.vo;

import java.util.List;

/**
 * 存放信息：
 * 1，当前页码
 * 2，总页数
 * 3，每页显示的图片数量
 * 4，相册中的图片数量
 * 5，图片对应的url列表
 */
public class ImageModel {
    private Integer current_page;
    private Integer last_page;
    private Integer per_page_image;
    private Integer tolal_image;
    private List<String> imageUrls;
    private List<String> imageSize;

    public List<String> getImageSize() {
        return imageSize;
    }

    public void setImageSize(List<String> imageSize) {
        this.imageSize = imageSize;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public Integer getLast_page() {
        return last_page;
    }

    public void setLast_page(Integer last_page) {
        this.last_page = last_page;
    }

    public Integer getPer_page_image() {
        return per_page_image;
    }

    public void setPer_page_image(Integer per_page_image) {
        this.per_page_image = per_page_image;
    }

    public Integer getTolal_image() {
        return tolal_image;
    }

    public void setTolal_image(Integer tolal_image) {
        this.tolal_image = tolal_image;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
