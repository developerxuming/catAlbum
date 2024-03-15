package com.test.service;

import com.test.entity.Cat;
import com.test.entity.Category;
import com.test.mapper.Cat_feedbackMapper;
import com.test.mapper.CategoryMapper;
import com.test.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class CatCategoryService {
    protected void saveCatCategory(String name, byte[] image, Integer albumId, String age, String variety, String gender,
                                   String appearance, String neutered, String healthy, String address, String region, String other) {
        Category category = new Category();
        category.setName(name);
        category.setImage(image);
        category.setAlbumId(albumId);
        category.setAge(age);
        category.setVariety(variety);
        category.setGender(gender);
        category.setAppearance(appearance);
        category.setNeutered(neutered);
        category.setHealthy(healthy);
        category.setAddress(address);
        category.setRegion(region);
        category.setOther(other);

        try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            int result = categoryMapper.insertCategory(category);
            sqlSession.commit();
            System.out.println("Result of insertCat: " + result);
            System.out.println("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败！");
        }
    }

    protected static List<Category> readCatCategory(String region) {
        List<Category> result = new ArrayList<Category>();
        SqlSession sqlSession = GetSqlSession.createSqlSession();
        CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
        if (region.isEmpty()) {
            try {
                result = categoryMapper.queryAllCats();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("所有目录查询失败！");
            }
        } else {
            try {
                result = categoryMapper.queryRegionCats(region);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("区域目录查询失败！");
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String name = "11";
        byte[] image = new byte[0];
        Integer albumId = 11;
        String age = "0.2";
        String variety = "11";
        String gender = "111";
        String appearance = "1111";
        String neutered= "1111";
        String healthy= "1111";
        String address= "1111";
        String region = "";
        String other = "11";
        CatCategoryService catCategoryService = new CatCategoryService();
//        catCategoryService.saveCatCategory(name, image, albumId, age, variety, gender, appearance, neutered, healthy, address, region, other);
        List<Category> categories = CatCategoryService.readCatCategory(region);
        int aaa = 1;
    }
}
