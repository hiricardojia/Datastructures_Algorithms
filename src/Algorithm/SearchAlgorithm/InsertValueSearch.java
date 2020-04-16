package Algorithm.SearchAlgorithm;

import java.util.Arrays;
import java.util.List;

/**
 * 插值查找，和二分查找仅在中间值的算法不同
 *
 * @auther Jia RenHao
 * @create 2020-04-13
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 6, 6, 6, 0};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        insertValueSearch(arr, 3, 0, arr.length - 1);
        System.out.println(insertValueSearch(arr, 3, 0, arr.length - 1));
    }

    public static int insertValueSearch(int[] arr, int value, int low, int high) {
        if (low > high || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }

        //利用插值法求出中间值下标
        int flag = low + (value - arr[low]) / (arr[high] - arr[low]) * (high - low);
        if (value > arr[flag]) {
            return insertValueSearch(arr, value, flag + 1, high);
        } else if (value < arr[flag]) {
            return insertValueSearch(arr, value, low, flag - 1);
        } else {
            return flag;
        }
    }
}
