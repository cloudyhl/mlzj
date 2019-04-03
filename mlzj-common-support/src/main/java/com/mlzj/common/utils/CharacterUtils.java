package com.mlzj.common.utils;

/**
 * @author yhl
 * @date 2019/1/27
 */
public class CharacterUtils {

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 首字母大写后的字符串
     */
    public static String toUpperCaseFist(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return String.valueOf(Character.toUpperCase(str.charAt(0))) + str.substring(1);
        }

    }

    /**
     * 首字母小写
     *
     * @param str 字符串
     * @return 首字母大写的字符串
     */
    public static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return String.valueOf(Character.toLowerCase(str.charAt(0))) + str.substring(1);
        }
    }

    private CharacterUtils() {
    }
}
