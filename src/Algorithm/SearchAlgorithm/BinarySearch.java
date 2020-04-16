package Algorithm.SearchAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分法查找
 *
 * @auther Jia RenHao
 * @create 2020-04-13
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 6, 6, 6, 0};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(arr, 3, 0, arr.length - 1));
        List<Integer> list = binarySearchToList(arr, 6, 0, arr.length - 1);
        System.out.println(list);
    }

    /**
     * 二分查找一个数据
     *
     * @param arr   查找的数组
     * @param value 要查找的值
     * @param start 开始下标
     * @param end   结束下标
     * @return 返回查找值的索引
     */
    public static int binarySearch(int[] arr, int value, int start, int end) {
        if (start > end) {
            return -1;
        } else {
            int mid = (start + end) / 2;
            if (value < arr[mid]) {
                return binarySearch(arr, value, start, mid - 1);
            } else if (value > arr[mid]) {
                return binarySearch(arr, value, mid + 1, end);
            } else {
                return arr[mid];
            }
        }
    }

    public static List<Integer> binarySearchToList(int[] arr, int value, int start, int end) {
        List<Integer> list = new ArrayList<>();
        if (start > end) {
            return new ArrayList<Integer>();
        } else {
            int mid = (start + end) / 2;
            if (value < arr[mid]) {
                return binarySearchToList(arr, value, start, mid - 1);
            } else if (value > arr[mid]) {
                return binarySearchToList(arr, value, mid + 1, end);
            } else {
                int temp = mid - 1;
                //向左扫描
                while (temp >= 0 && arr[temp] >= arr[mid]) {
                    list.add(temp);
                    temp--;
                }
                //添加找到的这个中间元素
                list.add(arr[mid]);
                //向右扫描
                temp = mid + 1;
                while (temp <= arr.length - 1 && arr[temp] <= arr[mid]) {
                    list.add(temp);
                    temp++;
                }
                return list;
            }
        }
    }
}
