package Algorithms.SortAlgorithm;

/**
 * 选择排序
 *
 * @author Jia RenHao
 * @create 2020-04-08
 */
public class SelectSort {
    public static void main(String[] args) {
        /*int[] arr = {3, 2, 9, 10, 5, 8, 6, 7, 1, 4};
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("---排序后---");
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int curMIN = arr[i];
            int curMinIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < curMIN) {
                    curMIN = arr[j];
                    curMinIndex = j;
                }
            }
            if (curMinIndex != i) {
                arr[curMinIndex] = arr[i];
                arr[i] = curMIN;
            }
        }
    }
}
