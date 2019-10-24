package com.dhcc.sso.utils;

/**
 * Annotation:
 * 字符串工具类
 *
 * @Author: Adam Ming
 * @Date: Oct 24, 2019 at 8:39:21 PM
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String value) {
        return value == null || "".equals(value.trim());
    }
}
