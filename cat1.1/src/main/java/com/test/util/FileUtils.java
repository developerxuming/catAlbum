package com.test.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    // 生成更新索引更新记录文件
    public static boolean createFile(String filePath){
        File newFile = new File(filePath);
        try {
            // 创建新文件
            if (newFile.createNewFile()) {
                System.out.println("新文件已创建：" + newFile.getAbsolutePath());
            } else {
                System.out.println("文件已存在：" + newFile.getAbsolutePath());
            }
            return true;
        } catch (IOException e) {
            System.out.println("创建文件失败：" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
