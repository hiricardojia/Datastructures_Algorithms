package Algorithms.SortAlgorithm;

/**
 * 基数排序（桶排序升级版）
 *
 * @author Jia RenHao
 * @create 2020-04-11
 */
public class RadixSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
    }

    public static void radixSort(int[] arr) {
        //定义一个二维数组作为桶
        int[][] bucket = new int[10][arr.length];
        //定义一个桶内的索引数组,下标代表桶的个数，数组元素代表这个桶内元素的个数
        int[] bucketElementCounts = new int[10];
        //获取当前数组最大位数的位数长度
        int MAX = arr[0];
        //循环遍历找到数组中最大的数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > MAX) {
                MAX = arr[i];
            }
        }
        //得到最大数的位数
        int MAXLength = (MAX + "").length();//比如 6324 最大位数就是4
        //最大位数是几就需要几次排序
        for (int i = 0, n = 1; i < MAXLength; i++, n *= 10) {
            //获取当前位数，从个位十位百位以此类推
            int digitOfElement;
            //往桶里放数据
            for (int j = 0; j < arr.length; j++) {
                digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //将放入桶内的数字再取出放回原数组
            int index = 0;//放回原数组的过程中需要的下标索引
            for (int k = 0; k < bucket.length; k++) {
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
                bucketElementCounts[k] = 0;
            }
        }
    }
}
