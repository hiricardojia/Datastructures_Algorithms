package DataStructures.Tree;

/**
 * 二叉树
 *
 * @auther Jia RenHao
 * @create 2020-04-16
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = new TreeNode(1, "疾风剑豪");
        TreeNode node2 = new TreeNode(2, "影流之主");
        TreeNode node3 = new TreeNode(3, "放逐之刃");
        TreeNode node4 = new TreeNode(4, "盲僧");
        TreeNode node5 = new TreeNode(5, "无双剑姬");

        binaryTree.setRoot(root);
        root.setLeftNode(node2);
        root.setRightNode(node4);
        node4.setLeftNode(node3);
        node4.setRightNode(node5);

        //        1
        //     2     4
        //         3   5
        //前序遍历
        System.out.println("前序遍历");
        binaryTree.proOrder();

        //中序遍历
        System.out.println("中序遍历");
        binaryTree.infixOrder();

        //后序遍历
        System.out.println("后序遍历");
        binaryTree.postOrder();

    }
}

class BinaryTree {
    private TreeNode root;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void proOrder(){
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder(){
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder(){
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

}

class TreeNode {
    private int no;
    private String name;
    private TreeNode leftNode;
    private TreeNode rightNode;

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        if (this.leftNode != null) {
            this.leftNode.postOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.postOrder();
        }
        System.out.println(this);
    }

    public TreeNode() {
    }

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public TreeNode(int no, String name, TreeNode leftNode, TreeNode rightNode) {
        this.no = no;
        this.name = name;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
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

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}