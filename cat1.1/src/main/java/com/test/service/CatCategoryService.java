package com.test.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.entity.Category;
import com.test.entity.ChangeHistory;
import com.test.entity.ChangeHistoryImage;
import com.test.entity.Feedback;
import com.test.mapper.CategoryMapper;
import com.test.util.DataUtils;
import com.test.util.FileUtils;
import com.test.util.GetSqlSession;
import com.test.util.DateUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CatCategoryService {
    // 保存猫咪信息
    public static String saveCatCategory(Category category, boolean isAdmin) throws IllegalAccessException {
        // 检查是否有重复名字
        List<Category> searchCategory = readCatCategory("");
        if (searchCategory != null) {
            for (Category value : searchCategory) {
                if (value.getName().equals(category.getName())) {
                    System.out.println("猫咪名字重复！");
                    return "失败：猫咪名字重复";
                }
            }
        }
        // 无重复名字正常保存
        String stream = "";
        try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            int result = categoryMapper.insertCategory(category);
            sqlSession.commit();
            System.out.println("Result of insertCat: " + result);
            stream = "成功";
            System.out.println("数据库信息添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败！");
            stream = "失败";
        }
        // 在保存成功的条件下生成日志文件
        if (stream.equals("成功")) {
            String currentTime = DateUtils.formatDateTimeForSQL();
//            List<String> location = DataUtils.getAssignedFields(category); // 获取修改位置
            Map<String, Object> newContent = DataUtils.getNewMap(category);  // 获取新内容
            Map<String, Object> oldContent = DataUtils.getEmptyMap(newContent);  // 获取对比内容
            List<String> location = new ArrayList<>(newContent.keySet()); // 获取修改位置
            ChangeHistory changeHistory = new ChangeHistory(currentTime, location, oldContent, newContent, isAdmin, "首次新增");

            Feedback feedback = createNewChangeHistoryFile(category.getName(), changeHistory);
            if (!feedback.isState()) {
                stream = "成功（警告：无法写入日志文件）";
            } else {
                stream = feedback.getMessage();
            }
        }
        return stream;
    }

    // 根据地区从数据库读取词条
    public static List<Category> readCatCategory(String region) {
        List<Category> result;
        // 如果没有传入区域，则查询所有目录
        if (region.isEmpty()) {
            try (SqlSession sqlSession = GetSqlSession.createSqlSession()){
                CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
                result = categoryMapper.queryAllCats();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("所有目录查询失败！");
            }
        // 如果传入区域，则查询该区域目录
        } else {
            try (SqlSession sqlSession = GetSqlSession.createSqlSession()){
                CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
                result = categoryMapper.queryRegionCats(region);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("区域目录查询失败！");
            }
        }
        return null;
    }

    // 由数据库读取特定名字的词条
    public static Category readCatCategoryByName(String name) {
        Category result;
        try (SqlSession sqlSession = GetSqlSession.createSqlSession()){
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            result = categoryMapper.queryNameCats(name);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("目录查询失败！");
        }
        return null;
    }
    // 服务器路径
