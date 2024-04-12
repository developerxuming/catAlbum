package com.test.util;

import com.test.entity.Category;
import com.test.entity.vo.CategoryFeedbackModel;

import java.lang.reflect.Field;
import java.util.*;

public class DataUtils {
    public static List<String> getAssignedFields(Category category) throws IllegalAccessException {
        List<String> assignedFields = new ArrayList<>();
        Field[] fields = Category.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(category) != null) {
                assignedFields.add(field.getName());
            }
        }
        return assignedFields;
    }
    // 判断内容修改，返回<新增位置，新增值>的Map
    public static Map<String, Object> getNewMap(Category category){
        Map<String, Object> fieldValueMap = new HashMap<>();
        if (category.getName() != null) {
            fieldValueMap.put("name", category.getName());
        }
        if (category.getImage() != null) {
            fieldValueMap.put("image", category.getImage());
        }
        if (category.getAlbumId() != 0) {
            fieldValueMap.put("albumId", category.getAlbumId());
        }
        if (category.getAge() != null) {
            fieldValueMap.put("age", category.getAge());
        }
        if (category.getVariety() != null) {
            fieldValueMap.put("variety", category.getVariety());
        }
        if (category.getGender() != null) {
            fieldValueMap.put("gender", category.getGender());
        }
        if (category.getAppearance() != null) {
            fieldValueMap.put("appearance", category.getAppearance());
        }
        if (category.getNeutered() != null) {
            fieldValueMap.put("neutered", category.getNeutered());
        }
        if (category.getHealthy() != null) {
            fieldValueMap.put("healthy", category.getHealthy());
        }
        if (category.getAddress() != null) {
            fieldValueMap.put("address", category.getAddress());
        }
        if (category.getRegion() != null) {
            fieldValueMap.put("region", category.getRegion());
        }
        if (category.getOther() != null) {
            fieldValueMap.put("other", category.getOther());
        }
        return fieldValueMap;
    }
    // 返回旧内容的值Map<字段名，字段值>
    public static Map<String, Object> getEmptyMap(Map<String, Object> newContent) {
        // 创建一个空内容的Map，用于作为新内容的比较基准
        Map<String, Object> emptyContent = new HashMap<>();
        for (Map.Entry<String, Object> entry : newContent.entrySet()) {
            emptyContent.put(entry.getKey(), "");
        }
        return emptyContent;
    }
    // 处理特殊情况
    public static void isUnhealthy(String healthy, CategoryFeedbackModel categoryFeedbackModel) {
        if (healthy.equals("很差")) {
            categoryFeedbackModel.setMsg("我们会持续关注它的健康状况");
        }
    }
    // 判断不合理数据
    public static void isMaleAndPregnant(String gender, String healthy, CategoryFeedbackModel categoryFeedbackModel) {
        if (gender.equals("公") && healthy.equals("怀孕")) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("公猫好像不能怀孕");
        }
    }
    // 判断非法数据
    public static void isEmpty(Category category, CategoryFeedbackModel categoryFeedbackModel) {
        if (category.getName() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("名字好像不能为空");
        }
        if (category.getVariety() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("品种好像不能为空");
        }
        if (category.getAddress() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("地址好像不能为空");
        }
        if (category.getNeutered() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("是否绝育好像不能为空");
        }
        if (category.getAge() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("年龄好像不能为空");
        }
        if (category.getHealthy() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("健康状况好像不能为空");
        }
        if (category.getGender() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("性别好像不能为空");
        }
        if (category.getAppearance() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("外貌好像不能为空");
        }
        if (category.getOther() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("其他信息好像不能为空");
        }
        if (category.getRegion() == null) {
            categoryFeedbackModel.setCode(0);
            categoryFeedbackModel.setMsg("地区好像不能为空");
        }
    }
    // 对比两个Category对象，返回新和旧的字段Map
    public static Map<String, Map<String, Object>> contrastCategory(Category oldCategory, Category newCategory){
        // 新建一个用于覆盖原数据库词条的对象，不需要更新的内容置空
        Map<String,Map<String, Object>> CategoryMap = new HashMap<>();
        Map<String, Object> newCategoryMap = new HashMap<>();
        Map<String, Object> oldCategoryMap = new HashMap<>();
        // 检查数据差异
        if (!oldCategory.getName().equals(newCategory.getName())) {
            newCategoryMap.put("name", newCategory.getName());
            oldCategoryMap.put("name", oldCategory.getName());
        }
        if (!Arrays.equals(newCategory.getImage(), new byte[0]) && newCategory.getImage() != null && newCategory.getImage().length != 0) {
            newCategoryMap.put("image", newCategory.getImage());
            oldCategoryMap.put("image", oldCategory.getImage());
        }
        if (Arrays.equals(oldCategory.getImage(), newCategory.getImage())) {
            newCategoryMap.put("albumId", newCategory.getAlbumId());
            oldCategoryMap.put("albumId", oldCategory.getAlbumId());
        }
        if (!oldCategory.getAge().equals(newCategory.getAge())) {
            newCategoryMap.put("age", newCategory.getAge());
            oldCategoryMap.put("age", oldCategory.getAge());
        }
        if (!oldCategory.getVariety().equals(newCategory.getVariety())) {
            newCategoryMap.put("variety", newCategory.getVariety());
            oldCategoryMap.put("variety", oldCategory.getVariety());
        }
        if (!oldCategory.getGender().equals(newCategory.getGender())) {
            newCategoryMap.put("gender", newCategory.getGender());
            oldCategoryMap.put("gender", oldCategory.getGender());
        }
        if (!oldCategory.getAppearance().equals(newCategory.getAppearance())) {
            newCategoryMap.put("appearance", newCategory.getAppearance());
            oldCategoryMap.put("appearance", oldCategory.getAppearance());
        }
        if (!oldCategory.getNeutered().equals(newCategory.getNeutered())) {
            newCategoryMap.put("neutered", newCategory.getNeutered());
            oldCategoryMap.put("neutered", oldCategory.getNeutered());
        }
        if (!oldCategory.getHealthy().equals(newCategory.getHealthy())) {
            newCategoryMap.put("healthy", newCategory.getHealthy());
            oldCategoryMap.put("healthy", oldCategory.getHealthy());
        }
        if (!oldCategory.getAddress().equals(newCategory.getAddress())) {
            newCategoryMap.put("address", newCategory.getAddress());
            oldCategoryMap.put("address", oldCategory.getAddress());
        }
        if (!oldCategory.getRegion().equals(newCategory.getRegion())) {
            newCategoryMap.put("region", newCategory.getRegion());
            oldCategoryMap.put("region", oldCategory.getRegion());
        }
        if (!oldCategory.getOther().equals(newCategory.getOther())) {
            newCategoryMap.put("other", newCategory.getOther());
            oldCategoryMap.put("other", oldCategory.getOther());
        }
        CategoryMap.put("new", newCategoryMap);
        CategoryMap.put("old", oldCategoryMap);
        return CategoryMap;
    }

}
