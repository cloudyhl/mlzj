package com.mlzj.commontest.demo.datastruct.search;

/**
 * 这般查找
 *
 * @author yhl
 * @date 2019/11/15
 */
public class HalveSearch {


    public static void main(String[] args) {
        int[] array = {1, 23, 44, 47, 64, 67, 72, 73, 91};
        int target = 47;
        System.out.println(halveSearch(array, 91));
    }

    private static int halveSearch(int[] array, int target) {
        int high = array.length - 1;
        int low = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            //int mid = low + (high - low) / 2;
            //int mid = low + (high - low) * (target - array[low]) / (array[high] - array[low]); 插值比较 具体是用在分布较均匀的有序集合 即计算一个比例 试计算查询值的大致位置
            if (target == array[mid]) {
                return mid;
            }
            if (target > array[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

}
