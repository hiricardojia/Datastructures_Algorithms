package Algorithm.SortAlgorithm;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @auther Jia RenHao
 * @create 2020-04-09
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        quickSort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

        /*int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);*/
    }

    /**
     * 快速排序
     *
     * @param arr 要排序的数组
     * @param low 左指针
     * @param high 右指针
     */
    public static void quickSort(int[] arr, int low, int high) {
        int l = low;//记录左下标，l会改变，low并不随着递归而改变
        int h = high;//记录右下标，h会改变，high并不随着递归而改变
        int pivot = arr[(low + high) / 2];//基准值
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
            System.out.println(Arrays.toString(arr));
        }
        //此处疑问：上面的循环退出条件就是l==h，这里还是否需要判断
        //l++是作为下一轮左边分区的low，h--是作为下一轮右边分区的high
        if (l == h) {
            l++;
            h--;
        }
        //一轮结束，进入下一轮递归
        //这两个if是作为递归头，如果没有下一次递归，则所有栈帧依次出栈
        //左分区递归
        if (low < h) {
            System.out.println("左---");
            quickSort(arr, low, h);
        }
        //右分区递归
        if (high > l) {
            System.out.println("右---");
            quickSort(arr, l, high);
        }
    }
}
