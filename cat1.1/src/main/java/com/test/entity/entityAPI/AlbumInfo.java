package com.test.entity.entityAPI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumInfo {
    private Integer id;
    private String name;
    private String intro;
    private Integer image_num;

    public AlbumInfo(Integer id, String name, String intro, Integer image_num) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.image_num = image_num;
    }
}
