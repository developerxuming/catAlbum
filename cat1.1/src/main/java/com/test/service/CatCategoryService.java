package com.test.service;

import com.test.entity.Category;
import com.test.mapper.CategoryMapper;
import com.test.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatCategoryService {
    public static String saveCatCategory(String name, byte[] image, Integer albumId, String age, String variety, String gender,
                                   String appearance, String neutered, String healthy, String address, String region, String other) {
        String feedback = "";
        Category category = new Category();
        category.setName(name);
        category.setImage(image);
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
            feedback = "成功";
            System.out.println("添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加失败！");
            feedback = "失败";
        }
        return feedback;
    }

    public static List<Category> readCatCategory(String region) {
        List<Category> result = new ArrayList<Category>();
        if (region.isEmpty()) {
            try (SqlSession sqlSession = GetSqlSession.createSqlSession()){
                CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
                result = categoryMapper.queryAllCats();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("所有目录查询失败！");
            }
        } else {
            try (SqlSession sqlSession = GetSqlSession.createSqlSession()){
                CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
                result = categoryMapper.queryRegionCats(region);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("区域目录查询失败！");
            }
        }
        return result;
    }
    public static String property = System.getProperty("user.dir");

    private static boolean createNewChangeHistoryFile(String catName, String location, String oldContent, String newContent, boolean isAdmin) {
        // 构建文件路径
        String fileName = catName + "ChangeHistory.txt";
        String filePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "catMessageRenew" + File.separator + fileName;
        // 创建文件对象
        File newFile = new File(filePath);
        boolean stream = false;
        boolean createFile = false;
        boolean writeJson = false;
        try {
            // 创建新文件
            if (newFile.createNewFile()) {
                System.out.println("新文件已创建：" + newFile.getAbsolutePath());
            } else {
                System.out.println("文件已存在：" + newFile.getAbsolutePath());
            }
            createFile = true;
        } catch (IOException e) {
            System.out.println("创建文件失败：" + e.getMessage());
            e.printStackTrace();
        }
        // 写入文件
        try {
            // 创建文件对象
            File file = new File(filePath);
            // 获取当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(new Date());
            // 构建 JSON 字符串
            String jsonString = "{" +
                    "  \"修改时间\": \"" + currentTime + "\"," +
                    "  \"修改位置\": \"" + location + "\"," +
                    "  \"历史内容\": \"" + oldContent + "\"," +
                    "  \"新的内容\": \"" + newContent + "\"," +
                    "  \"是否管理员\": " + isAdmin  +
                    "}";
            // 写入 JSON 字符串到文件中
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(jsonString);
            // 关闭写入器
            writer.close();
            System.out.println("信息写入成功！");
            writeJson = true;
        } catch (IOException e) {
            System.out.println("写入信息失败：" + e.getMessage());
            e.printStackTrace();
        }
        if (createFile && writeJson) {
            stream = true;
        }
        return stream;
    }

    private static boolean renewChangeHistoryFile(String catName, String location, String oldContent, String newContent, boolean isAdmin) {
        // 构建文件路径
        String fileName ="";
        boolean stream = false;
        boolean changeName = false;
        boolean addJson = false;
        boolean log = readChangeHistoryFile(catName);
        if (log || isAdmin) {
            // 如果修改了名字，则需要修改文档名字
            if (location.equals("name")) {
                changeName = changeFileName(oldContent, newContent);
                if (changeName) {
                    fileName = newContent + "ChangeHistory.txt";
                }
            } else {
                fileName = catName + "ChangeHistory.txt";
                changeName = true;
            }
            String filePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "catMessageRenew" + File.separator + fileName;
            // 开始写入文件
            try {
                // 创建文件对象
                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("文件不存在：" + filePath);
                    return stream;
                }
                // 获取当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(new Date());
                // 构建 JSON 字符串
                String jsonString = "{" +
                        "  \"修改时间\": \"" + currentTime + "\"," +
                        "  \"修改位置\": \"" + location + "\"," +
                        "  \"历史内容\": \"" + oldContent + "\"," +
                        "  \"新的内容\": \"" + newContent + "\"," +
                        "  \"是否管理员\": " + isAdmin  +
                        "}";
                // 追加模式写入文件
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.newLine(); // 换行
                writer.write(jsonString);
                // 关闭写入器
                writer.close();
                System.out.println("信息写入成功！");
                addJson = true;
            } catch (IOException e) {
                System.out.println("写入信息失败：" + e.getMessage());
                e.printStackTrace();
            }
            if (changeName && addJson) {
                stream =true;
            }
        }
        return stream;
    }

    private static boolean changeFileName(String oldName, String NewName) {
        // 建立新旧两套文件的路径
        String oldFileName = oldName + "ChangeHistory.txt";
        String oldFilePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "catMessageRenew" + File.separator + oldFileName;
        String newFileName = NewName + "ChangeHistory.txt";
        String newFilePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "catMessageRenew" + File.separator + newFileName;
        boolean stream = false;
        File oldFile = new File(oldFilePath);
        File newFile = new File(newFilePath);
        // 文件变更
        if (oldFile.exists()) {
            if (oldFile.renameTo(newFile)) {
                System.out.println("文件名修改成功！");
                stream = true;
            }
        } else {
            System.out.println("文件不存在！");
        }
        return stream;
    }

    private static boolean readChangeHistoryFile(String catName) {
        // 构建文件路径
        String fileName = catName + "ChangeHistory.txt";
        String filePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "catMessageRenew" + File.separator + fileName;
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
                String lastModificationTime = lastJsonObject.getString("修改时间");
                double hoursDifference = calculateHoursDifference(lastModificationTime);
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

    private static double calculateHoursDifference(String lastModificationTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 将读取的时间字符串转换为日期时间对象
            Date lastModificationDate = sdf.parse(lastModificationTime);
            // 获取当前时间
            Date currentDate = new Date();
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
//        List<Category> categories = CatCategoryService.readCatCategory(region);
        boolean log= renewChangeHistoryFile("馋馋", "age", "1","2", false);
        System.out.println(log);
    }
}
