package Algorithms.SearchAlgorithm;

import java.util.Arrays;

/**
 * 斐波那契查找
 *
 * @author Jia RenHao
 * @create 2020-04-14
 */
public class FibonacciSearch {
    static final int MAXSIZE = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index=" + fibonacciSearch(arr, 1234));
    }

    /**
     * 得到一个斐波那契数列，默认长度20
     *
     * @return 返回有二十个元素的斐波那契数列
     */
    public static int[] getFibonacciArr() {
        int[] f = new int[MAXSIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < MAXSIZE; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibonacciSearch(int[] arr, int value) {
        int low = 0;//原数组开始下标
        int high = arr.length - 1;//原数组结束下标
        int[] fibonacciArr = getFibonacciArr();
        int k = 0;//k是斐波那契数列的下标
        int mid;//每次迭代的分割点

        //获取到斐波那契分割数值的下标
        //思想就是，把原数组的长度搞成斐波那契数列中的一个元素值-1，然后就可以利用斐波那契的特性分割
        //比如原数组arr={1,8,10,89,1000,1234}一共6个数
        //斐波那契数列fib={1,1,2,3,5,8,11...}
        //6位于fib中的5和8中间，所以我们找的k=5，是8的下标
        while (arr.length > fibonacciArr[k] - 1) {
            k++;
        }

        //现在原数组的长度一定是斐波那契数列中一个数字-1了
        //然后拷贝到temp中间数组中去
        int[] temp = Arrays.copyOf(arr, fibonacciArr[k] - 1);

        //然后因为copyOf的数组后面默认补0，我们要把它们换成原数组最后那个最大的数
        //比如copy后的数组：temp={1,8,10,89,1000,1234,0} => {1,8,10,89,1000,1234,1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //开始循环找到我们的value
        while (low <= high) {
            //分割点
            mid = low + fibonacciArr[k - 1] - 1;
            //解释：斐波那契一定满足fib[k]=fib[k-1] + fib[k-2]
            //数组当前的长度是fib[k]-1,然后就分成了两部分
            //左边数组长度fib[k-1]-1，右边数组长度就是总长度减去左边数组长度再减1，(fib[k]-1)-(fib[k-1]-1)-1=fib[k-2]-1
            if (value < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (value > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                //已经找到
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
