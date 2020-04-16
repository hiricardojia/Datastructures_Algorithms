package Algorithm.SortAlgorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @auther Jia RenHao
 * @create 2020-04-08
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr = {3, 2, 9, 10, 5, 8, 6, 7, 1, 4};
        //int[] arr = {1,2,3,4,5};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);

    }

    public static void bubbleSort(int[] arr){
        int temp;
        boolean flag;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //System.out.println(Arrays.toString(arr));
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
