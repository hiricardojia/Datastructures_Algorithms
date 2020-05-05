package Algorithms.SortAlgorithm;

import java.util.Arrays;

/**
 * @Title: 快速排序(韩顺平版)
 * @Description: 效率比算法导论版高，8000w数据8s,遇到问题：韩版和个人版出现性能问题未解决！！！
 * @Author: Jia RenHao
 * @Create: 2020-04-09
 * @Version: V2.0
 */
public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        review(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        review(arr, 0, arr.length - 1);
        //quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     *
     * @param arr   要排序的数组
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//记录左下标，l会改变，low并不随着递归而改变
        int r = right;//记录右下标，h会改变，high并不随着递归而改变
        int pivot = arr[(left + right) / 2];//基准值
        int temp;
        while (l < r) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[r] > pivot) {
                r -= 1;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //这两个if目的：
            //如果基准值一边已经全都是比他大或者比他小的数，那么基准值就和找到的数交换
            // 所以就会arr[l] == pivot或者arr[r] == pivot
            //然后另一个指针++或--，就不用再比较一次刚换过来的数了
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
            //System.out.println(Arrays.toString(arr));
        }
        //一轮结束，进入下一轮递归
        //这两个if是作为递归头，如果没有下一次递归，则所有栈帧依次出栈
        //左分区递归
        if (left < r) {
            //System.out.println("左---");
            quickSort(arr, left, r - 1);
        }
        //右分区递归
        if (right > l) {
            //System.out.println("右---");
            quickSort(arr, l + 1, right);
        }
    }

    public static void review(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp;
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == pivot) {
                r--;
                continue;
            }
            if (arr[r] == pivot) {
                l++;
                continue;
            }
            l++;
            r--;
        }//while
        if (r > left) {
            review(arr, left, r - 1);
        }
        if (l < right) {
            review(arr, l + 1, right);
        }
    }
}
