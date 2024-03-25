package com.test.service;

import com.test.entity.Category;
import com.test.entity.vo.CategoryFeedbackModel;

public class ProcessCategoryService {
    public static CategoryFeedbackModel processUpload(String name, byte[] image, Integer albumId, String age, String variety, String gender,
                              String appearance, String neutered, String healthy, String address, String region, String other) {
        Category category = new Category();
        CategoryFeedbackModel categoryFeedbackModel = new CategoryFeedbackModel();
        category.setName(name);
        category.setAge(age);
        category.setVariety(variety);
        category.setAppearance(appearance);
        category.setAddress(address);
        category.setOther(other);
        categoryFeedbackModel.setObject(category);
        if (healthy.equals("很差")) {
            categoryFeedbackModel.setMsg("我们会持续关注它的健康状况");
        }
        if (gender.equals("公") && healthy.equals("怀孕")) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("公猫好像不能怀孕");
        }
        if (name == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("名字好像不能为空");
        }
        if (variety == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("品种好像不能为空");
        }
        if (address == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("地址好像不能为空");
        }
        if (categoryFeedbackModel.getCode() == 200) {
            String Msg = CatCategoryService.saveCatCategory(name, image, albumId, age, variety, gender, appearance, neutered, healthy, address, region, other);
            categoryFeedbackModel.setMsg(Msg);
        }
        return categoryFeedbackModel;
    }
}
