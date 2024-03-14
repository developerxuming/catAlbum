package com.test.service;

import com.test.entity.Category;
import com.test.entity.vo.CatModel;
import com.test.mapper.CategoryMapper;
import org.apache.ibatis.session.SqlSession;
import com.test.util.GetSqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatloadService {
    public static String catread() {
        CatModel catModel = new CatModel();

        SqlSession session = GetSqlSession.createSqlSession();
        CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
        List<Category> allCatsCategory = categoryMapper.queryAllCats();
        int len = allCatsCategory.size();
        List<String> names = new ArrayList<>();
        List<byte[]> images = new ArrayList<>();
        List<Integer> albumIds = new ArrayList<>();
        List<String> ages = new ArrayList<>();
        List<String> varieties = new ArrayList<>();
        List<String> genders = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        List<String> neutered = new ArrayList<>();
        List<String> healthy = new ArrayList<>();
        List<String> addresses = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Category category = allCatsCategory.get(i);
            names.add(category.getName());
            images.add(category.getImage());
            albumIds.add(category.getAlbumId());
            ages.add(category.getAge());
            varieties.add(category.getVariety());
        }
        return null;
    };

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("java.class.path"));
    }
}
