package DataStructures.Recursion;

import java.util.Collection;

/**
 * 八皇后问题
 * 本程序采用一维数组递归回溯算法
 *
 * @auther Jia RenHao
 * @create 2020-04-07
 */
public class EightQueen {
    //定义一个MAX最多八个皇后
    int MAX = 8;
    //创建一维数组，例如array={0,4,7,5,2,6,1,3}
    //重点！！！->下标是几就是第几+1个皇后，就是说array[i] = val，i+1是行，也是第几个皇后，val+1是列
    int[] array = new int[MAX];
    static int count = 0;
    static int JudgeCount = 0;
    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.check(0);
        System.out.println("一共有"+count+"种解法");
        System.out.println("一共进行了"+JudgeCount+"次判断");
    }

    /**
     * 递归主体
     * @param n 从第n个皇后开始
     */
    private void check(int n){
        //递归头：n是从0开始，如果n == MAX，说明八个皇后已经摆满了，将要摆第九个
        if (n == MAX) {
            count++;//解计数器
            printArr();
            return;
        }
        //这里的循环是列循环
        for (int i = 0; i < MAX; i++) {
            //把当前第n个皇后也就是第n行，放到第n行第i列中
            array[n] = i;
            if (judge(n)) {
                //判断为true说明不冲突，继续放下一行的皇后
                check(n + 1);
            }
            //判断为false，i++，把皇后放在当前行的下一列即可
        }
    }
    /**
     * 每放置一个皇后就要和前面已经放好的皇后进行判断，是否当前皇后和前面已经摆放好的皇后冲突
     * @param n 当前第几个皇后
     * @return true说明没冲突
     */
    private boolean judge(int n){
        JudgeCount++;//判断次数计数器
        //这里的循环是列循环
        //此处刚刚放入一个皇后，进行判断刚放入的位置和前面已经放好位置的皇后有无冲突
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]是对新皇后列位置和旧皇后列位置判断，如果一样，说明在同一列
            //Math.abs(n - i) == Math.abs(array[n] - array[i])是对是否在一条斜线进行判断，式子是对行差和列差判断，如果一样说明在一条斜线上，可用斜率理解
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
    /**
     * 打印一个解
     */
    private void printArr(){
        for (int value : array) {
            System.out.print(value);
        }
        System.out.println();
    }
}
