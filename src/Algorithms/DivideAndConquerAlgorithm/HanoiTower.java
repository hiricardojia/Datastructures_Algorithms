package Algorithms.DivideAndConquerAlgorithm;

/**
 * @Title: 汉诺塔
 * @Description: 分治算法☞递归实现汉诺塔
 * @Author: Jia RenHao
 * @Create: 2020-04-19
 * @Version: V1.0
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
        System.out.println("一共进行了"+count+"次交换");
    }

    static int count;

    public static void hanoiTower(int num, char a, char b, char c) {
        count++;
        if (num == 1) {
            System.out.println("第1个盘:" + a + "=>" + c);
        } else {
            hanoiTower(num - 1, a, c, b);
            System.out.println("第" + num + "个盘:" + a + "=>" + c);
            hanoiTower(num - 1, b, a, c);
        }
    }
}
