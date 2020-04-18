package DataStructures.Tree;

/**
 * 线索化二叉树
 * 先序+中序线索化
 *
 * @author Jia RenHao
 * @create 2020-04-18
 */
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        Node root = new Node(1, "疾风剑豪");
        Node node2 = new Node(2, "影流之主");
        Node node3 = new Node(3, "盲僧");
        Node node4 = new Node(4, "放逐之刃");
        Node node5 = new Node(5, "戏命师");
        Node node6 = new Node(6, "刀锋之影");
        Node node7 = new Node(7, "诡术妖姬");
        threadedBinTree tree = new threadedBinTree();
        tree.setRoot(root);
        root.setLeftNode(node2);
        root.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node3.setLeftNode(node6);
        node3.setRightNode(node7);
        //先序线索化
        tree.preThreadedNodes();
        //中序线索化
        //tree.infixThreadedNodes();
        System.out.println("节点的前驱节点：" + node4.getLeftNode());
        System.out.println("节点的后继节点" + node4.getRightNode());
        //tree.infixThreadedList();//4 2 5 1 6 3 7
        tree.preThreadedList();// 1 2 4 5 3 6 7
    }
}

class threadedBinTree {
    private Node root;
    private Node pre;//用来保存当前节点的前一个节点

    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * 重载先序线索化方法，不用给参数，默认root
     */
    public void preThreadedNodes() {
        this.preThreadedNodes(root);
    }

    /**
     * 重载中序线索化方法，不用给参数，默认root
     */
    public void infixThreadedNodes() {
        this.infixThreadedNodes(root);
    }

    /**
     * 先序线索化
     *
     * @param node 当前线索化的节点
     */
    public void preThreadedNodes(Node node) {
        if (node == null) {
            return;
        }
        //处理前驱节点
        if (node.getLeftNode() == null) {
            node.setLeftNode(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRightNode() == null) {
            pre.setRightNode(node);
            pre.setRightType(1);
        }
        //把当前节点给pre
        pre = node;
        //线索化左子树
        if (node.getLeftType() == 0) {
            preThreadedNodes(node.getLeftNode());
        }
        //线索化右子树
        if (node.getRightType() == 0) {
            preThreadedNodes(node.getRightNode());
        }
    }

    /**
     * 中序线索化
     *
     * @param node 当前线索化的节点
     */
    public void infixThreadedNodes(Node node) {
        if (node == null) {
            return;
        }
        //先线索化左子树
        infixThreadedNodes(node.getLeftNode());
        //处理前驱节点
        if (node.getLeftNode() == null) {
            node.setLeftNode(pre);
            node.setLeftType(1);
        }
        //处理后继节点
        if (pre != null && pre.getRightNode() == null) {
            pre.setRightNode(node);
            pre.setRightType(1);
        }
        //把当前节点给pre
        pre = node;
        //线索化右子树
        infixThreadedNodes(node.getRightNode());
    }

    /**
     * 先序线性遍历线索化二叉树,非递归方式
     */
    public void preThreadedList() {
        //定义一个变量用来存储当前遍历的节点,从root开始
        Node curNode = root;
        while (curNode != null) {
            System.out.println(curNode);
            while (curNode.getLeftType() == 0) {
                curNode = curNode.getLeftNode();
                System.out.println(curNode);
            }
            curNode = curNode.getRightNode();
        }
    }
    /**
     * 中序线性遍历线索化二叉树,非递归方式
     */
    public void infixThreadedList() {
        //定义一个变量用来存储当前遍历的节点,从root开始
        Node curNode = root;
        while (curNode != null) {
            while (curNode.getLeftType() == 0) {
                curNode = curNode.getLeftNode();
            }
            System.out.println(curNode);
            while (curNode.getRightType() == 1) {
                curNode = curNode.getRightNode();
                System.out.println(curNode);
            }
            curNode = curNode.getRightNode();
        }
    }
}

class Node {
    private int no;
    private String name;
    private Node leftNode;
    private Node rightNode;
    private int leftType;//保存left节点的类型 0是左子树，1是前驱节点
    private int rightType;//保存right节点的类型，0是右子树，1是后继节点

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }
}