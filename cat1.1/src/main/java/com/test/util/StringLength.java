package com.test.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringLength {
    public static boolean chineseCheck(String string) // 检查字符串是否有中文
    {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(string);
        return m.find();
    }
    private static boolean isChineseWord(char c) // 检查字符是否为汉字
    {
        return (c >= '\u4e00' && c <= '\u9fa5');
    }
    private static boolean isChinesePunctuation(char c) // 检查字符是否为中文符号
    {
        // 中文标点符号的Unicode范围
        return (c >= '\u3000' && c <= '\u303F') || (c >= '\u2010' && c <= '\u2017') ||
                (c >= '\u2000' && c <= '\u206F') || (c >= '\uFE10' && c <= '\uFE1F');
    }
    public static void printStringWithLength(String string, int maxLength) // 接收两个输入，字符串和返回字符长度
    {
        int totalLength = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (isChineseWord(c) || isChinesePunctuation(c)) {
                totalLength += 2; // 汉字和中文标点符号算两个长度
            } else {
                totalLength += 1; // 非汉字和非中文标点符号算一个长度
            }
            if (totalLength <= maxLength) {
                System.out.print(c);
            } else {
                break;
            }
        }
        System.out.println(); // 换行
    }

    public static void main(String[] args) {
        String testString = "你好、!Hello！";
        printStringWithLength(testString, 6);
    }
}
