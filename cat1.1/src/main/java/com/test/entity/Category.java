package com.test.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private String name;
    private byte[] image;
    private int albumId;
    private String age;
    private String variety;
    private String gender;
    private String appearance;
    private String neutered;
    private String healthy;
    private String address;
    private String region;
    private String other;
//    public Category(String name, byte[] image, int albumId, String age, String variety, String gender, String appearance, String neutered, String healthy, String address, String region, String other) {
//        this.name = name;
//        this.image = image;
//        this.albumId = albumId;
//        this.age = age;
//        this.variety = variety;
//        this.gender = gender;
//        this.appearance = appearance;
//        this.neutered = neutered;
//        this.healthy = healthy;
//        this.address = address;
//        this.region = region;
//        this.other = other;
//    }
}
