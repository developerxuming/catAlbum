package com.test.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
    private Integer albumId;
    private Integer page;
    private String order;
    private String q;
}
