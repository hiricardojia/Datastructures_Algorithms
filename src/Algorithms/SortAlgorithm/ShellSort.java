package Algorithms.SortAlgorithm;

/**
 * 希尔排序
 *
 * @author Jia RenHao
 * @create 2020-04-09
 */
public class ShellSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSortPro(arr);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        shellSortPro(arr);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
    }

    /**
     * 通过移位式希尔排序，效率高
     * 就是插入排序的升级版,通过记录元素和下标，直到找到合适的位置在进行交换，效率很高
     *
     * @param arr 数组
     */
    public static void shellSortPro(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //循环结束说明找到了要插入的地方
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 中间交换式希尔排序，效率低
     * 有点类似冒泡排序，一组之内判断，如果后比前小就交换，效率低，注意，这里是从组内的后面往前比较，重点理解第二层for
     *
     * @param arr 数组
     */
    public static void shellSortByExchange(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j + gap] < arr[j]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
    }
}
