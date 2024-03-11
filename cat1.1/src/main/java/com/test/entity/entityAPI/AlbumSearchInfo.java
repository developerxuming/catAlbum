package com.test.entity.entityAPI;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class AlbumSearchInfo {
    private String message;
    private Integer current_page;  //当前所在页页码
    private Integer last_page;  // 最后一页页码
    private Integer per_page;  //每页展示数据数量
    private Integer total;  //相册总数量
    private ArrayList<AlbumInfo> albumInfos;

    public AlbumSearchInfo(String message, Integer current_page, Integer last_page, Integer per_page, Integer total, ArrayList<AlbumInfo> albumInfos) {
        this.message = message;
        this.current_page = current_page;
        this.last_page = last_page;
        this.per_page = per_page;
        this.total = total;
        this.albumInfos = albumInfos;
    }
}
