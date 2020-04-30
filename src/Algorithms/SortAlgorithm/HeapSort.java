package Algorithms.SortAlgorithm;

/**
 * @Title: 堆排序
 * @Description: 包括建堆，调整堆，和排序三个方法
 * @Author: Jia RenHao
 * @Create: 2020-04-20
 * @Version: V1.0
 */
public class HeapSort {
    public static void main(String[] args) {
        //int[] arr = {7, 1, 3, 10, 5, 2, 8, 9, 6};
        //buildHeap(arr);
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("---耗费时间---");
        System.out.println(end - start);
        //System.out.println("排序完成后");
        //System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        //升序排序，构建大顶堆
        buildHeap(arr);
        int temp;
        for (int i = arr.length - 1; i > 0; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //注意此处的长度是i，在交换完之后，沉到下面的数不参与重新建堆
            downAdjustHeap(arr, 0, i);
        }
    }

    /**
     * 将无序数组构建成堆(此处大顶堆)
     *
     * @param arr 要构建的无序数组
     */
    public static void buildHeap(int[] arr) {
        //从最后一个非叶节点开始，从下到上进行构建
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            downAdjustHeap(arr, i, arr.length);
        }
    }

    /**
     * 下沉调整大顶堆(如果要构建小顶堆，只需要把59行和63行判断条件改一下即可)
     *
     * @param arr         要调整的子树
     * @param parentIndex 当前子树根节点下标
     * @param length      当前子树的长度
     */
    public static void downAdjustHeap(int[] arr, int parentIndex, int length) {
        //保存当前根节点，用于最后的赋值
        int temp = arr[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        //如果上面元素交换，会导致下面的堆混乱，所以要循环，把上面小的数一层一层下沉
        //从左子节点开始
        while (childIndex < length) {
            //如果右子节点存在且左子节点的值小于右子节点的值，则定位到右子节点
            if (childIndex + 1 < length && arr[childIndex] < arr[childIndex + 1]) {
                childIndex++;
            }
            //如果父节点的值大于两个子节点的最大值，则无需交换，直接break，注意是temp
            if (temp >= arr[childIndex]) {
                break;
            }
            //说明子节点大于父节点，交换
            arr[parentIndex] = arr[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        arr[parentIndex] = temp;
    }
}