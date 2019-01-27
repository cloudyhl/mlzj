package com.mlzj.common.utils;

/**
 * @author yhl
 * @date 2019/1/27
 */
public class CharacterUtils {

    public static String toUpperCaseFist(String str){
        if(Character.isUpperCase(str.charAt(0))){
            return str;
        }
        else{
            return String.valueOf(Character.toUpperCase(str.charAt(0))) + str.substring(1);
        }

    }
    private CharacterUtils(){};
}
