package Algorithms.SortAlgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @Title: 快速排序&随机快速排序(算法导论)
 * @Description: 效率相较于韩顺平版比较差，8000w数据50+s
 * @Author: Jia RenHao
 * @Create: 2020-05-04
 * @Version: V1.0
 */
public class RandomQuickSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        quickSort(arr);
        randomQuickSort(arr);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[20000000];
        for (int i = 0; i < 20000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        //quickSort(arr);
        randomQuickSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
    }

    /**
     * 经典快速排序,对外API接口
     *
     * @param arr 待排序数组
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 经典快速排序内部实现
     *
     * @param arr   待排序数组
     * @param left  左边界
     * @param right 右边界
     */
    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, left);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, left);
        }
    }

    /**
     * 分割
     *
     * @param arr   待排序数组
     * @param left  左边界
     * @param right 右边界
     * @return 返回中枢值的索引
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];//枢值
        int p = left - 1;//记录要换的下标
        //i从左往右扫描
        for (int i = left; i < right; i++) {
            //如果找到一个数比pivot小，就往前放，即下标为p的地方
            if (arr[i] <= pivot) {
                p++;
                swap(arr, p, i);
            }
        }
        swap(arr, p + 1, right);
        return p + 1;
    }

    /**
     * 随机快速排序,对外API接口
     *
     * @param arr 待排序数组
     */
    public static void randomQuickSort(int[] arr) {
        randomQuickSort(arr, 0, arr.length - 1);
    }

    /**
     * 随机快速排序内部实现
     * @param arr 待排序数组
     * @param left 左指针
     * @param right 右指针
     */
    private static void randomQuickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = randomPartition(arr, left, right);
            randomQuickSort(arr, left, pivotIndex - 1);
            randomQuickSort(arr, pivotIndex + 1, right);
        }
    }

    /**
     * 随机快排的分割
     *
     * @param arr   待排序数组
     * @param left  左边界
     * @param right 右边界
     * @return 返回中枢值的索引
     */
    private static int randomPartition(int[] arr, int left, int right) {
        int r = new Random().nextInt(right - left) + left; //生成一个随机数，即是主元所在位置
        swap(arr, right, r); //将主元与序列最右边元素互换位置，这样就变成了之前快排的形式。
        return partition(arr, left, right); //直接调用之前的代码
    }

    /**
     * 辅助方法：交换
     *
     * @param arr 数组
     * @param i   索引
     * @param j   索引
     */
    private static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
