package Algorithms.SortAlgorithm;

import java.util.Arrays;

/**
 * 快速排序(韩顺平版)
 * 效率比算法导论版高，8000w数据8s
 *
 * @author Jia RenHao
 * @create 2020-04-09
 */
public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
    }

    /**
     * 快速排序
     *
     * @param arr  要排序的数组
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//记录左下标，l会改变，low并不随着递归而改变
        int h = right;//记录右下标，h会改变，high并不随着递归而改变
        int pivot = arr[(left + right) / 2];//基准值
        int temp;
        while (l < h) {
            while (arr[l] < pivot) {
                l += 1;
            }
            while (arr[h] > pivot) {
                h -= 1;
            }
            if (l == h) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[h];
            arr[h] = temp;
            //这两个if目的：
            //如果基准值一边已经全都是比他大或者比他小的数，那么基准值就和找到的数交换
            // 所以就会arr[l] == pivot或者arr[h] == pivot
            //然后另一个指针++或--，就不用再比较一次刚换过来的数了
            if (arr[l] == pivot) {
                h--;
            }
            if (arr[h] == pivot) {
                l++;
            }
            //System.out.println(Arrays.toString(arr));
        }
        //一轮结束，进入下一轮递归
        //这两个if是作为递归头，如果没有下一次递归，则所有栈帧依次出栈
        //左分区递归
        if (left < h) {
            //System.out.println("左---");
            quickSort(arr, left, h - 1);
        }
        //右分区递归
        if (right > l) {
            //System.out.println("右---");
            quickSort(arr, l + 1, right);
        }
    }
}
