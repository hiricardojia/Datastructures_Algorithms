package Algorithms.DynamicProgramming;

/**
 * @Title: 0/1背包问题
 * @Description:
 * @Author: Jia RenHao
 * @Create: 2020-04-27
 * @Version: V1.0
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3};//物品重量
        int[] value = {1500, 3000, 2000};//物品价值
        int capacity = 4;//背包容量
        int count = value.length;//物品个数
        int[][] v = new int[count + 1][capacity + 1];//价值表，行代表物品价值，列代表物品重量
        int[][] path = new int[count + 1][capacity + 1];//记录当前路径
        //初始化价值表
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (j < weight[i - 1]) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], value[i - 1] + v[i - 1][j - weight[i - 1]]);
                    if (v[i - 1][j] < value[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = value[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
