package DataStructures.Tree;

/**
 * @Title: 红黑树
 * @Description: 插入实现
 * @Author: Jia RenHao
 * @Create: 2020-04-26
 * @Version: V1.0
 */
public class RedBlackBinarySearchTree<Key extends Comparable<? super Key>, Value> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;
        private boolean color;//父节点指向他连接的颜色

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }

        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }
        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", N=" + N +
                    ", color=" + color +
                    '}';
        }
    }

    public void preOrder(){
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树空！！");
        }
    }
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.color = node.color;
        node.color = RED;
        temp.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;
        return temp;
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.color = node.color;
        node.color = RED;
        temp.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;
        return temp;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1, RED);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public static void main(String[] args) {
        RedBlackBinarySearchTree<Character, String> tree = new RedBlackBinarySearchTree<>();
        tree.put('S',"S");
        tree.put('E',"E");
        tree.put('A',"A");
        tree.put('R',"R");
        tree.put('C',"C");
        tree.put('H',"H");
        tree.put('X',"X");
        tree.put('M',"M");
        tree.put('P',"P");
        tree.put('L',"L");
        tree.preOrder();
    }
}
