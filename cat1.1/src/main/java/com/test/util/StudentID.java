package com.test.util;

public class StudentID {
    public static boolean grade(String ID) // 判断学号信息
    {
        int g = Integer.parseInt(ID.substring(0,4));
        return (g >= 2017 && g <= 2023);
    }
}
