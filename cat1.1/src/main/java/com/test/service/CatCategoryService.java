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
    /**
    * 向数据库保存猫咪分类信息
    * @param name 分类名称
    * @param image 分类图片
    * @param age 猫咪年龄
    * @param variety 猫咪品种
    * @param gender 猫咪性别
    * @param appearance 猫咪外貌特征
    * @param neutered 猫咪是否绝育
    * @param healthy 猫咪健康状况
    * @param address 猫咪地址
    * @param region 猫咪所在地区
    * @param other 其他信息
    * @return 反馈信息
     */
    public static String saveCatCategory(String name, byte[] image, String age, String variety, String gender,
                                         String appearance, String neutered, String healthy, String address, String region, String other) {
        String feedback;
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
            CategoryMapper categoryMapper = null;
            if (sqlSession != null) {
                categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            }
            int result = 0;
            if (categoryMapper != null) {
                result = categoryMapper.insertCategory(category);
            }
            if (sqlSession != null) {
                sqlSession.commit();
            }
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

    /**
     * 根据地区读取流浪猫目录
     * @param region 地区（南区、北区、领养）
     * @return List<Category> 分类信息列表
     */
    public static List<Category> readCatCategory(String region) {
        List<Category> result = new ArrayList<>();
        if (region.isEmpty()) {
            try (SqlSession sqlSession = GetSqlSession.createSqlSession()){
                CategoryMapper categoryMapper = null;
                if (sqlSession != null) {
                    categoryMapper = sqlSession.getMapper(CategoryMapper.class);
                }
                if (categoryMapper != null) {
                    result = categoryMapper.queryAllCats();
                }
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

    /**
     * 根据目录名称读取目录信息
     */
    public static String property = System.getProperty("user.dir");

    /**
     * 创建新的ChangeHistory.txt文件
     * @param catName 猫咪名字
     * @param location 修改位置
     * @param oldContent 旧内容
     * @param newContent 新内容
     * @param isAdmin 是否管理员
     * @return 是否成功创建文件
     */
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

    /**
     * 更新ChangeHistory.txt文件
     * @param catName 猫咪名字
     * @param location 位置
     * @param oldContent 旧内容
     * @param newContent 新内容
     * @param isAdmin 是否管理员
     * @return 是否成功更新文件
     */
    private static boolean renewChangeHistoryFile(String catName, String location, String oldContent, String newContent, boolean isAdmin) {
        // 构建文件路径
        String fileName ="";
        boolean stream = false;
        boolean changeName;
        boolean addJson = false;
        boolean log = readChangeHistoryFile(catName, isAdmin).equals("满足时间限制");
        if (log || isAdmin) {
            // 如果修改了名字，则需要修改文档名字
            if (location.equals("name")) {
                changeName = changeFileName(oldContent, newContent);
                if (changeName) {
                    // 新文档名字
                    fileName = newContent + "ChangeHistory.txt";
                }
            } else {
                // 保持旧文档名
                fileName = catName + "ChangeHistory.txt";
                changeName = true;
            }
            // 建立文档路径
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

    /**
     * 修改文件名
     * @param oldName 旧名字
     * @param NewName 新名字
     * @return 是否修改成功
     */
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

    /**
     * 读取变更历史文件,检查是否允许修改
     * @param catName 猫咪名字
     * @param Adim 是否管理员
     * @return 变更历史文件内容
     */
    private static String readChangeHistoryFile(String catName, boolean Adim) {
        // 构建文件路径
        String fileName = catName + "ChangeHistory.txt";
        String filePath = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "changeHistory" + File.separator + "catMessageRenew" + File.separator + fileName;
        String log = "不满足时间限制";
        // 创建文件对象
        try {
            // 创建文件对象
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在：" + filePath);
                return "找不到文件";
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
                // 计算时间差
                String msg = calculateHoursDifference(lastModificationTime);
                if (msg.equals("满足时间限制")) {
                    log = "满足时间限制";
                } else if (msg.equals("不满足时间限制") && Adim) {
                    log = "管理员权限";
                } else if (msg.equals("不满足时间限制") && !Adim) {
                    log = "不满足时间限制，请联系管理员";
                } else {
                    log = "出错";
                }
            }
        } catch (IOException | JSONException e) {
            System.out.println("读取信息失败：" + e.getMessage());
            e.printStackTrace();
        }
        return log;
    }

    /**
     * 计算时间差
     * @param lastModificationTime 最后修改时间
     * @return 满足时间限制或不满足时间限制
     */
    private static String calculateHoursDifference(String lastModificationTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 将读取的时间字符串转换为日期时间对象
            Date lastModificationDate = sdf.parse(lastModificationTime);
            // 获取当前时间
            Date currentDate = new Date();
            // 计算时间差（毫秒）
            long timeDifferenceMillis = currentDate.getTime() - lastModificationDate.getTime();
            // 将毫秒转换为小时
            if (timeDifferenceMillis / (1000.0 * 60 * 60) >=0.5) {
                return "满足时间限制";
            } else {
                return "不满足时间限制";
            }
        } catch (ParseException e) {
            System.out.println("时间格式解析失败：" + e.getMessage());
            e.printStackTrace();
            return "出错"; // 返回负值表示出错
        }
    }
    // 测试方法
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
//        CatCategoryService catCategoryService = new CatCategoryService();
//        catCategoryService.saveCatCategory(name, image, albumId, age, variety, gender, appearance, neutered, healthy, address, region, other);
//        List<Category> categories = CatCategoryService.readCatCategory(region);
//        boolean log= renewChangeHistoryFile("馋馋", "age", "1","2", false);
//        System.out.println(log);
    }
}
