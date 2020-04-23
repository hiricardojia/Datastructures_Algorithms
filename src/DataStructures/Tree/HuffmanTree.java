package DataStructures.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Title: 赫夫曼树
 * @Description: 构建赫夫曼树
 * @Author: Jia RenHao
 * @Create: 2020-04-21
 * @Version: V1.0
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] weight = {13, 7, 8, 3, 29, 6, 1};
        HTNode root = createHuffmanTree(weight);
        root.preOrder();
    }

    /**
     * 创建赫夫曼树
     * @param weight 权值数组
     * @return 生成的赫夫曼树的根节点
     */
    public static HTNode createHuffmanTree(int[] weight) {
        List<HTNode> nodeList = new ArrayList<>();
        for (int i : weight) {
            nodeList.add(new HTNode(i));
        }
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            //System.out.println(nodeList);

            HTNode leftNode = nodeList.get(0);
            HTNode rightNode = nodeList.get(1);

            HTNode parentNode = new HTNode(leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            nodeList.add(parentNode);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
        }
        return nodeList.get(0);
    }

    public void preOrder(HTNode root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空");
        }
    }
}

class HTNode implements Comparable<HTNode> {
    int weight;//权值
    HTNode left;//左子树
    HTNode right;//右子树

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public HTNode(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HTNode{" +
                "weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(HTNode o) {
        //表示从小到大排序，如果要从大到小加个负号或者倒过来
        return this.weight - o.weight;
    }
}