//    public static String property = System.getProperty("user.dir");
    // 本地路径（调试用）
    public static String property = "B:/java/catAlbum/cat1.1";

    // 读取猫咪信息日志文件
    private static Feedback createNewChangeHistoryFile(String catName, ChangeHistory changeHistory) {
        Feedback feedback = new Feedback(false, "失败，无任何操作");
        // 构建文件路径
        String fileName = catName + "ChangeHistory.txt";
        String filePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "MessageRenew" + File.separator + fileName;
        // 创建文件对象
        boolean createFile = FileUtils.createFile(filePath);
        if (createFile) {
            try { // 写入目录变化信息
                // 创建文件对象
                File file = new File(filePath);
                // 使用 Gson 序列化对象为 JSON 字符串
                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                String jsonString = gson.toJson(changeHistory);
                // 写入 JSON 字符串到文件中
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(jsonString);
                // 关闭写入器
                writer.close();
                System.out.println("新增信息写入成功！");
                feedback = new Feedback(true, "成功，新增信息写入成功");
            } catch (IOException e) {
                System.out.println("新增信息写入失败：" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            return new Feedback(false, "失败，文件创建失败");
        }
        return feedback;
    }

    // 更新猫咪信息文件
    private static Feedback renewChangeHistoryFile(String catName, List<String> location, Map<String, Object> oldContentMap, Map<String, Object> newContentMap, String dateTime, boolean isAdmin) {
        // 构建文件路径
        Feedback feedback = new Feedback(false, "失败，无任何操作");
        String fileName ="";
        boolean stream = false;  // 返回state，检查主流程是否成功
        boolean needChangeName = false;  // 返回state，是否需要修改文件名
        boolean changeName = false;  // 返回state，是否成功修改了文件名
        boolean addJson = false;  // 返回state，是否成功写入了 JSON
        boolean log = readChangeHistoryFile(catName, dateTime);
        if (log || isAdmin) {
            for (String locate: location) {
                if (locate.equals("name")) {
                    needChangeName = true;
                    break;
                }
            }
            // 如果修改了名字，则需要修改文档名字
            if (needChangeName) {
                String oldName = (String) oldContentMap.get("name");
                String newName = (String) newContentMap.get("name");
                // 修改文件名
                Feedback changeNameFeedback = changeFileName(oldName, newName);
                if (changeNameFeedback.isState())  { // 如果修改成功
                    changeName = true;
                    fileName = newName + "ChangeHistory.txt"; // 更新文件名
                } else {// 修改失败
                    feedback.setMessage("失败，文件名修改失败");
                }
            } else {
                fileName = catName + "ChangeHistory.txt"; // 未修改文件名用老路径
                changeName = true;
            }
            String filePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "MessageRenew" + File.separator + fileName;
            // 开始写入文件
            if (changeName) {
                try {
                    File file = new File(filePath);
                    if (!file.exists()) {
                        System.out.println("文件不存在：" + filePath);
                        feedback.setState(stream);
                        feedback.setMessage("失败：文件不存在");
                        return feedback;
                    }
                    // 获取当前时间
                    String currentTime = DateUtils.formatDateTimeForSQL();
                    // 构建 JSON 字符串
                    ChangeHistory changeHistory = new ChangeHistory(currentTime, location, oldContentMap, newContentMap, isAdmin, "");
                    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
                    String jsonString = gson.toJson(changeHistory);
                    // 追加模式写入文件
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                    writer.newLine(); // 换行
                    writer.write(jsonString);
                    // 关闭写入器
                    writer.close();
                    System.out.println("信息写入成功！");
                    addJson = true;
                    feedback.setMessage("成功，写入信息成功");
                } catch (IOException e) {
                    System.out.println("写入信息失败：" + e.getMessage());
                    feedback.setMessage("失败，写入信息失败");
                    e.printStackTrace();
                }
            } else {
                feedback.setState(changeName);
                feedback.setMessage("失败：文件名修改失败，无法更新信息");
                return feedback;
            }
            if (changeName && addJson) {
                stream =true;
                feedback.setMessage("成功，文件名修改成功，数据库更新成功");
                feedback.setState(stream);
            }
        } else if (!log && !isAdmin) {
            return new Feedback(false, "失败：时间间隔不足，请联系管理员");
        }
        return feedback;
    }

    private static Feedback changeFileName(String oldName, String NewName) {
        // 建立新旧两套文件的路径
        Feedback feedback = new Feedback(false, "失败，无任何操作");
        String oldFileName = oldName + "ChangeHistory.txt";
        String oldFilePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "MessageRenew" + File.separator + oldFileName;
        String newFileName = NewName + "ChangeHistory.txt";
        String newFilePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "MessageRenew" + File.separator + newFileName;
        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);
        // 文件变更
        if (oldFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                System.out.println("文件名修改成功！");
                feedback = new Feedback(true, "成功，文件名修改成功");
            }
        } else {
            System.out.println("文件不存在！");
            feedback = new Feedback(false, "失败，文件不存在");
        }
        return feedback;
    }
    // 读取修改历史文件，计算时间差是否允许
    private static boolean readChangeHistoryFile(String catName, String dateTime) {
        // 构建文件路径
        String fileName = catName + "ChangeHistory.txt";
        String filePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "MessageRenew" + File.separator + fileName;
        boolean stream = false;
        // 创建文件对象
        try {
            // 创建文件对象
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在：" + filePath);
                return stream;
            }
            // 读取文件内容
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String lastLine = null;
            // 逐行读取文件内容并将每行内容赋值给 line 变量
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
            reader.close();

            // 解析最后一行为 JSON 对象
            if (lastLine != null) {
                JSONObject lastJsonObject = new JSONObject(lastLine);
                String lastModificationTime = lastJsonObject.getString("modifyTime");
                double hoursDifference = calculateHoursDifference(lastModificationTime, dateTime);
                if (hoursDifference > 0.5) {
                    return true;
                }
            }
        } catch (IOException | JSONException e) {
            System.out.println("读取信息失败：" + e.getMessage());
            e.printStackTrace();
        }
        return stream;
    }

    private static double calculateHoursDifference(String lastModificationTime, String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 将读取的时间字符串转换为日期时间对象
            Date lastModificationDate = sdf.parse(lastModificationTime);
            // 获取当前时间
            Date currentDate = sdf.parse(dateTime);
            // 计算时间差（毫秒）
            long timeDifferenceMillis = currentDate.getTime() - lastModificationDate.getTime();
            // 将毫秒转换为小时
            return timeDifferenceMillis / (1000.0 * 60 * 60);
        } catch (ParseException e) {
            System.out.println("时间格式解析失败：" + e.getMessage());
            e.printStackTrace();
            return -1; // 返回负值表示出错
        }
    }

    public static Feedback renewCategory(String oldName, Category newcategory, boolean isAdmin) {
        String dateTime = DateUtils.formatDateTimeForSQL();
        // 获取源数据
        Map<String, Object> newContentMap = new HashMap<>();
        Map<String, Map<String, Object>> CategoryMap = new HashMap<>();
        Category oldCategory = CatCategoryService.readCatCategoryByName(oldName);  // 获取原数据
        if (oldCategory != null) {
            CategoryMap = DataUtils.contrastCategory(oldCategory, newcategory);
            newContentMap = CategoryMap.get("new");
        }
        if (newContentMap.isEmpty()) {
            return new Feedback(false, "失败：没有更新任何数据");
        } else {
            Map<String, Object> oldContentMap = CategoryMap.get("old");
            List<String> location = new ArrayList<>(oldContentMap.keySet());
            Feedback feedback = renewChangeHistoryFile(oldName, location, oldContentMap, newContentMap, dateTime, isAdmin);
            if (feedback.isState()) {
                try (SqlSession sqlSession = GetSqlSession.createSqlSession()) {
                    CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
                    newContentMap.put("oldName", oldName);
                    newContentMap.put("lastRenewTime", dateTime);
                    int result = categoryMapper.updateCategory(newContentMap);
                    sqlSession.commit();
                    System.out.println("Result of insertCat: " + result);
                    System.out.println("数据库信息更新成功！");
                    return new Feedback(true, "成功，数据库信息更新成功,日志更新成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("添加失败！");
                    return new Feedback(false, "失败：未知的数据库更新错误，请联系管理员");
                }
            } else {
                return feedback;
            }
        }
    }

//    public static void main(String[] args) {
//        String name = "11";
//        byte[] image = new byte[0];
//        Integer albumId = 11;
//        String age = "0.2";
//        String variety = "11";
//        String gender = "111";
//        String appearance = "1111";
//        String neutered= "1111";
//        String healthy= "1111";
//        String address= "1111";
//        String region = "";
//        String other = "11";
//        CatCategoryService catCategoryService = new CatCategoryService();
////        catCategoryService.saveCatCategory(name, image, albumId, age, variety, gender, appearance, neutered, healthy, address, region, other);
////        List<Category> categories = CatCategoryService.readCatCategory(region);
//        boolean log= createNewChangeHistoryFile("yiyi", "age", "1","2", false);
//        System.out.println(log);
//    }
}
