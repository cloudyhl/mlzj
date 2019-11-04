package com.mlzj.commontest.demo.method;

/**
 * kmp匹配
 *
 * @author yhl
 * @date 2019/10/22
 */
public class KmpMatching {

    private static boolean hasOther(String master, String other) {
        char[] masterArray = master.toCharArray();
        char[] otherArray = other.toCharArray();
        for (int i = 0; i < masterArray.length; i++) {
            int temp = i;
            for (int j = 0; j < otherArray.length; j++) {

                if (masterArray[temp] != otherArray[j]) {
                    if (temp + otherArray.length - j >= masterArray.length){
                        return false;
                    }
                    break;
                }
                temp++;
                if (j == otherArray.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * kmp比较若两个字符串头不相等,模式串j==0时 则i需要后移 若主串i与模式串j相等 则 i,j都需要后移
     * 若j>0时模式串与主串失配 则i不动j需要与后next数组位置的j相比
     *
     * @param master 主串
     * @param other  模式串
     * @return 是否包含模式串
     */
    private static boolean hasOtherKmp(String master, String other) {
        char[] masterArray = master.toCharArray();
        char[] otherArray = other.toCharArray();
        int j = 0;
        int i = 0;
        int[] next = getNext(other);
        while (i < masterArray.length) {
            if (masterArray[i] == otherArray[j] || j == 0) {
                if (masterArray[i] == otherArray[j]) {
                    i++;
                    j++;
                } else {
                    i++;
                    j = 0;
                }

            } else {
                j = next[j];
            }
            if (j == otherArray.length) {
                return true;
            }
        }

        return false;
    }

    /**
     * 获取next数组 自身与自身比较 例如 abcde abcde 即是开始a与b比较 错开一位开始比较
     *
     * @param other 模式串
     * @return 模式串next数组
     */
    private static int[] getNext(String other) {
        char[] otherArray = other.toCharArray();
        int[] resultArray = new int[otherArray.length];
        resultArray[0] = 0;
        int j = 0;
        int i = 1;
        while (i < otherArray.length) {
            if (otherArray[i] == otherArray[j] || j == 0) {
                if (otherArray[i] == otherArray[j]) {
                    j++;
                    resultArray[i++] = j;
                } else {
                    i++;
                }
            } else {
                j = 0;
            }
        }
        System.arraycopy(resultArray, 0, resultArray, 1, resultArray.length - 1);
        for (int index = 0; index < resultArray.length; index++){
            if (otherArray[resultArray[index]] == otherArray[index]){
                resultArray[index] =  resultArray[resultArray[index]];
            }
        }
        return resultArray;
    }

    public static void main(String[] args) {
        String master = "abcaaaasdsadasdas";
        String other = "ababaaabc";
        String others = "aaabssscdsa";
        int[] next = getNext(other);
        System.out.println(hasOtherKmp(master, other));
        boolean b = hasOther(master, other);
        System.out.println(b);
    }
}
