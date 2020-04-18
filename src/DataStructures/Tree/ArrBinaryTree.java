package DataStructures.Tree;

/**
 * 数组实现前中后序遍历二叉树
 *
 * @author Jia RenHao
 * @create 2020-04-18
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        //             1
        //          2     3
        //        4  5   6  7
        int[] arr = {1, 2, 3, 4, 5, 6, 7,};
        arrayBinaryTree arrBinaryTree = new arrayBinaryTree(arr);
        //arrBinaryTree.preOrderTree(0);
        //arrBinaryTree.infixOrderTree(0);
        arrBinaryTree.postOrderTree(0);
    }
}

class arrayBinaryTree {
    private int[] arr;

    public arrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 先序遍历顺序二叉树
     * 1 2 4 5 3 6 7
     *
     * @param index 数组下标
     */
    public void preOrderTree(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        System.out.println(arr[index]);
        if ((2 * index + 1) < arr.length) {
            preOrderTree(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            preOrderTree(2 * index + 2);
        }
    }

    /**
     * 中序遍历顺序二叉树
     * 4 2 5 1 6 3 7
     *
     * @param index 数组下标
     */
    public void infixOrderTree(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            infixOrderTree(2 * index + 1);
        }
        System.out.println(arr[index]);
        if ((2 * index + 2) < arr.length) {
            infixOrderTree(2 * index + 2);
        }
    }

    /**
     * 后序遍历顺序二叉树
     * 4 5 2 6 7 3 1
     *
     * @param index 数组下标
     */
    public void postOrderTree(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((2 * index + 1) < arr.length) {
            postOrderTree(2 * index + 1);
        }
        if ((2 * index + 2) < arr.length) {
            postOrderTree(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}