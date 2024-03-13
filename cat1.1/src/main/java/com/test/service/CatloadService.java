package com.test.service;

import com.test.entity.vo.CatModel;
import com.test.entity.Cat;
import com.test.mapper.CategoryMapper;
import org.apache.ibatis.session.SqlSession;
import com.test.util.GetSqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatloadService {
    public static CatModel catread() {
        CatModel catModel = new CatModel();

        SqlSession session = GetSqlSession.createSqlSession();
        CategoryMapper categoryMapper = session.getMapper(CategoryMapper.class);
        List<Cat> allCats = categoryMapper.queryAllCats();
        int len = allCats.size();
        List<String> names = new ArrayList<>();
        List<byte[]> images = new ArrayList<>();
        List<Integer> albumIds = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        List<String> varieties = new ArrayList<>();
        List<String> genders = new ArrayList<>();
        List<String> colors = new ArrayList<>();
        List<String> neutered = new ArrayList<>();
        List<String> healthy = new ArrayList<>();
        List<String> addresses = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Cat cat = allCats.get(i);
            names.add(cat.getName());
            images.add(cat.getImage());
            albumIds.add(cat.getAlbumId());
            ages.add(cat.getAge());
            varieties.add(cat.getVariety());
            int tempGender = cat.getGender();
            if (tempGender == 1) {
                genders.add("母");
            } else if (tempGender == 2) {
                genders.add("公");
            } else {
                genders.add("未知");
            }
            colors.add(cat.getColor());
            int tempNeutered = cat.getNeutered();
            if (tempNeutered == 2) {
                neutered.add("已绝育");
            } else if (tempNeutered == 1) {
                neutered.add("未绝育");
            } else {
                neutered.add("未知");
            }
            healthy.add(cat.getHealthy());
            addresses.add(cat.getAddress());
        }
        catModel.setNames(names);
        catModel.setImages(images);
        catModel.setAlbumIds(albumIds);
        catModel.setAges(ages);
        catModel.setVarieties(varieties);
        catModel.setGenders(genders);
        catModel.setColors(colors);
        catModel.setNeutereds(neutered);
        catModel.setHealthies(healthy);
        catModel.setAddresses(addresses);
        return catModel;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("java.class.path"));
        CatModel catModel = catread();
        System.out.println(catModel.getAges());
    }
}
