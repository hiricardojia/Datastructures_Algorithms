package Algorithm.SortAlgorithm;

import java.util.Arrays;

/**
 * 归并排序，分治策略
 *
 * @author Jia RenHao
 * @create 2020-04-10
 */
public class MergeSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1,temp);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int[] temp = new int[arr.length];
        long start = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1,temp);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
    }

    /**
     * 分+合
     *
     * @param arr   排序数组
     * @param start 开始下标
     * @param end   结束下标
     * @param temp  中间存放数组
     */
    public static void mergeSort(int[] arr, int start, int end, int[] temp) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid, temp);
            mergeSort(arr, mid + 1, end, temp);
            merge(arr, start, mid, end, temp);
        }
    }

    /**
     * 并
     *
     * @param arr   数组
     * @param start 开始下标
     * @param mid   前数组最后一个，mid+1就是后数组第一个
     * @param end   结束下标
     * @param temp  中间存放数组
     */
    private static void merge(int[] arr, int start, int mid, int end, int[] temp) {
        int i = start;//左边有序序列初始索引
        int j = mid + 1;//右边有序序列初始索引
        int tempIndex = 0;//temp的下标索引

        //(一)
        //先把两边有序序列数据按照规则放到temp中
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                temp[tempIndex] = arr[i];
                i++;
                tempIndex++;
            } else {
                temp[tempIndex] = arr[j];
                j++;
                tempIndex++;
            }
        }
        //(二)
        //将其中一个有序序列剩余元素添加到temp后头
        while (i <= mid) {
            temp[tempIndex] = arr[i];
            i++;
            tempIndex++;
        }
        while (j <= end) {
            temp[tempIndex] = arr[j];
            j++;
            tempIndex++;
        }
        //(三)
        //将temp中拷贝到arr中
        tempIndex = 0;
        int tempStart = start;
        while (tempStart <= end) {
            arr[tempStart] = temp[tempIndex];
            tempIndex++;
            tempStart++;
        }
    }
}
