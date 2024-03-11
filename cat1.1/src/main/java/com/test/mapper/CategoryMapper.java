package com.test.mapper;
import com.test.entity.Cat;
import com.test.entity.Category;

import java.util.List;
public interface CategoryMapper {
    List<Cat> queryAllCats();
    int insertCategory(Category category);
}