package DataStructures.Tree;

import java.io.Serializable;

/**
 * @Title: BST二叉搜索树
 * @Description:
 * @Author: Jia RenHao
 * @Create: 2020-04-24
 * @Version: V1.0
 */
public class BinarySearchTree<Key extends Comparable<? super Key>, Value> {
    private Node root;

    /**
     * 节点类
     */
    private class Node implements Serializable {
        private Key key;//当前节点键
        private Value value;//当前节点值
        private Node left;//当前节点左子树
        private Node right;//当前节点右子树
        private int N;//当前节点作为根节点的子节点个数

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }

        public void infixOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.infixOrder();
            }
            if (this.right != null) {
                this.right.infixOrder();
            }
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", N=" + N +
                    '}';
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.N;
        }
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Key getMin() {
        return getMin(root).key;
    }

    private Node getMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return getMin(node.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node temp = node;
            node = getMin(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void preOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("树空！！");
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
        tree.put(7, "7");
        tree.put(3, "3");
        tree.put(10, "10");
        tree.put(1, "1");
        tree.put(5, "5");
        tree.put(9, "9");
        tree.put(12, "12");
        System.out.println(tree.size());
        tree.preOrder();
        System.out.println(tree.getMin());
        tree.delete(7);
        tree.preOrder();
    }
}
