package com.test.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

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
    private String lastRenewTime;
}
