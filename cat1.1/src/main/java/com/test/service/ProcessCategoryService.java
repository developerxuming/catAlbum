package com.test.service;

import com.test.entity.Category;
import com.test.entity.Feedback;
import com.test.entity.vo.CategoryFeedbackModel;
import com.test.util.DataUtils;
import com.test.util.DateUtils;

import java.util.Arrays;

public class ProcessCategoryService {
    public static CategoryFeedbackModel processUpload(String name, byte[] image, Integer albumId, String age, String variety, String gender,
                              String appearance, String neutered, String healthy, String address, String region, String other, boolean isAdmin) {

        Category category = new Category();
        CategoryFeedbackModel categoryFeedbackModel = new CategoryFeedbackModel();
        category.setName(name);
        category.setImage(image);
        category.setNeutered(neutered);
        category.setHealthy(healthy);
        category.setRegion(region);
        category.setAge(age);
        category.setGender(gender);
        category.setVariety(variety);
        category.setAppearance(appearance);
        category.setAddress(address);
        category.setOther(other);
        category.setLastRenewTime(DateUtils.formatDateTimeForSQL());
        if (albumId != null) {
            category.setAlbumId(albumId);
        }
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
            String Msg = null;
            try {
                Msg = CatCategoryService.saveCatCategory(category, isAdmin);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            categoryFeedbackModel.setMsg(Msg);
            if (Msg.contains("失败")) {
                categoryFeedbackModel.setCode(0);
                categoryFeedbackModel.setMsg(Msg);
            }
        }
        return categoryFeedbackModel;
    }

    public static CategoryFeedbackModel processRenew(String oldName, Category category, boolean isAdmin) {
        CategoryFeedbackModel categoryFeedbackModel = new CategoryFeedbackModel();
        String dateTime = DateUtils.formatDateTimeForSQL();
        category.setLastRenewTime(dateTime);
        categoryFeedbackModel.setObject(category);
        // 处理特殊情况
        DataUtils.isUnhealthy(category.getHealthy(), categoryFeedbackModel);
        // 检查参数是否合法, 不合法立即返回
        DataUtils.isMaleAndPregnant(category.getGender(), category.getHealthy(), categoryFeedbackModel);
        if (categoryFeedbackModel.getCode() == 0 ) {
            return categoryFeedbackModel;
        }
        DataUtils.isEmpty(category, categoryFeedbackModel);
        if (categoryFeedbackModel.getCode() == 0 ) {
            return categoryFeedbackModel;
        }
        // 参数合法，更新词条
        Feedback feedback = CatCategoryService.renewCategory(oldName, category, isAdmin);
        if (feedback.isState()) {
            categoryFeedbackModel.setMsg(feedback.getMessage());
        } else {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg(feedback.getMessage());
        }
        return categoryFeedbackModel;
    }
}
