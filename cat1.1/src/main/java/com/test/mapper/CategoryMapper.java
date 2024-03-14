package com.test.mapper;

import com.test.entity.Category;

import java.util.List;
public interface CategoryMapper {
    List<Category> queryAllCats();
    List<Category> queryRegionCats(String region);
    int insertCategory(Category category);
}