package com.test.mapper;

import com.test.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    List<Category> queryAllCats();
    List<Category> queryRegionCats(String region);
    Category queryNameCats(String name);
    int insertCategory(Category category);
    int updateCategory(Map<String, Object> map);
}