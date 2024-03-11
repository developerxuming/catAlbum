package com.test.entity.entityAPI;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ImageSearchInfo {
    private String message;
    private String albumId;
    private Integer current_page;  //当前所在页页码
    private Integer last_page;  // 最后一页页码
    private Integer per_page;  //每页展示数据数量
    private Integer total;  //图片总数量
    private ArrayList<ImageInfo> imageInfos;

    public ImageSearchInfo(String message, Integer current_page, Integer last_page, Integer per_page, Integer total, ArrayList<ImageInfo> imageInfos) {
        this.message = message;
        this.current_page = current_page;
        this.last_page = last_page;
        this.per_page = per_page;
        this.total = total;
        this.imageInfos = imageInfos;
    }
}
