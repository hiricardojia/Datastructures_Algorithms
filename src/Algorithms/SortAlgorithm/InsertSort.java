package Algorithms.SortAlgorithm;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author Jia RenHao
 * @create 2020-04-08
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {3, 2, 9, 10, 5, 8, 6, 7, 1, 4};
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println("---排序后---");
        System.out.println(Arrays.toString(arr));
        int[] ints = new int[10];
        /*int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);*/
    }

    public static void insertSort(int[] arr) {
        int insertVal;//记录下标为i的值
        int insertIndex;//记录i前面一个元素的下标
        //循环从1开始
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];//记录i前面一个元素的下标
            insertIndex = i - 1;//记录i前面一个元素的下标
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //循环结束说明找到了要插入的地方
            //如果insertIndex变化说明要换位置，没变就说明当前arr[i]已经是最大了
            if (insertIndex != i - 1) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
