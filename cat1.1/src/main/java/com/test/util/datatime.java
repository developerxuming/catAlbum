package com.test.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class datatime {
    // 返回当前时间的 LocalDateTime 对象
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    // 将 LocalDateTime 格式化为 SQL 中的 datetime 字符串
    public static String formatDateTimeForSQL(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
